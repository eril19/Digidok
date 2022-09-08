package com.example.digidok

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.DaftarMitraModel
import com.example.digidok.R

class DaftarMitraAdapter(private val context: Context, private val DaftarMitra: List<DaftarMitraModel>, val listener: (DaftarMitraModel) -> Unit)
    : RecyclerView.Adapter<DaftarMitraAdapter.DaftarMitraViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    class DaftarMitraViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {

        val id_mitra = view.findViewById<TextView>(R.id.id_mitra)
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val jenis_mitra = view.findViewById<TextView>(R.id.jenis_mitra)
        val status = view.findViewById<TextView>(R.id.status)
        val status_mitra = view.findViewById<TextView>(R.id.status_mitra)
        val npwp = view.findViewById<TextView>(R.id.npwp)
        val npwp_mitra = view.findViewById<TextView>(R.id.npwp_mitra)
        val header_color = view.findViewById<TextView>(R.id.header_color)

        fun bindView(daftarMitraModel: DaftarMitraModel, listener: (DaftarMitraModel) -> Unit){
            id_mitra.text = daftarMitraModel.id_mitra
            nama_mitra.text = daftarMitraModel.nama_mitra
            jenis_mitra.text = daftarMitraModel.jenis_mitra
            status.text = daftarMitraModel.status
            status_mitra.text = daftarMitraModel.status_mitra
            npwp.text = daftarMitraModel.npwp
            npwp_mitra.text = daftarMitraModel.npwp_mitra
            header_color.text = daftarMitraModel.header_color
            if (daftarMitraModel.header_color.equals("Tidak Aktif", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.red2
                )
            }
            else if(daftarMitraModel.header_color.equals("Aktif", true) ) {
                header_color.background = ContextCompat.getDrawable(header_color.context,
                    R.color.green
                )
            }
        }

        init {
            itemView.setOnClickListener {

                listener.onItemClick(adapterPosition)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarMitraViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_daftar_mitra, parent, false)

        return DaftarMitraViewHolder(itemView, mListener)


    }

    override fun onBindViewHolder(holder: DaftarMitraViewHolder, position: Int) {
        holder.bindView(DaftarMitra[position], listener)
    }

    override fun getItemCount(): Int = DaftarMitra.size

}