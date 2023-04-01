package za.ac.iie.opsc.geoweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import za.ac.iie.opsc.geoweather.model.currentweather.CurrentWeather
import za.ac.iie.opsc.geoweather.retrofit.RetrofitClient

class CurrentWeatherModel : ViewModel() {

    private var _currentWeather = MutableLiveData<CurrentWeather>()
    var currentWeather: LiveData<CurrentWeather> = _currentWeather

    fun getCurrentWeather(locationKey: String) {
        viewModelScope.launch {
            val weatherData = RetrofitClient.weatherService?.
                    getCurrentConditions(locationKey,
                        BuildConfig.ACCUWEATHER_API_KEY)
            _currentWeather.value = weatherData?.get(0)
        }
    }
}