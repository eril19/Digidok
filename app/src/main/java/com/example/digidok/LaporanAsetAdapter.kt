package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class LaporanAsetAdapter(private val context: Context, private val LaporanAset: List<LaporanAsetModel>, private var mListener: onItemClickListener,
                         val listener: (LaporanAsetModel) -> Unit)
    : RecyclerView.Adapter<LaporanAsetAdapter.LaporanAsetViewHolder>(){

//    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    class LaporanAsetViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {

        val id_pks = view.findViewById<TextView>(R.id.id_pks)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val nilai_pks = view.findViewById<TextView>(R.id.nilai_pks)
        val jenis_kerjasama = view.findViewById<TextView>(R.id.jenis_kerjasama)
        val header_color = view.findViewById<TextView>(R.id.header_color)
        val tomboldetail = view.findViewById<Button>(R.id.cek_detail)

        init {
            tomboldetail.setOnClickListener {
                listener.onItemClick(adapterPosition)

            }
        }

        fun bindView(laporanAsetModel: LaporanAsetModel, listener: (LaporanAsetModel) -> Unit){
            id_pks.text = laporanAsetModel.id_pks
            nama_mitra.text = laporanAsetModel.nama_mitra
            nilai_pks.text = laporanAsetModel.nilai_pks
            jenis_kerjasama.text = laporanAsetModel.jenis_kerjasama
            header_color.text = laporanAsetModel.header_color
            if (laporanAsetModel.header_color.equals("Dikirim", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.blue
                )
            }
            else if(laporanAsetModel.header_color.equals("Dikembalikan", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.holo_orange_dark
                )
            }
            else if(laporanAsetModel.header_color.equals("Draft", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.darker_gray
                )
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanAsetViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_laporan_aset, parent, false)

        return LaporanAsetViewHolder(itemView, mListener)


    }

    override fun onBindViewHolder(holder: LaporanAsetViewHolder, position: Int) {
        holder.bindView(LaporanAset[position], listener)
    }

    override fun getItemCount(): Int = LaporanAset.size

}