package fast.campus.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml의  뷰 컴포넌트를 자바로 변경하는 방법
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        //xml의  뷰 컴포넌트를 동적으로 변경하는 방법
        imageView.setImageResource(R.drawable.dog2);
        textView.setText("안녕하세요");

        //xml의  뷰 컴포넌트를 string으로 변경하는 방법
        String content = getApplicationContext().getResources().getString(R.string.long_text);
        textView.setText(content);

        getApplicationContext().getResources().getStringArray(R.array.string_array);
        Log.d("String_array",getApplicationContext().getResources().getStringArray(R.array.string_array)[1]);
        //
    }
}
