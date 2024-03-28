package dicoding.bangkit.submission_fragmtny.ui.Detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dicoding.bangkit.submission_fragmtny.data.response.DetailUserResponse
import dicoding.bangkit.submission_fragmtny.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    val user = MutableLiveData<DetailUserResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        const val TAG = "DetailActivity"
    }
    fun finduserDetail(username : String){
        _isLoading.value = true
        ApiConfig.getApiService()
            .getDetailUser(username)
            .enqueue(object :Callback<DetailUserResponse>{
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful){
                        _isLoading.value = false
                        user.postValue(response.body())
                    }else{
                        Log.e(TAG,"error : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.e(TAG,"salah : ${t.message}")
                }

            })
    }

    fun getUserDetail():LiveData<DetailUserResponse>{
        return user
    }
}