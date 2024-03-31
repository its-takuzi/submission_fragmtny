package dicoding.bangkit.submission_fragmtny.ui.Fav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dicoding.bangkit.submission_fragmtny.data.database.Note
import dicoding.bangkit.submission_fragmtny.data.response.ItemsItem
import dicoding.bangkit.submission_fragmtny.databinding.ActivityFavBinding
import dicoding.bangkit.submission_fragmtny.ui.Detail.DetailAvtivity
import dicoding.bangkit.submission_fragmtny.ui.Main.UserAdapter

class FavActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavBinding
    private lateinit var favViewModel: FavViewModel
    private lateinit var adapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favViewModel = ViewModelProvider(this).get(FavViewModel::class.java)

        adapter = UserAdapter()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemsItem) {
                Intent(this@FavActivity, DetailAvtivity::class.java).also {
                    it.putExtra(DetailAvtivity.USER_NAME, data.login)
                    it.putExtra(DetailAvtivity.EXTRA_ID, data.id)
                    it.putExtra(DetailAvtivity.EXTRA_AVA, data.avatarUrl)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvFav.setHasFixedSize(true)
            rvFav.layoutManager = LinearLayoutManager(this@FavActivity)
            rvFav.adapter = adapter
        }

        favViewModel.getfavuser()?.observe(this) {
            if (it != null) {
                val list = mapList(it)
                Log.d("favorite activity", "onCreate : ${list.size}")
                adapter.submitList(list)

            }
        }
    }

    private fun mapList(users: List<Note>): ArrayList<ItemsItem> {
        val listfav = ArrayList<ItemsItem>()
        for ( items in users){
            val userMapped = ItemsItem(
                items.login,
                items.id.toString(),
                items.avatar_ulr
            )
        listfav.add(userMapped)
        }
        return listfav
    }
}