package com.example.guess_rgb_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.data.entity.User
import kotlin.math.round

class StatisticsAdapter(var data: List<User>, context: Context) :
    RecyclerView.Adapter<StatisticsAdapter.StatisticsHolder>() {

    var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: StatisticsHolder, position: Int) {
        val user = data[position]
        holder.initViews()
        holder.setData(user)
        holder.setPlace(position + 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsHolder {
        val view = inflater.inflate(R.layout.item_user_statistics, parent, false)
        return StatisticsHolder(view)
    }

    class StatisticsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var nameTv: TextView
        private lateinit var winTv: TextView
        private lateinit var looseTv: TextView
        private lateinit var scorePb: ProgressBar
        private lateinit var placeTv: TextView

        fun initViews() {
            nameTv = itemView.findViewById(R.id.tv_user_name)
            winTv = itemView.findViewById(R.id.tv_user_win)
            looseTv = itemView.findViewById(R.id.tv_user_loose)
            scorePb = itemView.findViewById(R.id.pb_progress)
            placeTv = itemView.findViewById(R.id.tv_place)
        }

        fun setData(user: User) {
            val winText = "${user.win} (${user.winPercent}%)"
            val looseText = "${user.loose} (${user.loosePercent}%)"

            nameTv.text = user.email
            winTv.text = winText
            looseTv.text = looseText

            scorePb.progress = round(user.winPercent.toFloat()).toInt()
        }

        fun setPlace(place: Int) {
            placeTv.text = place.toString()
        }
    }
}