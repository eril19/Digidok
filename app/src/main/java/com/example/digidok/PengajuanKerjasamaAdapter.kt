
package com.example.digidok

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
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

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    class PengajuanKerjasamaViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {

        val id_pks = view.findViewById<TextView>(R.id.id_pks)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val jenis_mitra = view.findViewById<TextView>(R.id.jenis_mitra)
        val header_color = view.findViewById<TextView>(R.id.header_color)
        val tglmulai = view.findViewById<TextView>(R.id.tglMulai)
        val tglakhir = view.findViewById<TextView>(R.id.tglAkhir)
        val cardView = view.findViewById<CardView>(R.id.cardViewDaftarPengajuan)
        val menu_popup = view.findViewById<ImageView>(R.id.menu_popup)

//        init {
//            cardView.setOnClickListener {
//                listener.onItemClick(adapterPosition)
//            }
//        }

        fun bindView(pengajuanKerjasamaModel: PengajuanKerjasamaModel, listener: (PengajuanKerjasamaModel) -> Unit){
            id_pks.text = pengajuanKerjasamaModel.id_pks
            nama_mitra.text = pengajuanKerjasamaModel.nama_mitra
            jenis_mitra.text = pengajuanKerjasamaModel.jenis_mitra
            header_color.text = pengajuanKerjasamaModel.header_color
            tglakhir.text = pengajuanKerjasamaModel.tglAkhir
            tglmulai.text = pengajuanKerjasamaModel.tglMulai

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
        holder.menu_popup.setOnClickListener(View.OnClickListener {
            val popup = PopupMenu(context, holder.menu_popup)
            popup.inflate(R.menu.daftar_pengajuan_menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_view -> {
                        val intent = Intent(context, PengajuanKerjasamaDetailActivity::class.java)
                        context.startActivity(intent)
                        true
                    }
                    R.id.menu_edit -> {
                        val intent = Intent(context, PengajuanKerjasamaDetailActivity::class.java)
                        context.startActivity(intent)
                        true
                    }
                    R.id.menu_telaah -> {
                        val intent = Intent(context, PengajuanKerjasamaDetailActivity::class.java)
                        context.startActivity(intent)
                        true
                    }
                }
                false
            }

            popup.show()
        })

    }

    override fun getItemCount(): Int = PengajuanKerja.size

}
