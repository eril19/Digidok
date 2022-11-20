package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DaftarSuratLampiranAdapter(private val context: Context, private val PengajuanKerja: List<PengajuanKerjasamaDetailModel>, private var mListener:onItemClickListener,
                                 val listener: (PengajuanKerjasamaDetailModel) -> Unit)
    : RecyclerView.Adapter<DaftarSuratLampiranAdapter.DaftarSuratLampiranViewHolder>()
{

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    class DaftarSuratLampiranViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {

        val kodeDokumen = view.findViewById<TextView>(R.id.kode_dokumen)
        val jenisDokumen = view.findViewById<TextView>(R.id.nama_lokasi)
        val noSurat = view.findViewById<TextView>(R.id.no_surat)
        val tglDokumen = view.findViewById<TextView>(R.id.tglDokumen)
        val keteranganSurat = view.findViewById<TextView>(R.id.keterangan_surat)


        fun bindView(pengajuanKerjasamaDetailModel: PengajuanKerjasamaDetailModel, listener: (PengajuanKerjasamaDetailModel) -> Unit){

            kodeDokumen.text = pengajuanKerjasamaDetailModel.kodeDokumen
            jenisDokumen.text = pengajuanKerjasamaDetailModel.jenisDokumen
            noSurat.text = pengajuanKerjasamaDetailModel.noSurat
            tglDokumen.text = pengajuanKerjasamaDetailModel.tanggalDokumen
            keteranganSurat.text = pengajuanKerjasamaDetailModel.keteranganSurat


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarSuratLampiranViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_card_surat_lampiran, parent, false)
        return DaftarSuratLampiranViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: DaftarSuratLampiranViewHolder, position: Int) {
        holder.bindView(PengajuanKerja[position], listener)
    }

    override fun getItemCount(): Int = PengajuanKerja.size
}