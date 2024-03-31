package dicoding.bangkit.submission_fragmtny.ui.Detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dicoding.bangkit.submission_fragmtny.data.database.FavoriteDao
import dicoding.bangkit.submission_fragmtny.data.database.FavoriteRoomDatabase
import dicoding.bangkit.submission_fragmtny.data.database.Note
import dicoding.bangkit.submission_fragmtny.data.response.DetailUserResponse
import dicoding.bangkit.submission_fragmtny.data.retrofit.ApiConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val user = MutableLiveData<DetailUserResponse>()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var favdao : FavoriteDao?
    private var favdb : FavoriteRoomDatabase?

    init {
        favdb = FavoriteRoomDatabase.getDatabase(application)
        favdao = favdb?.FavoriteDao()
    }

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

    fun addtofav(username: String?, id: Int, avatarulr: String?){
        CoroutineScope(Dispatchers.IO).launch {
            var user = Note(
                username,
                id,
                avatarulr
            )
            favdao?.addToFav(user)
    }
    }

    suspend fun checkuser(id:Int) = favdao?.checkuser(id)
    fun deletefav(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            favdao?.deletefromfav(id)
        }
    }
}