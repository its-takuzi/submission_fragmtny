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
