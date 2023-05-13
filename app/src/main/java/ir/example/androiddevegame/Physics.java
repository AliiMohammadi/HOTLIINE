package ir.example.androiddevegame;

import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Physics {
    public static boolean IsColiding (ImageView object1 , ImageView object2){
        //IF they are invisible they are always not coliding!
        if(object1.getVisibility() == View.INVISIBLE || object2.getVisibility() == View.INVISIBLE)
            return false;

        float x1 = object1.getX() + object1.getWidth() / 2;
        float x2 = object2.getX() + object2.getWidth() / 2;

        float y1 = object1.getY() + object1.getHeight() / 2;
        float y2 = object2.getY() + object2.getHeight() / 2;

        float rad1 = (object1.getWidth() / 2);
        float rad2 = (object2.getWidth() /2);

        return IsColiding(new Point((int) x1, (int) y1) , new Point((int) x2, (int) y2),rad1,rad2);
    }
    public static boolean IsColiding (ImageView object1 , ImageView object2, float radias1,float radias2){
        //IF they are invisible they are always not coliding!
        if(object1.getVisibility() == View.INVISIBLE || object2.getVisibility() == View.INVISIBLE)
            return false;

        float x1 = object1.getX() + object1.getWidth() / 2;
        float x2 = object2.getX() + object2.getWidth() / 2;

        float y1 = object1.getY() + object1.getHeight() / 2;
        float y2 = object2.getY() + object2.getHeight() / 2;


        return IsColiding(new Point((int) x1, (int) y1) , new Point((int) x2, (int) y2),radias1,radias2);
    }
    public static boolean IsColiding (ImageView object1 , ImageView object2,float radias2){
        //IF they are invisible they are always not coliding!
        if(object1.getVisibility() == View.INVISIBLE || object2.getVisibility() == View.INVISIBLE)
            return false;

        float x1 = object1.getX() + object1.getWidth() / 2;
        float x2 = object2.getX() + object2.getWidth() / 2;

        float y1 = object1.getY() + object1.getHeight() / 2;
        float y2 = object2.getY() + object2.getHeight() / 2;

        float rad1 = (object1.getWidth() / 2);

        return IsColiding(new Point((int) x1, (int) y1) , new Point((int) x2, (int) y2),rad1,radias2);
    }
    public static  boolean IsColiding(ImageView object1 , Point p2 , float radias2){
        float rad1 = (object1.getWidth() / 2);
        return IsColiding(new Point((int) object1.getX(), (int) object1.getY()),p2,rad1,radias2);
    }
    public static boolean IsColiding (Point p1 , Point p2 , float radias1,float radias2){
        float distance = MathF.Distnce(p1.x,p1.y,p2.x,p2.y);

        return ((radias1 + radias2) >= distance);
    }
}
