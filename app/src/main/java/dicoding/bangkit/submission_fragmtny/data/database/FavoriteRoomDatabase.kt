package dicoding.bangkit.submission_fragmtny.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class FavoriteRoomDatabase : RoomDatabase(){
    abstract fun FavoriteDao() : FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE : FavoriteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context) : FavoriteRoomDatabase? {
            if (INSTANCE == null){
                synchronized(FavoriteRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,FavoriteRoomDatabase::class.java, "favorite database")
                        .build()
                }
            }
            return INSTANCE
        }
    }

}