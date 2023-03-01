
package com.example.digidok.DaftarPengajuanKerjasama

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.R
import java.text.SimpleDateFormat

class PengajuanKerjasamaAdapter(private val context: Context, val pengajuanKerjasamaViewModel: PengajuanKerjasamaViewModel, private var mListener: onItemClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var PengajuanKerja: MutableList<PengajuanKerjasamaModel> = pengajuanKerjasamaViewModel.mData

    interface onItemClickListener{
        fun onItemClick(position: Int)
        fun onItemClickPopupMenu(position: Int, statusPengajuan:String, idPks:String, view : View)
    }

    class PengajuanKerjasamaViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        var pattern: String? = "dd/MM/yyyy"
        var simpleDateFormat = SimpleDateFormat(pattern)
        val id_pks = view.findViewById<TextView>(R.id.id_pks)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
//        val jenis_mitra = view.findViewById<TextView>(R.id.jenis_mitra)
        val header_color = view.findViewById<TextView>(R.id.header_color)
        val tglmulai = view.findViewById<TextView>(R.id.tglMulai)
        val tglakhir = view.findViewById<TextView>(R.id.tglAkhir)
        val cardView = view.findViewById<CardView>(R.id.cardViewDaftarPengajuan)
        val menu_popup = view.findViewById<ImageView>(R.id.menu_popup)
        var statusPengajuan = ""
        var idPks =""

        init {
            menu_popup.setOnClickListener {
                listener.onItemClickPopupMenu(adapterPosition, statusPengajuan, idPks,menu_popup)
            }
        }


        fun bindView(pengajuanKerjasamaModel: PengajuanKerjasamaModel){
            id_pks.text = pengajuanKerjasamaModel.no_pks
            nama_mitra.text = pengajuanKerjasamaModel.nama_mitra
//            jenis_mitra.text = pengajuanKerjasamaModel.jenis_mitra
            header_color.text = pengajuanKerjasamaModel.header_color
            tglakhir.text = pengajuanKerjasamaModel.periodeAkhir
            tglmulai.text = pengajuanKerjasamaModel.periodeAwal
            statusPengajuan = pengajuanKerjasamaModel.header_color
            idPks = pengajuanKerjasamaModel.no_pks

            if (pengajuanKerjasamaModel.header_color.equals("DRAFT", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.darker_gray
                )
            }
            else if(pengajuanKerjasamaModel.header_color.equals("DIKIRIM", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.blue
                )
            }
            else if(pengajuanKerjasamaModel.header_color.equals("DIKEMBALIKAN", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.holo_orange_dark
                )
            }
            else if(pengajuanKerjasamaModel.header_color.equals("DITOLAK", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.holo_red_light
                )
            }
            else if(pengajuanKerjasamaModel.header_color.equals("DISETUJUI", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.green
                )
            }
            else if(pengajuanKerjasamaModel.header_color.equals("DIHAPUS", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.black
                )
            }
        }
    }

    class LoadingViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_pagination -> {
                val itemView = LayoutInflater.from(context).inflate(R.layout.item_pagination, parent, false)
                return LoadingViewHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_daftar_pengajuan, parent, false)
                return PengajuanKerjasamaViewHolder(itemView, mListener)

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PengajuanKerjasamaViewHolder) {
            holder.bindView(PengajuanKerja[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if((position == itemCount - 1) && pengajuanKerjasamaViewModel.isLastPage.value == false){
            R.layout.item_pagination
        } else {
            R.layout.layout_card_daftar_pengajuan
        }

    }

    override fun getItemCount(): Int {
        val extras = (if(pengajuanKerjasamaViewModel.isLastPage.value == true) 0 else 1)
        return PengajuanKerja.size + extras
    }

}
