package dicoding.bangkit.submission_fragmtny.ui.Fav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dicoding.bangkit.submission_fragmtny.data.database.Note
import dicoding.bangkit.submission_fragmtny.data.response.ItemsItem
import dicoding.bangkit.submission_fragmtny.databinding.ActivityFavBinding
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

/*        favViewModel.getfavuser()?.observe(this) { users ->
            val items = arrayListOf<ItemsItem>()
            users.map {
                val item = ItemsItem(login = it.login, avatarUrl = it.avatar_ulr)
                items.add(item)
            }
            adapter.setListNotes(users)
        }*/
       /* favViewModel.getfavuser()?.observe(this) { users ->
            val items = arrayListOf<ItemsItem>()
            users.map {
                val item = ItemsItem(login = it.login, avatarUrl = it.avatar_ulr)
                items.add(item)
            }
            adapter.setListNotes(users)
        }*/

        binding.apply {
            rvFav.setHasFixedSize(true)
            rvFav.layoutManager = LinearLayoutManager(this@FavActivity)
            rvFav.adapter = adapter
        }

        favViewModel.getfavuser()?.observe(this) {
            if (it != null) {
                val list = mapList(it)
                adapter.setList(list)
            }
        }
    }

    private fun mapList(itemsItem: List<Note>): ArrayList<ItemsItem> {
        val listfav = ArrayList<ItemsItem>()
        for ( items in itemsItem){
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