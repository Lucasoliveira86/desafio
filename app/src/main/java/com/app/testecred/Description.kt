package com.app.testecred

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.testecred.api.endpoint.EventsApi
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.description_activity.*
import okhttp3.FormBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Description : AppCompatActivity() {

    var id: Int = 0

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
            id = extras.getInt("id")
            ScrollingMovementMethod().also { tv_description.movementMethod = it }
        }

        btn_checkin.setOnClickListener() {
            editUserCheckIn()
        }

        ib_back.setOnClickListener() {
            onBackPressed()
        }

        ib_share.setOnClickListener { view ->
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                tv_title.text.toString() + "\n" + tv_description.text.toString()
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

    fun editUserCheckIn() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("With EditText")
        val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_edittext, null)
        val edName = dialogLayout.findViewById<EditText>(R.id.et_name)
        val edEmail = dialogLayout.findViewById<EditText>(R.id.et_email)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Confirm") { dialogInterface, i ->
            if (!edName.text.isEmpty() && !edEmail.text.isEmpty() && edEmail.text.contains("@")) {
                userCheckIn(edName.text.toString(), edEmail.text.toString())
            } else {
                Toast.makeText(
                    this,
                    "It is necessary to fill in all the fields.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        builder.setNegativeButton("cancel") { dialogInterface, i ->
        }
        builder.show()
    }

    fun userCheckIn(name: String, email: String) {
        val formBody = FormBody.Builder()
            .add("eventId", id.toString())
            .add("name", name)
            .add("email", email)
            .build()
        EventsApi().postCheckin(formBody).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 200 || response.code() == 201) {
                    Toast.makeText(this@Description, "Success", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        this@Description,
                        "Failure ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@Description, "Failure ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

}
