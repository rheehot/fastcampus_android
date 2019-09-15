package weather.fastcampus.myapplication

import java.io.Serializable

class TotalWeather (
    var main : Main? = null,
    var weatherList : ArrayList<Weather>? = null
    ) : Serializable

class Weather(
    var description : String? = null,
    var icon : String? = null,
    var main : String? = null
) : Serializable

class Main(
    var humidity : Int? = null,
    var pressure : Int? = null,
    var temp : Float? = null,
    var temp_max : Float? = null,
    var temp_min : Float? = null

)  : Serializable