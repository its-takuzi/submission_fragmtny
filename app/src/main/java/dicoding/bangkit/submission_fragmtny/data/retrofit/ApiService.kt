package dicoding.bangkit.submission_fragmtny.data.retrofit
import dicoding.bangkit.submission_fragmtny.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*
interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getsearchuser(
       @Query("q") query:String
    ): Call<UserResponse>
}