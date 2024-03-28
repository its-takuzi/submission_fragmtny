package dicoding.bangkit.submission_fragmtny.ui.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dicoding.bangkit.submission_fragmtny.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProvider
import dicoding.bangkit.submission_fragmtny.data.response.ItemsItem
import dicoding.bangkit.submission_fragmtny.ui.Detail.DetailAvtivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewmodel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            mainViewmodel::class.java)
        val layoutManager = LinearLayoutManager(this)
        binding.listAccount.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listAccount.addItemDecoration(itemDecoration)

        supportActionBar?.hide()
        mainViewmodel.user.observe(this){ItemsItem ->
            setUserdata(ItemsItem)
        }
        mainViewmodel.isloading.observe(this) {
            showLoading(it)
        }

        binding.rvUser.apply {
            requestFocus()
                setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                      androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (!query.isNullOrEmpty()){
                            mainViewmodel.searchuser(query)
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }

                })
        }
    }

    private fun setUserdata(item: List<ItemsItem?>) {
        val adapter = UserAdapter()
        adapter.submitList(item)
        binding.listAccount.adapter = adapter
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemsItem) {
                Intent(this@MainActivity, DetailAvtivity::class.java).also {
                    it.putExtra(DetailAvtivity.USER_NAME, data.login)
                    startActivity(it)
                }
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
