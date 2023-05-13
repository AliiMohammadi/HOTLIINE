package ir.example.androiddevegame;

import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class GameObject {
    public static Point OUTOFSCREEN = new Point(0,-500);

    public static void SetVisible(boolean visivble, ImageView object){
        if(visivble)
        object.setVisibility(View.VISIBLE);
        else
            object.setVisibility(View.INVISIBLE);

    }

    public static void GoOut(ImageView gameobjec){
        gameobjec.setX(OUTOFSCREEN.x);
        gameobjec.setY(OUTOFSCREEN.y);
    }
    public static void GoRandomPlace(ImageView gameobjec){
        Random rd = new Random();

        int minY = 300;
        int maxY = 9000;

        int minX = -1500;
        int maxX = 3000;

        int random_intY = (int)Math.floor(Math.random() * (maxY - minY + 1) + minY);
        int random_intX = ((int)Math.floor(Math.random() * (maxX - minX + 1) + minX));

        gameobjec.setX(random_intX);
        gameobjec.setY(-random_intY);
    }
}
