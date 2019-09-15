package weather.fastcampus.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_open_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpenWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_weather)

        setting.setOnClickListener {
            startActivity(Intent(this,AccountSettingActivity::class.java))
            requestCurrentWeather()
        }
    }

    private fun requestCurrentWeather(){

        (application as WeatherApplication)
            .requestService()
            ?.getWeatherInfoOfLocation("London","dbb8cf21ed5bc8e7ab088d2ff6f7661f")
            ?.enqueue(object : Callback<TotalWeather>{

                override fun onResponse(call: Call<TotalWeather>, response: Response<TotalWeather>) {
//                    Log.d("requestt", "response : " + response.body())
                    var totalWeather  = response.body()
                    Log.d("testt","main : " + totalWeather?.main?.temp)
                }

                override fun onFailure(call : Call<TotalWeather>, t: Throwable){
                }
            })
    }


//    private fun setupRetrofit(){
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.openweathermap.org/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(Service::class.java)
//
//        service.getWeatherInfoOfLocation("London","dbb8cf21ed5bc8e7ab088d2ff6f7661f")
//            .enqueue(object : Callback<JsonObject>{
//
//                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                    Log.d("abc", "response : " + response.body())
//                }
//
//                override fun onFailure(call : Call<JsonObject>, t: Throwable){
//                }
//            })
//    }
}
