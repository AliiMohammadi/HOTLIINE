package ir.example.androiddevegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


import java.util.Timer;
import java.util.TimerTask;

public class HotLineGame extends AppCompatActivity
{
    public ImageView BattleFiledAreaIMG;
    private Timer timer;

    private float lastTouchX, lastTouchY;
    private float rotationAngle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_line_game__activity);
        Start();
    }

    private void Start(){
        TheGuyScript.TheGuyIMG = (ImageView) findViewById(R.id.TheGuyIMG);
        BattleFiledAreaIMG = (ImageView) findViewById(R.id.BattleFiledAreaIMG);

        TheGuyScript.TheGuyIMG.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastTouchX = event.getX();
                        lastTouchY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = event.getX() - lastTouchX;
                        float dy = event.getY() - lastTouchY;
                        rotationAngle += Math.atan2(dy, dx) * 180 / Math.PI;
                        TheGuyScript.TheGuyIMG.setRotation(rotationAngle);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        timer = new Timer();
        timer.schedule(new MyTimerTask(), 0, 10); // delay of 1 second
    }
    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            //EngineLoop();
        }
    }
    private void EngineLoop(){
        //TheGuyScript.TheGuyIMG.setRotation(AngleToLook(new Point((int) TheGuyScript.TheGuyIMG.getX(), (int) TheGuyScript.TheGuyIMG.getY()),touch));
    }
    public int AngleToLook(Point p1 , Point p2){
        float X = p1.x - p2.x;
        float Y = p1.y - p2.y;

        Point vec = new Point((int)X, (int)Y);

        return (int) -(Math.atan2(vec.x, vec.y) * (180 / Math.PI));
    }
}
/*
    public static float AngleToLook(Vector2 Position1, Vector2 Position2)
    {

        float X = Position1.x - Position2.x;
        float Y = Position1.y - Position2.y;

        Vector2 vec = new Vector2(X, Y);
        vec.Normalize();

        float tt = -(Mathf.Atan2(vec.x, vec.y) * (180 / Mathf.PI) + 90);

        return tt;
    }

 */