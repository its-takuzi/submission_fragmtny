package dicoding.bangkit.submission_fragmtny.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dicoding.bangkit.submission_fragmtny.R
import dicoding.bangkit.submission_fragmtny.databinding.FragmentFollowingBinding
import dicoding.bangkit.submission_fragmtny.ui.Detail.DetailAvtivity

class followingFragment : Fragment(R.layout.fragment_following) {

    private var _binding : FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: follersingViewModel
    private lateinit var adapter : fragmentAdapter
    private lateinit var username : String


    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
        const val TAG = "DetailActivity"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailAvtivity.USER_NAME).toString()

        _binding = FragmentFollowingBinding.bind(view)

        adapter = fragmentAdapter()
        adapter.notifyDataSetChanged()
        binding.apply {
          rvuser.setHasFixedSize(true)
            rvuser.layoutManager = LinearLayoutManager(activity)
            rvuser.adapter = adapter
        }
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(follersingViewModel::class.java)
        viewModel.getfollowing(username)
        viewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        viewModel.getListFollowing().observe(viewLifecycleOwner){
            if (it != null){
                val adapter = fragmentAdapter()
                adapter.submitList(it)
                binding.rvuser.adapter = adapter
            }
        }
        viewModel.getListFollowers().observe(viewLifecycleOwner){
            if (it != null){
                val adapter = fragmentAdapter()
                adapter.submitList(it)
                binding.rvuser.adapter = adapter
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(isLoading : Boolean){
        if (isLoading == true){
            _binding?.progressBar2?.visibility = View.VISIBLE
        }else{
            _binding?.progressBar2?.visibility = View.GONE
        }
    }
}
