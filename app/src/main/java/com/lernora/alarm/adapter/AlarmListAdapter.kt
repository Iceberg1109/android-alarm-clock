package com.lernora.alarm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.lernora.alarm.R
import java.util.ArrayList

class AlarmListAdapter(private val context: Context?, private val alarmArray: ArrayList<AlarmModel>) : BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return alarmArray.size
    }

    override fun getItem(position: Int): Any {
        return alarmArray[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                ?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.alarm_list_item, null, true)

            holder.alarm_time = convertView!!.findViewById(R.id.time) as TextView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.alarm_time!!.setText(alarmArray[position].getTime())

        return convertView
    }

    private inner class ViewHolder {

        var alarm_time: TextView? = null

    }

}