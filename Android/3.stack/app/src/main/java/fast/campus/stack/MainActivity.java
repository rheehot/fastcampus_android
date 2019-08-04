package fast.campus.stack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import stack.stack.stack.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent commingIntent = getIntent();
        boolean again = commingIntent.getBooleanExtra("AGAIN", true);

        if(again ==true){
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }

    }


}
