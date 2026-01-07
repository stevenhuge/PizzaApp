package com.example.pizzaapp3383

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaapp3383.response.food.FoodResponse
import com.squareup.picasso.Picasso

class AdaptorMenu(private val listMenu: ArrayList<FoodResponse>): RecyclerView.Adapter<AdaptorMenu.ViewHolder> () {

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val imgPhotoMenu: ImageView = v.findViewById(R.id.imageViewMenu)
        val textNameMenu: TextView = v.findViewById(R.id.textViewNamaMenu)
        val textPriceMenu: TextView = v.findViewById(R.id.textViewHargaMenu)
        val context: Context? = v.context

        fun bind(response: FoodResponse) {
            val name = "${response.food_name}"
            val price = "${response.price}"
            val picture = "${response.food_picture}"

            textNameMenu.text = name
            textPriceMenu.text = price
            val url = "http://192.168.100.193/rest_api3383/gambar/" + picture
            Picasso.get().load(url).into(imgPhotoMenu)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layInflate = LayoutInflater.from(parent.context)
        val cellForRow = layInflate.inflate(R.layout.card_layout_menu, parent, false)
        return ViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMenu[position])
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

}