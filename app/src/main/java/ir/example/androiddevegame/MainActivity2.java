package ir.example.androiddevegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private Button increasbutton;
    private TextView textView;
    public int EXACTnumber = 0;

    private ImageView packmanimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        increasbutton = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.ExactnumberText) ;
        packmanimage = (ImageView)findViewById(R.id.packman) ;

        addListenerOnButton();
    }
    public void addListenerOnButton() {

        increasbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                IncreasBTNonClick();
            }
        });
    }
    public void IncreasBTNonClick(){
        EXACTnumber++;
        textView.setText(Integer.toString(EXACTnumber));
        packmanimage.setY(packmanimage.getY()+EXACTnumber);
        packmanimage.setRotation(packmanimage.getRotation() + EXACTnumber);
    }
}
