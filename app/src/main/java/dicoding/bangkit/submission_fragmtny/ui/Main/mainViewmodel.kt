package dicoding.bangkit.submission_fragmtny.ui.Main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dicoding.bangkit.submission_fragmtny.data.response.ItemsItem
import dicoding.bangkit.submission_fragmtny.data.response.UserResponse
import dicoding.bangkit.submission_fragmtny.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class mainViewmodel : ViewModel() {

    private val _user = MutableLiveData<List<ItemsItem?>>()
    val user : LiveData<List<ItemsItem?>> = _user

    private val _isloading = MutableLiveData<Boolean>()
    val isloading : LiveData<Boolean> = _isloading

    companion object{
        private const val TAG = "mainviewmodel"
        private const val USERQUERY = "jasa"
    }
    init {
        listuser()
    }

    private fun listuser() {
        _isloading.value = true
        val client = ApiConfig.getApiService().getsearchuser(USERQUERY)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                _isloading.value = false
                if (response.isSuccessful) {
                    _user.value = response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isloading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun searchuser (query: String){
        val service = ApiConfig.getApiService()
        val call = service.getsearchuser(query)

        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful){
                    val userResponse = response.body()
                    val users = userResponse?.items
                    _user.value = users
                }else{
                    Log.e(TAG, "gagal mencari user")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, "Error mendapat hasil pencarian", t)
            }
        })

    }


}