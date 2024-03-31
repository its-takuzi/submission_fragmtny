package dicoding.bangkit.submission_fragmtny.ui.Fav

import androidx.recyclerview.widget.DiffUtil
import dicoding.bangkit.submission_fragmtny.data.database.Note

class NoteDiffCallback (private val oldFavoriteList: List<Note>, private val newFavoriteList: List<Note>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldFavoriteList.size
    override fun getNewListSize(): Int = newFavoriteList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFavoriteList[oldItemPosition].id == newFavoriteList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavorite = oldFavoriteList[oldItemPosition]
        val newFavorite = newFavoriteList[newItemPosition]
        return oldFavorite.login == newFavorite.login && oldFavorite.avatar_ulr == newFavorite.avatar_ulr
    }
}