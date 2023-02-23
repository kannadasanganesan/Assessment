package com.example.searchindictionary.ui.src.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchindictionary.R
import com.example.searchindictionary.databinding.ActivityMainBinding
import com.example.searchindictionary.ui.src.ui.adapter.UserListAdapter
import com.example.searchindictionary.ui.src.viewmodel.SearchViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: SearchViewModel
    private lateinit var userAdapter: UserListAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        init()
    }

    private fun doSearch() {
        binding.searchView.isSubmitButtonEnabled = true
        binding.searchView.queryHint = "search..."

        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length!! >= 3) {
                    //calling search list api
                    mainViewModel.getResult(binding.searchView.query.toString())
                    registerObservers()
                    binding.searchProgress.visibility = View.VISIBLE
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.three_char_minimum),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun init() {
        userAdapter = UserListAdapter()
        binding.searchList.adapter = null
        binding.searchList.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
        }
        if (isNetworkAvailable()) doSearch() else Toast.makeText(
                this@MainActivity,
                getString(R.string.network_error),
                Toast.LENGTH_SHORT
            ).show()

    }

    private fun registerObservers() {

        mainViewModel.usersSuccessLiveData.observe(this, Observer { userList ->

            //if it is not null then we will display all users
            userList?.let {
                if(userList.isNotEmpty()) {
                    userAdapter.setResult(it,it.get(0).lfs)
                    binding.searchProgress.visibility = View.GONE
                }else{
                    binding.searchProgress.visibility = View.GONE
                    Toast.makeText(this, getString(R.string.no_result_found), Toast.LENGTH_SHORT).show()
                }
            }
        })

        mainViewModel.usersFailureLiveData.observe(this, Observer { isFailed ->

            //when exception occurs
            isFailed?.let {
                binding.searchProgress.visibility = View.GONE
                Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        })

    }
    private fun isNetworkAvailable(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

}