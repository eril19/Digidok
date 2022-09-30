
package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class PengajuanKerjasamaAdapter(private val context: Context, private val PengajuanKerja: List<PengajuanKerjasamaModel>, private var mListener: onItemClickListener,
                         val listener: (PengajuanKerjasamaModel) -> Unit)
    : RecyclerView.Adapter<PengajuanKerjasamaAdapter.PengajuanKerjasamaViewHolder>(){

//    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

//    fun setOnItemClickListener(listener: onItemClickListener){
//        mListener = listener
//    }


    class PengajuanKerjasamaViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {

        val id_mitra = view.findViewById<TextView>(R.id.id_mitra)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val jenis_mitra = view.findViewById<TextView>(R.id.jenis_mitra)
        val header_color = view.findViewById<TextView>(R.id.header_color)
        val periode = view.findViewById<TextView>(R.id.periode)
//        val titlePeriode = view.findViewById<TextView>(R.id.title_periode)
        val cardView = view.findViewById<CardView>(R.id.cardViewDaftarPengajuan)

        init {
            cardView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bindView(pengajuanKerjasamaModel: PengajuanKerjasamaModel, listener: (PengajuanKerjasamaModel) -> Unit){
            id_mitra.text = pengajuanKerjasamaModel.id_mitra
            nama_mitra.text = pengajuanKerjasamaModel.nama_mitra
            jenis_mitra.text = pengajuanKerjasamaModel.jenis_mitra
            header_color.text = pengajuanKerjasamaModel.header_color
//            titlePeriode.text = pengajuanKerjasamaModel.titlePeriode
            periode.text = pengajuanKerjasamaModel.periode

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

        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_daftar_pengajuan, parent, false)

        return PengajuanKerjasamaViewHolder(itemView, mListener)


    }

    override fun onBindViewHolder(holder: PengajuanKerjasamaViewHolder, position: Int) {
        holder.bindView(PengajuanKerja[position], listener)
    }

    override fun getItemCount(): Int = PengajuanKerja.size

}
