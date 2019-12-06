package com.example.guess_rgb_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.data.entity.User

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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsHolder {
        val view = inflater.inflate(R.layout.item_user_statistics, parent, false)
        return StatisticsHolder(view)
    }

    class StatisticsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var nameTv: TextView
        lateinit var winTv: TextView
        lateinit var looseTv: TextView

        fun initViews() {
            nameTv = itemView.findViewById(R.id.tv_user_name)
            winTv = itemView.findViewById(R.id.tv_user_win)
            looseTv = itemView.findViewById(R.id.tv_user_loose)
        }

        fun setData(user: User) {
            nameTv.text = user.email
            winTv.text = user.win.toString()
            looseTv.text = user.loose.toString()
        }
    }
}