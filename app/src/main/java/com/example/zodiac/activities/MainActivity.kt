package com.example.zodiac.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zodiac.HoroscopeAdapter
import com.example.zodiac.R
import com.example.zodiac.data.HoroscopeProvider

class MainActivity : AppCompatActivity() {

    var horoscopeList = HoroscopeProvider.getAll()

    lateinit var recyclerView: RecyclerView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        
        recyclerView=findViewById(R.id.recyclerView)
        val adapter= HoroscopeAdapter(horoscopeList,{ position ->
            val horoscope = horoscopeList[position]

          //  Log.i("CLICK","He hecho click en un horoscopo ${horoscope.id}")

            val intent = Intent(this,DetailActivity2::class.java)
            intent.putExtra("HOROSCOPE_ID", horoscope.id)
            startActivity(intent)
        })
        recyclerView.adapter= adapter
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu,menu)
        return true
    }

}