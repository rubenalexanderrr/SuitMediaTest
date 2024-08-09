package com.app.suitmedia.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.suitmedia.R
import com.app.suitmedia.adapter.LoadingStateAdapter
import com.app.suitmedia.adapter.UserAdapter
import com.app.suitmedia.databinding.ActivityThirdBinding
import com.app.suitmedia.paging.UserPagingSource
import com.app.suitmedia.response.DataItem
import com.app.suitmedia.retrofit.ApiConfig

class ThirdActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityThirdBinding
    private val viewModel: ViewModel by viewModels()

    private lateinit var selectedString: String
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        username = intent.getStringExtra(SecondActivity.USER_NAME) ?: ""

        binding.toolbarTitle.text = getString(R.string.third_screen)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        adapter = UserAdapter { user ->
            viewModel.setSelectedUser(user)
        }
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        getListStory().observe(this) {
            adapter.submitData(lifecycle, it)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.selectedUser.observe(this) { user ->
            selectedString = user
        }

    }


    private fun getListStory(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UserPagingSource(ApiConfig.getApiService())
            }
        ).liveData
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.DATA_KEY, selectedString)
        intent.putExtra(SecondActivity.USER_NAME, username)
        startActivity(intent)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.DATA_KEY, selectedString)
        intent.putExtra(SecondActivity.USER_NAME, username)
        startActivity(intent)
        finish()

    }

}