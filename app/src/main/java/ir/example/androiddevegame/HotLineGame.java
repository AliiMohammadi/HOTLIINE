package ir.example.androiddevegame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
    //TODO:
    // Make a Shout gun animation.
    // Create sound and music system for game.

    private ImageView bottle1;
    private ImageView bottle2;
    private ImageView bottle3;
    private ImageView projectile;
    private ImageView mob1;
    private ImageView mob2;
    private ImageView mob3;
    private ImageView mob4;
    private ImageView mob5;

    private ImageView deadmob1;
    private ImageView deadmob2;
    private ImageView deadmob3;
    private ImageView deadmob4;
    private ImageView deadmob5;
    private ImageView deadmob6;
    private ImageView deadmob7;

    private Button FierBTN;
    private Timer timer;

    private int Kills = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_line_game__activity);
        Start();
    }

    private void Start(){

        TheGuyScript.TheGuyIMG = (ImageView) findViewById(R.id.TheGuyIMG);
        TheGuyScript.DeadTheGuyIMG = (ImageView) findViewById(R.id.deadtheguuy);

        GameObject.GoOut(TheGuyScript.DeadTheGuyIMG);

        bottle1 = (ImageView) findViewById(R.id.bottle1);
        bottle2 = (ImageView) findViewById(R.id.bottle2);
        bottle3 = (ImageView) findViewById(R.id.bottle3);

        mob1 = (ImageView) findViewById(R.id.mob1);
        mob2 = (ImageView) findViewById(R.id.mob2);
        mob3 = (ImageView) findViewById(R.id.mob3);
        mob4 = (ImageView) findViewById(R.id.mob4);
        mob5 = (ImageView) findViewById(R.id.mob5);

        GameObject.GoRandomPlace(mob1);
        GameObject.GoRandomPlace(mob2);
        GameObject.GoRandomPlace(mob3);
        GameObject.GoRandomPlace(mob4);
        GameObject.GoRandomPlace(mob5);

        deadmob1 = (ImageView) findViewById(R.id.mobdead1);
        deadmob2 = (ImageView) findViewById(R.id.mobdead2);
        deadmob3 = (ImageView) findViewById(R.id.mobdead3);
        deadmob4 = (ImageView) findViewById(R.id.mobdead4);
        deadmob5 = (ImageView) findViewById(R.id.mobdead5);
        deadmob6 = (ImageView) findViewById(R.id.mobdead5);
        deadmob7 = (ImageView) findViewById(R.id.mobdead5);

        GameObject.GoOut(deadmob1);
        GameObject.GoOut(deadmob2);
        GameObject.GoOut(deadmob3);
        GameObject.GoOut(deadmob4);
        GameObject.GoOut(deadmob5);
        GameObject.GoOut(deadmob6);
        GameObject.GoOut(deadmob7);

        projectile = (ImageView) findViewById(R.id.projectile);

        projectile.setVisibility(View.INVISIBLE);

        FierBTN = (Button) findViewById(R.id.fierBTN);
        DebugText.Text = (TextView) findViewById(R.id.debugtext);
        FierBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(TheGuyScript.Alive)
                    FierWeapon();
            }
        });

        //NOTE: The Guy image should be as big as screen cuz it depends on touch area!!
        StartLookingAtTouch();
        ResetMob(mob1);

        DebugText.ShowTextDebug(String.valueOf(Kills) + " KILLS");


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

                        if(TheGuyScript.Alive)
                            TheGuyScript.TheGuyIMG.setRotation(MathF.LookAt(p1,Input.LastTouchPoint));

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
        DebugText.ShowTextDebug(String.valueOf(Kills) + " KILLS");

    }
    private void ResetTheProjectile(){
        float aculX = TheGuyScript.TheGuyIMG.getX() + (TheGuyScript.TheGuyIMG.getWidth() / 2) - 20;
        float actulY = TheGuyScript.TheGuyIMG.getY() + (TheGuyScript.TheGuyIMG.getHeight() / 2) - 20;

        projectile.setVisibility(View.VISIBLE);

        projectile.setX(aculX);
        projectile.setY(actulY);
    }
    private void ResetMob(ImageView mob){
        mob1.setX(100);
        mob1.setY(-100);
    }
    int cyclecounter = 0;
    private class EngineLoop extends TimerTask {
        @Override
        public void run() {
            //Infinite loop codes HERE:

            //Bullet script
            if(projectile.getVisibility() == View.VISIBLE){
                CalculateVelocityOfProjectile();
                CheckAllObjectCollitions();
            }
            //mobs
            //First initial cycle for load.
            if(cyclecounter < 10){
                cyclecounter++;
                return;
            }
            MakeMobsKillTheGuy();
        }
    }
    private void CalculateVelocityOfProjectile(){

        int velocity = 60;
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

        CheckShoot(mob1,projectile , true);
        CheckShoot(mob2,projectile , true);
        CheckShoot(mob3,projectile , true);
        CheckShoot(mob4,projectile , true);
        CheckShoot(mob5,projectile , true);
    }
    private  void MakeMobsKillTheGuy(){
        KillTheGuy(mob1);
        KillTheGuy(mob2);
        KillTheGuy(mob3);
        KillTheGuy(mob4);
        KillTheGuy(mob5);
    }
    private void CheckShoot(ImageView object, ImageView projectile){

        if(Physics.IsColiding(object,projectile)){
            GameObject.GoOut(object);
        }
    }
    int deadbodytuern = 1;
    private void CheckShoot(ImageView object, ImageView projectile , boolean RandomLocation){

        if(Physics.IsColiding(object,projectile , 20,20)){

            switch (deadbodytuern){
                case 1:
                    SwapDeadBody(deadmob1,object);
                    deadbodytuern++;
                    break;
                case 2:
                    SwapDeadBody(deadmob2,object);
                    deadbodytuern++;
                    break;
                case 3:
                    SwapDeadBody(deadmob3,object);
                    deadbodytuern++;
                    break;
                case 4:
                    SwapDeadBody(deadmob4,object);
                    deadbodytuern++;
                    break;
                case 5:
                    SwapDeadBody(deadmob5,object);
                    deadbodytuern ++;
                    break;
                case 6:
                    SwapDeadBody(deadmob6,object);
                    deadbodytuern ++;
                    break;
                case 7:
                    SwapDeadBody(deadmob7,object);
                    deadbodytuern = 1;
                    break;
                default:
                    break;
            }

            Kills++;
            GameObject.GoRandomPlace(object); //Kill comformed.
        }
    }

    private void SwapDeadBody(ImageView deadbody ,ImageView object ){
        deadbody.setX(object.getX());
        deadbody.setY(object.getY());
        deadbody.setRotation(MathF.LookAt2(deadbody,TheGuyScript.TheGuyIMG));
    }
    private void KillTheGuy(ImageView mob){
        if(!TheGuyScript.Alive)
            return;

        mob.setRotation(MathF.LookAt2(mob,TheGuyScript.TheGuyIMG));

        float Speed = 0.3f + Kills / 50;

        MoveTowardTo(mob,TheGuyScript.TheGuyIMG , Speed);

        if(Physics.IsColiding(mob,TheGuyScript.TheGuyIMG,100,100)){
            TheGuyScript.Die();
            TheGuyScript.DeadTheGuyIMG.setX(TheGuyScript.TheGuyIMG.getX());
            TheGuyScript.DeadTheGuyIMG.setY(TheGuyScript.TheGuyIMG.getY());
            GameObject.GoOut(TheGuyScript.TheGuyIMG);

            //((ImageView)findViewById(R.id.TheGuyIMG)).setImageResource(R.drawable.theguydead);
        }
    }
    private void MoveTowardTo(ImageView imagetomove , ImageView imagetarget , float speed){

        Point p1 = new Point((int) (imagetomove.getX() + (imagetomove.getWidth() / 2)), (int) (imagetomove.getY() + (imagetomove.getHeight() / 2)));
        Point p2 = new Point((int) (imagetarget.getX() + (imagetarget.getWidth() / 2)), (int) (imagetarget.getY() + (imagetarget.getHeight() / 2)));


        float x = (p2.x - p1.x);
        float y = (p2.y - p1.y);


        imagetomove.setX(imagetomove.getX() + x * speed / 100);
        imagetomove.setY( imagetomove.getY() + y * speed / 100);


        /*
        float velocity = 1;

        float startx = imagetomove.getX();
        float starty = imagetomove.getY();
        float endx = imagetarget.getX();
        float endy = imagetarget.getY();

        float distancex = endx - startx;
        float distancey = endy - starty;

        double distance = Math.sqrt(Math.pow(distancex,2) + Math.pow(distancey,2));

        float directionx = (float) (distancex/distance);
        float directiony = (float) (distancey/distance);

        imagetomove.setX(imagetomove.getX() + directionx);
        imagetomove.setY(imagetomove.getY() + directiony);

         */

    }

    public void moveImageView(ImageView imageViewToMove, ImageView imageViewTarget) {
        // Get the current position of the image view to move
        float startX = imageViewToMove.getX();
        float startY = imageViewToMove.getY();

        // Get the target position of the image view to move
        float endX = imageViewTarget.getX();
        float endY = imageViewTarget.getY();

        // Calculate the distance between the two image views
        float distanceX = endX - startX;
        float distanceY = endY - startY;

        // Calculate the angle between the two image views
        double angleRadians = Math.atan2(distanceY, distanceX);
        double angleDegrees = Math.toDegrees(angleRadians);

        // Rotate and move the image view to its new position
        imageViewToMove.setRotation((float) angleDegrees);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageViewToMove, "x", endX);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageViewToMove, "y", endY);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.setDuration(1000);
        animatorSet.start();
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
