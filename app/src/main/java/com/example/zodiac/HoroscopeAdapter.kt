package com.example.zodiac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.zodiac.data.Horoscope

class HoroscopeAdapter(val items:List<Horoscope>) : Adapter<HoroscopeViewHolder>(){
    //lo que muestro
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope,parent, false)
        return HoroscopeViewHolder(view)
        }
    //Cuantos elementos listo
    override fun getItemCount(): Int {
        return items.size
    }
    //Voy a mostrar la celda en la posicion indicada
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        TODO("Not yet implemented")
        val horoscope=items[position]
        holder.render(horoscope)
    }

}
class HoroscopeViewHolder(view:View): ViewHolder(view){
    val nameTextView: TextView=view.findViewById(R.id.nameTextView)
    val datesTextView: TextView=view.findViewById(R.id.datesTextView)
    val iconImageView: ImageView=view.findViewById(R.id.iconImageView)


    fun render(horoscope: Horoscope){
        nameTextView.setText(horoscope.name)
        datesTextView.setText(horoscope.dates)
        iconImageView.setImageResource(horoscope.icon)
    }
}