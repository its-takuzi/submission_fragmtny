package dicoding.bangkit.submission_fragmtny.ui.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dicoding.bangkit.submission_fragmtny.data.response.ResponsefollowersItem
import dicoding.bangkit.submission_fragmtny.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class follersingViewModel : ViewModel(){

    private val _detailUser = MutableLiveData<List<ResponsefollowersItem>>()
    val detailUser : LiveData<List<ResponsefollowersItem>> = _detailUser

    private val _detailFollower = MutableLiveData<List<ResponsefollowersItem>>()
    val detailFollower : LiveData<List<ResponsefollowersItem>> = _detailFollower

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        const val TAG = "DetailActivity"
    }

    fun getfollowing(username : String){
        _isLoading.value = true
        ApiConfig.getApiService()
            .getFollowing(username)
            .enqueue(object : Callback<List<ResponsefollowersItem>> {
                override fun onResponse(
                    call: Call<List<ResponsefollowersItem>>,
                    response: Response<List<ResponsefollowersItem>>
                ) {
                    if (response.isSuccessful){
                        _isLoading.value = (false)
                        val responseBody = response.body()
                        if (responseBody != null){
                            _detailUser.value = (responseBody)
                        }
                    }else{
                        Log.e(followingFragment.TAG,"error : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<ResponsefollowersItem>>, t: Throwable) {
                    _isLoading.value = (true)
                    Log.e(followingFragment.TAG,"onfailure ${t.message}")
                }

            } )
    }
    fun getListFollowing():LiveData<List<ResponsefollowersItem>>{
        return _detailUser
    }

    fun getfollowers(username : String){
        _isLoading.value = true
        ApiConfig.getApiService()
            .getFollowers(username)
            .enqueue(object : Callback<List<ResponsefollowersItem>> {
                override fun onResponse(
                    call: Call<List<ResponsefollowersItem>>,
                    response: Response<List<ResponsefollowersItem>>
                ) {
                    if (response.isSuccessful){
                        _isLoading.value = (false)
                        val responseBody = response.body()
                        if (responseBody != null){
                            _detailUser.value = (responseBody)
                        }
                    }else{
                        Log.e(followingFragment.TAG,"error : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<ResponsefollowersItem>>, t: Throwable) {
                    _isLoading.value = (true)
                    Log.e(followingFragment.TAG,"onfailure ${t.message}")
                }

            } )
    }
    fun getListFollowers():LiveData<List<ResponsefollowersItem>>{
        return _detailUser
    }
}