package dicoding.bangkit.submission_fragmtny.ui.Fav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import dicoding.bangkit.submission_fragmtny.data.database.Note
import dicoding.bangkit.submission_fragmtny.data.response.ItemsItem
import dicoding.bangkit.submission_fragmtny.databinding.ItemFavoriteBinding
import dicoding.bangkit.submission_fragmtny.ui.Main.UserAdapter

class favAdapter : RecyclerView.Adapter<favAdapter.FavoriteViewHolder>() {
    private val listFavorite = ArrayList<Note>()
    private lateinit var onItemClickCallback: UserAdapter.OnItemClickCallback

    fun setListNotes(listNotes: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listFavorite, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavorite.clear()
        this.listFavorite.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int {
        return listFavorite.size
    }


    inner class FavoriteViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Note) {
            with(binding) {
                user.text = favorite.login
                imageUser.load(favorite.avatar_ulr) {
                    transformations(CircleCropTransformation())
                }
                }
            }
        }
    }

    //    supaya click bisa di akses dari activity
    interface OnDeleteClickListener {
        fun onDeleteClick(favorite: Note)
    }
