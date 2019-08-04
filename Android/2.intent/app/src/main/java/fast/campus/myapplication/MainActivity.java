package fast.campus.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("lifecycle","0: onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //명시적 intent
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("INTENT_KEY",5);
        intent.putExtra("INTENT_KEY_STRING","STRING");
//        startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE);

        //암시적 intent
//          Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https:google.com"));
//          startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (requestCode == 200){
                String result = data.getStringExtra("RESULT");
                Log.d("onActivityResult","result :  " + result);
            }else if (requestCode ==300){
                Log.d("onActivityResult","실패");
            }
        }
    }

    @Override
    protected void onStart() {
        Log.d("lifecycle","0: onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("lifecycle","0: onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("lifecycle","0: onPause");
        super.onPause();
    }


    @Override
    protected void onStop() {
        Log.d("lifecycle","0: onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("lifecycle","onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("lifecycle","0: onRestart");
        super.onRestart();
    }

}
