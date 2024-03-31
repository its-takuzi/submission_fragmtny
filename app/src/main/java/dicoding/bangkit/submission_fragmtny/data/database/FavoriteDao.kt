package dicoding.bangkit.submission_fragmtny.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    suspend fun addToFav(Note : Note)

    @Query("SELECT * FROM Note")
    fun getfavuser(): LiveData<List<Note>>

    @Query("SELECT count(*) FROM Note WHERE Note.id = :id" )
    suspend fun checkuser(id : Int): Int

    @Query( "DELETE FROM Note WHERE Note.id = :id")
    suspend fun deletefromfav(id: Int): Int
}