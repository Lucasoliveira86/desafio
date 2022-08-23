package com.app.testecred

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.testecred.adapter.EventsAdapter
import com.app.testecred.api.endpoint.EventsApi
import com.app.testecred.api.model.Events
import com.app.testecred.databinding.MainActivityBinding
import kotlinx.android.synthetic.main.main_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: MainActivityBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        refreshLayout.setOnRefreshListener {
            fetchEvents()
        }
        fetchEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null!!
    }

    private fun fetchEvents() {
        refreshLayout.isRefreshing = true
        EventsApi().getEvents().enqueue(object : Callback<List<Events>> {
            override fun onFailure(call: Call<List<Events>>, t: Throwable) {
                refreshLayout.isRefreshing = false
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Events>>, response: Response<List<Events>>) {
                refreshLayout.isRefreshing = false
                val events = response.body()
                events?.let {
                    showEvents(it)
                }
            }
        })
    }

    private fun showEvents(events: List<Events>) {
        recyclerViewEvents.layoutManager = LinearLayoutManager(this)
        recyclerViewEvents.adapter = EventsAdapter(events)
    }
}
