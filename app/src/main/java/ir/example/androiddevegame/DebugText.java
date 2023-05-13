package ir.example.androiddevegame;

import android.widget.TextView;

public class DebugText {

    public static TextView Text;
    public static void ShowTextDebug(float mess){
        ShowTextDebug(String.format("%2f",mess));
    }
    public static void ShowTextDebug(int mess){
        ShowTextDebug(String.valueOf(mess));
    }
    public static void ShowTextDebug(String mess) {
        Text.setText(mess);
    }
}
