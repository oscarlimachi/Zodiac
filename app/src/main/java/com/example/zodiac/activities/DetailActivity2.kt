package com.example.zodiac.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zodiac.R
import com.example.zodiac.data.Horoscope
import com.example.zodiac.data.HoroscopeProvider
import com.example.zodiac.utils.SessionManager

class DetailActivity2 : AppCompatActivity() {

    lateinit var nameTextView: TextView
    lateinit var dateTextView: TextView
    lateinit var iconImageView: ImageView


    lateinit var session: SessionManager
    lateinit var horoscope: Horoscope
    var isFavorite = false
    lateinit var favoriteMenuItem: MenuItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        session = SessionManager(this)

        nameTextView = findViewById(R.id.nameTextView)
        dateTextView = findViewById(R.id.datesTextView)
        iconImageView = findViewById(R.id.iconImageView)

        val id = intent.getStringExtra("HOROSCOPE_ID")!!
        horoscope = HoroscopeProvider.getById(id)!!

        isFavorite = session.getFavoriteHoroscope() == horoscope.id


        nameTextView.setText(horoscope.name)
        dateTextView.setText(horoscope.dates)
        iconImageView.setImageResource(horoscope.icon)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detail_menu, menu)

        favoriteMenuItem=menu.findItem(R.id.menu_favorite)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                if (isFavorite) {
                    session.setFavoriteHoroscope("")
                } else {
                    session.setFavoriteHoroscope(horoscope.id)
                }
                isFavorite = !isFavorite
                setFavoriteIcon()
                return true
            }

            R.id.menu_share -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }

        }
    }

        fun setFavoriteIcon(){
            if (isFavorite) {favoriteMenuItem.setIcon(R.drawable.ic_favorite_selected)}
            else {favoriteMenuItem.setIcon(R.drawable.ic_favorite)}
        }
    }


