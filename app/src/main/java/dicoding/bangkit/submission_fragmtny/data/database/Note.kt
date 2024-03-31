package dicoding.bangkit.submission_fragmtny.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Note(
    val login: String?,
    @PrimaryKey
    val id: Int,
    val avatar_ulr : String?
) :Parcelable