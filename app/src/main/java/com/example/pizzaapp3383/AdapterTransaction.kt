package com.example.pizzaapp3383

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// core - main
// connect card_layout & adapter, hitung jumlah data, looping berapa kali (sesuai jumlah data)
class AdapterTransaction(private val listOrder: List<TransactionModel>): RecyclerView.Adapter<AdapterTransaction.ViewHolderOrder>() {
    class ViewHolderOrder(view: View): RecyclerView.ViewHolder(view) {
        // connected adapter & card_layout
        val txtNama: TextView
        val txtHarga: TextView
        val txtJumlah: TextView
        val imgGambar: ImageView
        val context = view.context
        init {
            txtNama = view.findViewById(R.id.textNamaMenu)
            txtHarga = view.findViewById(R.id.textHargaMenu)
            txtJumlah = view.findViewById(R.id.textQuantityMenu)
            imgGambar = view.findViewById(R.id.imageMenu)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterTransaction.ViewHolderOrder {
        val li = LayoutInflater.from(parent.context)
        val cfr = li.inflate(R.layout.card_layout_order, parent, false)

        return ViewHolderOrder(cfr)
    }

    override fun onBindViewHolder(holder: AdapterTransaction.ViewHolderOrder, position: Int) {
        val modelTrx = listOrder[position]
        holder.txtNama.text = modelTrx.nama
        holder.txtHarga.text = modelTrx.harga
        holder.txtJumlah.text = modelTrx.jumlah
        holder.imgGambar.setImageResource(modelTrx.gambar)
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }

}