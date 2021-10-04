package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)  }

    companion object {
        const val CHANNEL_COURSES = "CHANNEL_COURSES"

        const val ACTION_RECIVER = "ACTION_RECEIVER"

        val GRUPO_SIMPLE = "GRUPO_SIMPLE"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }

        binding.run{

            btnNotify.setOnClickListener {
                simpleNotification()
            }

            btnActionNotify.setOnClickListener {
                touchNotification()
            }

            btnNotifyWithBtn.setOnClickListener {
                actionNotification()
            }

            btnExpandable.setOnClickListener{
                simpleNotification()
            }

            btnExpandable.setOnClickListener{

            }

            btnCancelNoti.setOnClickListener{

            }



        }


        setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotificationChannel(){
        val name = getString(R.string.channel_courses) //Nombre del canal
        val descriptionText = getString(R.string.courses_description) //descriptcion de channel
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_COURSES,name,importance).apply {
            description =descriptionText
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)

    }
/*
    private fun simpleNotification(){
        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES )
            .setSmallIcon(R.drawable.triforce)
            .setColor(ContextCompat.getColor(this,R.color.triforce))
            .setContentTitle(getString(R.string.simple_title))
            .setContentText(getString(R.string.simple_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(this).run {
            notify(20,notification)
        }
    }
*/
    private fun simpleNotification(){
        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES )
            .setSmallIcon(R.drawable.triforce)
            .setColor(ContextCompat.getColor(this,R.color.triforce))
            .setContentTitle(getString(R.string.simple_title))
            .setContentText(getString(R.string.simple_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setGroup(GRUPO_SIMPLE)
            .build()

        val notification2 = NotificationCompat.Builder(this, CHANNEL_COURSES )
            .setSmallIcon(R.drawable.triforce)
            .setColor(ContextCompat.getColor(this,R.color.triforce))
            .setContentTitle(getString(R.string.simple_title_2))
            .setContentText(getString(R.string.simple_body_2))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setGroup(GRUPO_SIMPLE)
            .build()

        val notification3 = NotificationCompat.Builder(this, CHANNEL_COURSES )
            .setSmallIcon(R.drawable.triforce)
            .setColor(ContextCompat.getColor(this,R.color.triforce))
            .setContentTitle(getString(R.string.simple_title_3))
            .setContentText(getString(R.string.simple_body_3))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setGroup(GRUPO_SIMPLE)
            .build()

        val summaryNotification = NotificationCompat.Builder(this@MainActivity, CHANNEL_COURSES)
            .setSmallIcon(R.drawable.bedu_icon)
            .setGroup(GRUPO_SIMPLE)
            .setGroupSummary(true)
            .build()

        NotificationManagerCompat.from(this).run {
            notify(20,notification)
            notify(21,notification2)
            notify(22,notification3)
            notify(23,summaryNotification)


        }
    }

    private fun touchNotification(){
        val intent = Intent(this, BeduActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES )
            .setSmallIcon(R.drawable.bedu)
            .setColor(ContextCompat.getColor(this,R.color.triforce))
            .setContentTitle(getString(R.string.action_title))
            .setContentText(getString(R.string.action_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).run {
            notify(20, notification)
        }


    }

    private fun actionNotification(){

        val acceptIntent = Intent(this, NotificationReceiver::class.java).apply {
            action = ACTION_RECIVER
        }

        val accepPendingIntent = PendingIntent.getBroadcast(this, 0, acceptIntent, 0)


        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES )
            .setSmallIcon(R.drawable.bedu)
            .setColor(ContextCompat.getColor(this,R.color.triforce))
            .setContentTitle(getString(R.string.button_title))
            .setContentText(getString(R.string.button_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.bedu_icon, getString(R.string.button_text),accepPendingIntent)
            .build()

        NotificationManagerCompat.from(this).run {
            notify(20, notification)
        }
    }


}