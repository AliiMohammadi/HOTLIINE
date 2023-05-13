package ir.example.androiddevegame;

import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;

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
}
