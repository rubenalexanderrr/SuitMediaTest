package com.app.suitmedia.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.app.suitmedia.R
import com.app.suitmedia.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val viewModel: ViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.selectedUser.observe(this) { user ->
            binding.tvSelectedName.text = user
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val titleTextView = binding.toolbarTitle
        val tvUsername = binding.tvUsername
        val userName = intent.getStringExtra(USER_NAME)
        val btnChooseUser = binding.btnChooseUser

        titleTextView.text = getString(R.string.second_screen)

        if (userName == "" || userName == null) {
            tvUsername.text = getString(R.string.user_name)
        } else {
            tvUsername.text = userName
        }

        btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra(USER_NAME, userName)
            startActivity(intent)
            finish()
        }

        val user = intent.getStringExtra(DATA_KEY)
        binding.tvSelectedName.text = user ?: getString((R.string.selected_username))

    }


    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val USER_NAME = "user_name"
        const val DATA_KEY = "data_key"

    }
}