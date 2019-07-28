package fast.campus.stack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("AGAIN",false);
        startActivity(intent);

        // Stack

        // Main
        // Thrid
        // Second
        // Main
    }
}
