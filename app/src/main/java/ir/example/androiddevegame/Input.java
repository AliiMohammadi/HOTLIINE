package ir.example.androiddevegame;

import android.graphics.Point;

public class Input {
    public static Point LastTouchPoint;

    public static  void UpdateTouchPoint(Point point){
        LastTouchPoint = point;
    }
}
