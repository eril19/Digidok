package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.PengajuanKerjasamaModel
import com.example.digidok.R

class PengajuanKerjasamaAdapter(private val context: Context, private val PengajuanKerja: List<PengajuanKerjasamaModel>,
   private var mListener: onItemClickListener, val listener: (PengajuanKerjasamaModel) -> Unit)
    : RecyclerView.Adapter<PengajuanKerjasamaAdapter.PengajuanKerjasamaViewHolder>(){

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    class PengajuanKerjasamaViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val id_mitra = view.findViewById<TextView>(R.id.id_mitra)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val jenis_mitra = view.findViewById<TextView>(R.id.jenis_mitra)
//        val status = view.findViewById<TextView>(R.id.status)
        val status_mitra = view.findViewById<TextView>(R.id.statusmitra)
//        val npwp = view.findViewById<TextView>(R.id.npwp)
        val npwp_mitra = view.findViewById<TextView>(R.id.npwpmitra)
        val header_color = view.findViewById<TextView>(R.id.header_color)
        val cardview = view.findViewById<CardView>(R.id.cardViewkjpp)

        init {
            cardview.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bindView(pengajuanKerjasamaModel: PengajuanKerjasamaModel, listener: (PengajuanKerjasamaModel) -> Unit){
            id_mitra.text = pengajuanKerjasamaModel.id_mitra
            nama_mitra.text = pengajuanKerjasamaModel.nama_mitra
            jenis_mitra.text = pengajuanKerjasamaModel.jenis_mitra
//            status.text = pengajuanKerjasamaModel.status
            status_mitra.text = pengajuanKerjasamaModel.status_mitra
//            npwp.text = pengajuanKerjasamaModel.npwp
            npwp_mitra.text = pengajuanKerjasamaModel.npwp_mitra
            header_color.text = pengajuanKerjasamaModel.header_color


            if (pengajuanKerjasamaModel.header_color.equals("Draft", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.darker_gray
                )
            }
            else if(pengajuanKerjasamaModel.header_color.equals("Dikirim", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.blue
                )
            }
            else if(pengajuanKerjasamaModel.header_color.equals("Menunggu Validasi", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.holo_orange_dark
                )
            }
            else if(pengajuanKerjasamaModel.header_color.equals("Disetujui", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.green
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengajuanKerjasamaViewHolder {

        val itemView =  LayoutInflater.from(context).inflate(R.layout.layout_card_pengajuan, parent, false)


        return PengajuanKerjasamaViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: PengajuanKerjasamaViewHolder, position: Int) {
        holder.bindView(PengajuanKerja[position], listener)
    }

    override fun getItemCount(): Int = PengajuanKerja.size

}
