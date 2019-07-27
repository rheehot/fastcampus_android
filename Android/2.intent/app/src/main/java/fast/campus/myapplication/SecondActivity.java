package fast.campus.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lifecycle","1: onCreate");
        setContentView(R.layout.activity_second);

//        Intent intent = getIntent();
//        int data = intent.getIntExtra("INTENT_KEY", 0);
//        String stringData = intent.getStringExtra("INTENT_KEY_STRING");
//        Log.d("intent_key","" + data);
//        Log.d("intent_key","" + stringData);

        //작업을 마친 후 ...
        Intent intent = new Intent();
        intent.putExtra("RESULT","성공");
        setResult(200, intent);
        finish(); //스스로 종료 main activity로 전환
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
