package com.example.pizzaapp3383

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptorMenu(private val listMenu: List<MenuModel>): RecyclerView.Adapter<AdaptorMenu.ViewHolder> () {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgGambar: ImageView
        val txtNama: TextView
        val txtHarga: TextView
        val context = view.context

        init {
            imgGambar = view.findViewById(R.id.imageViewMenu)
            txtNama = view.findViewById(R.id.textViewNamaMenu)
            txtHarga = view.findViewById(R.id.textViewHargaMenu)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdaptorMenu.ViewHolder {
        val layInflate = LayoutInflater.from(parent.context)
        val cellForRow = layInflate.inflate(R.layout.card_layout_menu, parent, false)
        return ViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: AdaptorMenu.ViewHolder, position: Int) {
        val model = listMenu[position]
        holder.imgGambar.setImageResource(model.gambar)
        holder.txtNama.text = model.nama
        holder.txtHarga.text = model.harga
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

}