package fast.campus.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this; // Activity의 context
        Context context2 = MainActivity.this;

        Context applicationContext = getApplicationContext(); // Application의 context

//        startActivity();
//        startActivityForResult();
//        Toast.makeText(this, )
    }
}
