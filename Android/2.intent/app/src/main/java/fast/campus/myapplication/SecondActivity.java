package fast.campus.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lifecycle","1: onCreate");
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onStart() {
        Log.d("lifecycle","1: onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("lifecycle","1: onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("lifecycle","1: onPause");
        super.onPause();
    }


    @Override
    protected void onStop() {
        Log.d("lifecycle","1: onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("lifecycle","1: onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("lifecycle","1: onRestart");
        super.onRestart();
    }
}
