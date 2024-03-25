package dicoding.bangkit.submission_fragmtny.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dicoding.bangkit.submission_fragmtny.data.response.ItemsItem
import dicoding.bangkit.submission_fragmtny.databinding.ItemAccountBinding

class UserAdapter : ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK){
    private val list = ArrayList<ItemsItem>()
    class MyViewHolder (val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(account : ItemsItem){
            binding.apply {
               Glide.with(itemView)
                   .load(account.avatarUrl)
                   .transition(DrawableTransitionOptions.withCrossFade())
                   .centerCrop()
                   .into(ppAccount)
                nameAccount.text = account.login
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val account = getItem(position)
        holder.bind(account)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ItemsItem> = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}