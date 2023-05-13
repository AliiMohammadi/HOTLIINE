package ir.example.androiddevegame;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class TheGuyScript {
    public static boolean Alive = true;
    public static ImageView TheGuyIMG;
    public static int GetAimAngle(){
        return (int) Math.abs((TheGuyIMG.getRotation()) % 360);
    }
}
