package ir.example.androiddevegame;

import android.graphics.Point;
import android.widget.ImageView;

public class MathF {
    private static float rotationAngle;

    public static float Distnce(float x1 , float y1, float x2 , float y2){

        return (float) Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
    }
    public static float LookAt(Point p1 , Point p2){
            float dx = p1.x - p2.x;
            float dy = p1.y - p2.y;
            rotationAngle += (Math.atan2(dy, dx) * 180 / Math.PI)%360;
            return rotationAngle;
    }
    public static float LookAt2(ImageView im1, ImageView im2){
        float x1 = im1.getX() + im1.getWidth() /2;
        float y1 = im1.getY() + im1.getHeight() /2;
        float x2 = im2.getX() + im2.getWidth() /2;
        float y2 = im2.getY() + im2.getHeight() /2;

        double anglerad = Math.atan2(y2-y1,x2-x1);

        return (float) Math.toDegrees(anglerad);

    }
    public static float AngleOfTwoPoints (Point p1 , Point p2){
        //double distance = Math.sqrt(Math.pow(p2.x-p1.x,2) + Math.pow(p2.y-p1.y,2));
        double angleradians = Math.atan((p2.y - p1.y) / (p2.x - p1.x));
        return (float) Math.toDegrees(angleradians);
    }

    public static double sin(double x){
        x = Math.toRadians(x);

        double Result = 0;
        for (int i = 0;i<10;i++){
            Result+=((i%2 == 0)? 1: -1) * (Math.pow(x,2*i+1))/factorial(2*i+1);
        }

        return Result;
    }
    public static double cos (double x){
        x = Math.toRadians(x);

        double Result = 0;

        for (int i = 0;i<10;i++){
            Result+=((i%2 == 0)? 1: -1) * (Math.pow(x,2*i))/factorial(2*i);
        }

        return Result;
    }
    public static int factorial(int n){
        int res = 1;

        for (int i = n; i>0;i--)
            res*=i;

        return res;
    }
        /*
        public static float AngleOfTwoPoints (Point p1 , Point p2){
            float dx = p2.x - p1.x;
            float dy = p2.y - p1.y;
            return (float) ((Math.atan2(dy, dx) * 180 / Math.PI));
        }

         */
}
