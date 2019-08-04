package fast.campus.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        Log.d("LifeCycle","onCreate");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("LifeCycle","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("LifeCycle","onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("LifeCycle","onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("LifeCycle","onResume");
        super.onResume();
    }
}
