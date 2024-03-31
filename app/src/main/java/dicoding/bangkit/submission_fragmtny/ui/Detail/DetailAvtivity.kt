package dicoding.bangkit.submission_fragmtny.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import dicoding.bangkit.submission_fragmtny.databinding.ActivityDetailAvtivityBinding
import coil.load
import coil.transform.CircleCropTransformation
import dicoding.bangkit.submission_fragmtny.R
import dicoding.bangkit.submission_fragmtny.data.database.Note
import dicoding.bangkit.submission_fragmtny.ui.fragment.sectionpageadapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class DetailAvtivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAvtivityBinding
    private lateinit var detailmodel: DetailViewModel
    companion object {
        const val USER_NAME = "user_name"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVA = "extra_avatar"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
    private val isExist = false
    private var favorite : Note? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAvtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(USER_NAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatar = intent.getStringExtra(EXTRA_AVA)
        val bundle = Bundle()
        bundle.putString(USER_NAME, username)

        detailmodel = ViewModelProvider(this).get(DetailViewModel::class.java)

        detailmodel.finduserDetail(username.toString())

        detailmodel.isLoading.observe(this) {
            showLoading(it)
        }
        detailmodel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    usernameDetail.text = it.name
                    bioDetail.text = it.login
                    biooo.text = it.bio.toString()
                    jumlahFollowers.text = it.followers.toString()
                    jumlahFollowing.text = it.following.toString()
                    binding.detilePp.load(it.avatarUrl) {
                        transformations(CircleCropTransformation())
                    }
                }
            }
        }
        val sectionsPagerAdapter = sectionpageadapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }

        var _check = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = detailmodel.checkuser(id)
            withContext(Dispatchers.Main){
                if (count != null){
                    if (count>0){
                        binding.fabb.isChecked = true
                        _check = true
                    }else{
                        binding.fabb.isChecked = false
                        _check = false
                    }
                }
            }
        }

        binding.fabb.setOnClickListener{
            _check = !_check
            if (_check){
                detailmodel.addtofav(username, id, avatar)
            }else{
                detailmodel.deletefav(id)
            }
            binding.fabb.isChecked = _check
        }
    }

    private fun showLoading(isLoading : Boolean){
        if (isLoading == true){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}

