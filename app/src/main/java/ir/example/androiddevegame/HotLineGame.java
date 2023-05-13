package ir.example.androiddevegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

public class HotLineGame extends AppCompatActivity
{
    private ImageView bottle1;
    private ImageView bottle2;
    private ImageView bottle3;

    private ImageView projectile;
    private Button FierBTN;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_line_game__activity);
        Start();
    }

    private void Start(){

        TheGuyScript.TheGuyIMG = (ImageView) findViewById(R.id.TheGuyIMG);

        bottle1 = (ImageView) findViewById(R.id.bottle1);
        bottle2 = (ImageView) findViewById(R.id.bottle2);
        bottle3 = (ImageView) findViewById(R.id.bottle3);
        projectile = (ImageView) findViewById(R.id.projectile);

        projectile.setVisibility(View.INVISIBLE);

        FierBTN = (Button) findViewById(R.id.fierBTN);
        DebugText.Text = (TextView) findViewById(R.id.debugtext);
        FierBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FierWeapon();
            }
        });

        //NOTE: The Guy image should be as big as screen cuz it depends on touch area!!
        StartLookingAtTouch();

        timer = new Timer();
        timer.schedule(new EngineLoop(), 0, 10); // delay of 1 second
    }
    private void StartLookingAtTouch(){
        TheGuyScript.TheGuyIMG.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Input.UpdateTouchPoint(new Point((int) event.getX(), (int) event.getY()));
                        break;
                    case MotionEvent.ACTION_MOVE:

                        Point p1 = new Point((int) event.getX(), (int) event.getY());

                        TheGuyScript.TheGuyIMG.setRotation(MathF.LookAt(p1,Input.LastTouchPoint));

                        int Dirx = (int) (MathF.cos(TheGuyScript.GetAimAngle()) * 10);
                        int Diry = (int) -(MathF.sin(TheGuyScript.GetAimAngle()) * 10);

                        DebugText.ShowTextDebug("Player: " + String.valueOf(TheGuyScript.GetAimAngle() + " .Direction: " + String.valueOf(Dirx) + "," + String.valueOf(Diry)));
                        //DebugText.setText("Player: " + String.valueOf(TheGuyScript.GetAimAngle()));
                        //ShowTextDebug(String.format("%2f",((TheGuyScript.TheGuyIMG.getRotation() + 270)%360)));
                        //ShowTextDebug(String.format("%2f",MathF.AngleOfTwoPoints(new Point((int) bottle1.getX(),(int)bottle1.getY()),new Point((int)TheGuyScript.TheGuyIMG.getX(),(int)TheGuyScript.TheGuyIMG.getY()))));
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private void FierWeapon(){
        //Toast.makeText(getApplicationContext(),"SHOOT",Toast.LENGTH_SHORT).show();
        /* DIABLED CODE
        int playerangle = TheGuyScript.GetAimAngle();

        CheckTheShoot(bottle1,playerangle);
        //CheckTheShoot(bottle2,playerangle);
        //CheckTheShoot(bottle3,playerangle);

         */

        ResetTheProjectile();

    }
    private void ResetTheProjectile(){
        float aculX = TheGuyScript.TheGuyIMG.getX() + (TheGuyScript.TheGuyIMG.getWidth() / 2) - 50;
        float actulY = TheGuyScript.TheGuyIMG.getY() + (TheGuyScript.TheGuyIMG.getHeight() / 2) - 50;

        projectile.setVisibility(View.VISIBLE);

        projectile.setX(aculX);
        projectile.setY(actulY);
    }
    private class EngineLoop extends TimerTask {
        @Override
        public void run() {
            //Infinite loop codes HERE:

            //Bullet script
            if(projectile.getVisibility() == View.VISIBLE){

                CalculateVelocityOfProjectile();
                CheckAllObjectCollitions();
            }

        }
    }
    private void CalculateVelocityOfProjectile(){

        int velocity = 100;
        int PlayGround = 1400;

        //if bulltet went out of screen, then stop moving.
        if(Math.abs(projectile.getX()) > PlayGround || Math.abs(projectile.getY()) > PlayGround){
            velocity = 0;
        }

        int aimangle = TheGuyScript.GetAimAngle();

        float Dirx = (float) (MathF.cos(aimangle) * velocity);
        float Diry = (float) (MathF.sin(aimangle) * velocity);

        if(aimangle > 180){
            aimangle = (aimangle % 180);
            Dirx *=-1;
            Diry*=-1;
        }


        projectile.setX(projectile.getX() + Dirx);
        projectile.setY(projectile.getY() + -Diry);
    }
    private void CheckAllObjectCollitions(){
        CheckShoot(bottle1,projectile);
        CheckShoot(bottle2,projectile);
        CheckShoot(bottle3,projectile);
    }
    private void CheckShoot(ImageView object, ImageView projectile){

        if(Physics.IsColiding(object,projectile)){
            GameObject.GoOut(object);
        }
    }

}

/*DISABLED RAYCASTING SHOOTING:
    //In this function we will check if our shoot actualy hitted something or missed.
    boolean CheckTheShoot(ImageView object , int shootangle){

        int objectangle = ObjectAngle(object);


        int aim = (Math.abs(shootangle - objectangle));
        DebugText.ShowTextDebug("Aim : " + String.valueOf(aim) + "Player: " + String.valueOf(shootangle) + "obj : " + String.valueOf(objectangle));
        if(aim <= 1){
            object.setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }

    private int ObjectAngle(ImageView objec){
        MathF mf = new MathF();
        return (int) (mf.AngleOfTwoPoints(new Point((int) objec.getX(),(int)objec.getY()) , new Point((int)TheGuyScript.TheGuyIMG.getX(),(int)TheGuyScript.TheGuyIMG.getY())));
    }
 */
