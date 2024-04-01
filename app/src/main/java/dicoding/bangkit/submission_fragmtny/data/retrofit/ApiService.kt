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

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(@Path("username") username: String): Call<List<ResponsefollowersItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(@Path("username") username: String): Call<List<ResponsefollowersItem>>
}
