package dicoding.bangkit.submission_fragmtny.ui.Main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import dicoding.bangkit.submission_fragmtny.data.response.ItemsItem
import dicoding.bangkit.submission_fragmtny.databinding.ItemAccountBinding

class UserAdapter : ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK){
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val list = ArrayList<ItemsItem>()

    
    inner class MyViewHolder (val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(account : ItemsItem){

            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(account)
            }
            binding.apply {
                binding.ppAccount.load(account.avatarUrl){
                    transformations(CircleCropTransformation())
            }
            nameAccount.text = account.login
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: List<ItemsItem>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: UserAdapter.MyViewHolder, position: Int) {
        val account = getItem(position)
        holder.bind(account)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsItem)
    }
    
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
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