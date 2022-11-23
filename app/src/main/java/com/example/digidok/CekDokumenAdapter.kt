package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CekDokumenAdapter(private val context: Context, private val CekDokumen: List<CekDokumenModel>, private var mListener:onItemClickListener, val listener: (CekDokumenModel) -> Unit)
    : RecyclerView.Adapter<CekDokumenAdapter.CekDokumenViewHolder>(){

    interface onItemClickListener{
        fun onItemClick(position: Int)

        fun onItemClickCekLampiran(position: Int, link:String)
    }

    class CekDokumenViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val nama_dokumen = view.findViewById<TextView>(R.id.nama_dokumen)
        val header_color = view.findViewById<TextView>(R.id.header_color)
        val cekDokumenBtn = view.findViewById<Button>(R.id.cek_dokumen_btn)
        var file = ""

        init {
            cekDokumenBtn.setOnClickListener {
                listener.onItemClickCekLampiran(adapterPosition,file)
            }
        }

        fun bindView(cekDokumenModel: CekDokumenModel, listener: (CekDokumenModel) -> Unit){
            nama_dokumen.text = cekDokumenModel.nama_dokumen
            header_color.text = cekDokumenModel.header_color
            file = cekDokumenModel.file

            if (cekDokumenModel.header_color.equals("Dikembalikan", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.holo_orange_dark
                )
            }
            else if(cekDokumenModel.header_color.equals("Disetujui", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.green
                )
            }
            else if(cekDokumenModel.header_color.equals("Draft", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    android.R.color.darker_gray
                )
            }
            else if(cekDokumenModel.header_color.equals("Dikirim", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.blue
                )
            }
            else if(cekDokumenModel.header_color.equals("Tidak Ada Dokumen", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.red2
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CekDokumenViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_cek_dok, parent, false)
        return CekDokumenViewHolder(
            itemView,mListener
        )
    }

    override fun onBindViewHolder(holder: CekDokumenViewHolder, position: Int) {
        holder.bindView(CekDokumen[position], listener)
    }

    override fun getItemCount(): Int = CekDokumen.size

}
