package ir.example.androiddevegame;

import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;

public class Physics {
    public static boolean IsColiding (ImageView object1 , ImageView object2){
        //IF they are invisible they are always not coliding!
        if(object1.getVisibility() == View.INVISIBLE || object2.getVisibility() == View.INVISIBLE)
            return false;

        float x1 = object1.getX();
        float x2 = object2.getX();

        float y1 = object1.getY();
        float y2 = object2.getY();

        float rad1 = (object1.getWidth() / 2);
        float rad2 = (object2.getWidth() /2);

        return IsColiding(new Point((int) x1, (int) y1) , new Point((int) x2, (int) y2),rad1,rad2);
    }
    public static boolean IsColiding (Point p1 , Point p2 , float radias1,float radias2){

        float x1 = p1.x;
        float x2 = p2.x;

        float y1 = p1.y;
        float y2 = p2.y;

        float distance = (float) Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));

        return ((radias1 + radias2) >= distance);
    }
}
