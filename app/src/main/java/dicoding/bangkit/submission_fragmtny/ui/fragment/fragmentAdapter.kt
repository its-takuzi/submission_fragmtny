package dicoding.bangkit.submission_fragmtny.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import dicoding.bangkit.submission_fragmtny.data.response.ResponsefollowersItem
import dicoding.bangkit.submission_fragmtny.databinding.ItemAccountBinding
class fragmentAdapter : ListAdapter<ResponsefollowersItem, fragmentAdapter.MyViewHolder>(DIFF_CALLBACK) {


    private var userList: List<ResponsefollowersItem> = listOf()

    fun setData(data: List<ResponsefollowersItem>) {
        userList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAccountBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }
    class MyViewHolder(val binding : ItemAccountBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: ResponsefollowersItem){
            binding.nameAccount.text = user.login
            binding.ppAccount.load(user.avatarUrl){
                transformations(CircleCropTransformation())
            }
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResponsefollowersItem>(){
            override fun areItemsTheSame(
                oldItem: ResponsefollowersItem,
                newItem: ResponsefollowersItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ResponsefollowersItem,
                newItem: ResponsefollowersItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}