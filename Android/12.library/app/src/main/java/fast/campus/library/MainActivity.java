package fast.campus.library;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewOne;
    ImageView imageViewTwo;
    ImageView imageViewThree;
    ImageView imageViewFour;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        imageViewOne = findViewById(R.id.image_one);
        imageViewTwo = findViewById(R.id.image_two);
        imageViewThree = findViewById(R.id.image_three);
        imageViewFour = findViewById(R.id.image_four);

        //웹 이미지 불러 표시하기
        Glide.with(context).load("https://image-notepet.akamaized.net/resize/620x-/seimage/20190222%2F88df4645d7d2a4d2ed42628d30cd83d0.jpg").into(imageViewOne);

        //option 사용
        RequestOptions cropOption = new RequestOptions().centerCrop();
        Glide.with(context).load("https://image-notepet.akamaized.net/resize/620x-/seimage/20190222%2F88df4645d7d2a4d2ed42628d30cd83d0.jpg").apply(cropOption).into(imageViewTwo);

          //option 사용
        RequestOptions circleCrop = new RequestOptions().circleCrop();
        Glide.with(context).load("https://image-notepet.akamaized.net/resize/620x-/seimage/20190222%2F88df4645d7d2a4d2ed42628d30cd83d0.jpg").apply(circleCrop).into(imageViewThree);

        //local image 불러오는 사용
          Glide.with(context).load(R.drawable.ic_launcher_background).apply(circleCrop).into(imageViewFour);

    }
}
