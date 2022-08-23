package com.app.testecred.util

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.testecred.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.description_activity.*


class Description : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_activity)

        val extras = intent.extras
        if (extras != null) {
            Glide.with(this)
                .load(extras.get("image"))
                .placeholder(R.drawable.ic_launcher_background)
                .into(iv_description)
            tv_title.text = extras.get("title").toString()
            tv_description.text = extras.get("description").toString()
            ScrollingMovementMethod().also { tv_description.movementMethod = it }

        }

        btn_checkin.setOnClickListener(){
            withEditText()
        }

        ib_back.setOnClickListener(){
            onBackPressed()
        }

        ib_share.setOnClickListener { view ->
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey Check out this Great app:"
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }

    }

    fun withEditText() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("With EditText")
        val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_edittext, null)
        val edName  = dialogLayout.findViewById<EditText>(R.id.et_name)
        val edEmail  = dialogLayout.findViewById<EditText>(R.id.et_email)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Confirm") { dialogInterface, i ->
            if (!edName.text.isEmpty() && !edEmail.text.isEmpty()){
            }else{
            Toast.makeText(this, "It is necessary to fill in all the fields.", Toast.LENGTH_SHORT).show()
            }
        }
        builder.show()
    }

}