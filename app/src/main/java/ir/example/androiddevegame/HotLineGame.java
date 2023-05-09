package ir.example.androiddevegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class HotLineGame extends AppCompatActivity
{
    public ImageView TheGuyIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_line_game__activity);

        Start();
    }

    private void Start(){
        TheGuyIMG = (ImageView) findViewById(R.id.TheGuyIMG);
        //EngineLoop();
    }

    private void EngineLoop(){
        //TODO: Instead of infitie loop, you can use when player touch screen event I guess.
        Handler handler = new Handler();
        while (true){
            handler.postDelayed(new Runnable() {
                public void run() {
                    TheGuyIMG.setRotation(TheGuyIMG.getRotation() + 5);
                }
            }, 10);
        }

    }
}