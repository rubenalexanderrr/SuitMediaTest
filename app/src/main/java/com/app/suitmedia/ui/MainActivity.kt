package com.app.suitmedia.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.app.suitmedia.R
import com.app.suitmedia.ui.SecondActivity.Companion.USER_NAME

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNext: Button = findViewById(R.id.btn_next)
        val etName: EditText = findViewById(R.id.name_edit_text)
        val etPalindrome: EditText = findViewById(R.id.palindrome_edit_text)
        val btnCheck: Button = findViewById(R.id.btn_check)

        btnCheck.setOnClickListener {
            val palindrome = etPalindrome.text.toString()
            if (isPalindrome(palindrome)) {
                showDialog(this, true)
            } else {
                showDialog(this, false)

            }
        }



        btnNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(USER_NAME, etName.text.toString())
            startActivity(intent)
            finish()
        }

    }

    private fun isPalindrome(str: String): Boolean {
        val stringWithoutSpaces = str.replace("\\s+".toRegex(), "")
        return stringWithoutSpaces.reversed() == stringWithoutSpaces
    }

    private fun showDialog(context: Context, isPalindrome: Boolean) {
        if (isPalindrome) {
            val message = getString(R.string.ispalindrome)
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.info))
            builder.setMessage(message)
            builder.setPositiveButton(getString(R.string.ok)) { _, _ ->
            }
            builder.show()
        } else {
            val message = getString(R.string.notpalindrome)
            val builder = AlertDialog.Builder(context)
            builder.setTitle(getString(R.string.info))
            builder.setMessage(message)
            builder.setPositiveButton(getString(R.string.ok)) { _, _ ->
            }
            builder.show()
        }


    }
}