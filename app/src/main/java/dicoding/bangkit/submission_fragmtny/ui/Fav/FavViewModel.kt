package dicoding.bangkit.submission_fragmtny.ui.Fav

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import dicoding.bangkit.submission_fragmtny.data.database.FavoriteDao
import dicoding.bangkit.submission_fragmtny.data.database.FavoriteRoomDatabase
import dicoding.bangkit.submission_fragmtny.data.database.Note

class FavViewModel(application: Application) : AndroidViewModel(application) {
    private var favdao : FavoriteDao?
    private var favdb : FavoriteRoomDatabase?

    init {
        favdb = FavoriteRoomDatabase.getDatabase(application)
        favdao = favdb?.FavoriteDao()
    }

    fun getfavuser(): LiveData<List<Note>>?{
        return favdao?.getfavuser()
    }
}