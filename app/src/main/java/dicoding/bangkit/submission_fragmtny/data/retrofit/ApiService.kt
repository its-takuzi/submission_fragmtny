package dicoding.bangkit.submission_fragmtny.data.retrofit
import dicoding.bangkit.submission_fragmtny.data.response.DetailUserResponse
import dicoding.bangkit.submission_fragmtny.data.response.ResponsefollowersItem
import dicoding.bangkit.submission_fragmtny.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*
interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getsearchuser(
       @Query("q") query:String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @Headers("Authorization: token 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")
    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ResponsefollowersItem>>

    @Headers("Authorization: token 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")
    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ResponsefollowersItem>>
}
