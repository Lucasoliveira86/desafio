package com.app.testecred.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.testecred.R
import com.app.testecred.api.model.Events
import com.app.testecred.util.Description
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_events.view.*


class EventsAdapter (val events : List<Events>) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_events, parent, false)
        )
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val events = events[position]
        holder.view.textViewTitle.text = events.title

        holder.view.btn_description.setOnClickListener() { v ->
            val i = Intent(v.context, Description::class.java)
            i.putExtra("image", events.image)
            i.putExtra("title", events.title)
            i.putExtra("description", events.description)
            v.context.startActivity(i)
        }

        Glide.with(holder.view.context)
            .load(events.image)
            .into(holder.view.imageView)
    }

    class EventsViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}

