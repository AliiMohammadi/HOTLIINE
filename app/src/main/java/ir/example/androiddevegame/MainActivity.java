package ir.example.androiddevegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonSum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }
    public void addListenerOnButton() {
        buttonSum = (Button) findViewById(R.id.button);

        buttonSum.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                EnterBTNonClick();
            }
        });
    }
    public void EnterBTNonClick(){
        //Toast.makeText(getApplicationContext(),"Hello world",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getBaseContext(), HotLineGame.class);
        startActivity(i);
    }
}