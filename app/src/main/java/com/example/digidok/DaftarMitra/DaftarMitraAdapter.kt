package com.example.digidok.DaftarMitra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.R

class DaftarMitraAdapter(private val context: Context, val daftarMitraViewModel: DaftarMitraViewModel, private var mListener: onItemClickListener,
                         val listener: (DaftarMitraModel) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var DaftarMitra: List<DaftarMitraModel> = daftarMitraViewModel.mData

    interface onItemClickListener{
        fun onItemClick(position: Int)
        fun onItemClickPopupMenu(position: Int, kodeMitra:String, statusMitra:String , NPWP:String, view : View)
    }

    class DaftarMitraViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val id_mitra = view.findViewById<TextView>(R.id.id_mitra)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val jenis_mitra = view.findViewById<TextView>(R.id.jenis_mitra)
        val status = view.findViewById<TextView>(R.id.status)
        val status_mitra = view.findViewById<TextView>(R.id.status_mitra)
        val npwp = view.findViewById<TextView>(R.id.npwp)
        val npwp_mitra = view.findViewById<TextView>(R.id.npwp_mitra)
        var header_color = view.findViewById<TextView>(R.id.header_color)
        val cardView = view.findViewById<CardView>(R.id.cardViewMitra)
        val popup = view.findViewById<ImageView>(R.id.menupopup)
        var statusMitra = ""
        var kodeMitra = ""
        var NPWP = ""

        init {
            popup.setOnClickListener {
                listener.onItemClickPopupMenu(adapterPosition, kodeMitra, statusMitra, NPWP, popup)
            }
        }

        fun bindView(daftarMitraModel: DaftarMitraModel, listener: (DaftarMitraModel) -> Unit){
            id_mitra.text = daftarMitraModel.id_mitra
            nama_mitra.text = daftarMitraModel.nama_mitra
            jenis_mitra.text = daftarMitraModel.jenis_mitra
            status.text = daftarMitraModel.status
            status_mitra.text = daftarMitraModel.status_mitra
            npwp.text = daftarMitraModel.npwp
            npwp_mitra.text = daftarMitraModel.npwp_mitra
            header_color.text = daftarMitraModel.header_color
            statusMitra = daftarMitraModel.statusAktifNonAktf
            kodeMitra = daftarMitraModel.id_mitra
            NPWP = daftarMitraModel.npwp_mitra


            if (daftarMitraModel.header_color.equals("NON AKTIF", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.red2
                )
            }
            else if(daftarMitraModel.header_color.equals("AKTIF", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.green
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
                val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_daftar_mitra, parent, false)
                return DaftarMitraViewHolder(itemView, mListener)

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DaftarMitraViewHolder) {
            holder.bindView(DaftarMitra[position], listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if((position == itemCount - 1) && daftarMitraViewModel.isLastPage.value == false){
            R.layout.item_pagination
        } else {
            R.layout.layout_card_daftar_mitra
        }

    }

    override fun getItemCount(): Int {
        val extras = (if(daftarMitraViewModel.isLastPage.value == true) 0 else 1)
        return DaftarMitra.size + extras
    }
}