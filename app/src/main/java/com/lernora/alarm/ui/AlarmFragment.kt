package com.lernora.alarm.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.AlarmManagerCompat
import com.lernora.alarm.AlarmReceiver

import com.lernora.alarm.R
import com.lernora.alarm.adapter.AlarmListAdapter
import com.lernora.alarm.adapter.AlarmModel
import kotlinx.android.synthetic.main.fragment_alarm.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AlarmFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AlarmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlarmFragment : Fragment() {

    private val alarmModelArrayList: ArrayList<AlarmModel> = arrayListOf()
    private lateinit var alarmListAdapter: AlarmListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_alarm, container, false)

        alarmListAdapter = AlarmListAdapter(context,alarmModelArrayList)
        view.listView.adapter = alarmListAdapter

        view.addAlarm.setOnClickListener(addAlarm)

        return view

    }

    private val addAlarm = View.OnClickListener {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)
            val alarmModel = AlarmModel()
            alarmModel.setTime(SimpleDateFormat("HH:mm").format(cal.time))
            alarmModelArrayList?.add(alarmModel)
            alarmListAdapter.notifyDataSetChanged()

            Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show()

            val alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(context, 0, intent, 0)
            }
            val info = AlarmManager.AlarmClockInfo(cal.timeInMillis, alarmIntent)

            alarmMgr?.setAlarmClock(
                info,
                alarmIntent
            )
        }
        TimePickerDialog(context,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE), true).show()


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AlarmFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlarmFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
