package com.example.zodiac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.zodiac.data.Horoscope
import com.example.zodiac.utils.SessionManager

class HoroscopeAdapter(var items: List<Horoscope>, val onItemClick:(Int) ->Unit) : Adapter<HoroscopeViewHolder>() {

    // Cual es la vista de las celdas
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }

    // Cuantos elementos tengo que listar
    override fun getItemCount(): Int {
        return items.size
    }

    // Voy a mostrar la celda en la posicion indicada
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = items[position]
        holder.render(horoscope)
        holder.itemView.setOnClickListener {
            // navegar al detalle
            onItemClick(position)
        }
    }
    fun updateItems(items: List<Horoscope>){
        this.items= items
        notifyDataSetChanged()
    }

}

class HoroscopeViewHolder(view: View) : ViewHolder(view) {

    val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    val datesTextView: TextView = view.findViewById(R.id.datesTextView)
    val iconImageView: ImageView = view.findViewById(R.id.iconImageView)
    val favoriteImageView : ImageView = view.findViewById(R.id.favoriteImageView)
    fun render(horoscope: Horoscope) {
        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.dates)
        iconImageView.setImageResource(horoscope.icon)

        val session = SessionManager(itemView.context)
        if (session.getFavoriteHoroscope()==horoscope.id){
            favoriteImageView.visibility=View.VISIBLE
        }else{
            favoriteImageView.visibility=View.GONE
        }
    }
}