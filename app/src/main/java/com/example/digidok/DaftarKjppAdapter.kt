package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class DaftarKjppAdapter(private val context: Context, val daftarKjppViewModel: DaftarKjppViewModel, private var mListener:onItemClickListener,val listener: (DaftarKjppModel) -> Unit)
    : RecyclerView.Adapter<DaftarKjppAdapter.DaftarKJPPViewHolder>(){

    private var DaftarKJPP: List<DaftarKjppModel> = daftarKjppViewModel.mData

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    class DaftarKJPPViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val no_kjpp = view.findViewById<TextView>(R.id.no_kjpp)
        val nama_kjpp = view.findViewById<TextView>(R.id.nama_kjpp)
        val telp_kjpp = view.findViewById<TextView>(R.id.telp_kjpp)

        val no_perizinan = view.findViewById<TextView>(R.id.no_perizinan)

        val tgl_perizinan = view.findViewById<TextView>(R.id.tgl_perizinan)

        val klasifikasi_perizinan = view.findViewById<TextView>(R.id.klasifikasi_perizinan)


        val dropdown = view.findViewById<ImageView>(R.id.kjppDropdown)

        fun bindView(daftarKjppModel: DaftarKjppModel, listener: (DaftarKjppModel) -> Unit){
            no_kjpp.text = daftarKjppModel.no_kjpp
            nama_kjpp.text = daftarKjppModel.nama_kjpp
            no_perizinan.text = daftarKjppModel.no_perizinan
            tgl_perizinan.text = daftarKjppModel.tgl_perizinan
            klasifikasi_perizinan.text = daftarKjppModel.klasifikasi_perizinan

            telp_kjpp.text = daftarKjppModel.telp_kjpp
        }

        init {
            dropdown.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarKJPPViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_daftar_kjpp, parent, false)

        return DaftarKJPPViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: DaftarKJPPViewHolder, position: Int) {
        holder.bindView(DaftarKJPP[position], listener)
    }

    override fun getItemCount(): Int {
        return DaftarKJPP.size
    }

}