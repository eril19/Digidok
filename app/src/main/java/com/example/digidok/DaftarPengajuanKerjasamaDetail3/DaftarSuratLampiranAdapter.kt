package com.example.digidok.DaftarPengajuanKerjasamaDetail3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.DaftarMitra.DaftarMitraModel
import com.example.digidok.DaftarPengajuanKerjasamaDetail1.DaftarPengajuanKerjasamaDetailModel
import com.example.digidok.R

class DaftarSuratLampiranAdapter(private val context: Context, val daftarPengajuanKerjasamaDetail3ViewModel: DaftarPengajuanKerjasamaDetail3ViewModel, private var mListener: onItemClickListener,
                                 val listener: (DaftarPengajuanKerjasamaDetailModel) -> Unit)
    : RecyclerView.Adapter<DaftarSuratLampiranAdapter.DaftarSuratLampiranViewHolder>()
{

    private var PengajuanKerja: List<DaftarPengajuanKerjasamaDetailModel> = daftarPengajuanKerjasamaDetail3ViewModel.mData


    interface onItemClickListener{
        fun onItemClick(position: Int)

        fun onItemClickCekLampiran(position: Int, link:String)
    }



    class DaftarSuratLampiranViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {
        val kodeDokumen = view.findViewById<TextView>(R.id.kode_dokumen)
        val jenisDokumen = view.findViewById<TextView>(R.id.nama_lokasi)
        val noSurat = view.findViewById<TextView>(R.id.no_surat)
        val tglDokumen = view.findViewById<TextView>(R.id.tglDokumen)
        val keteranganSurat = view.findViewById<TextView>(R.id.keterangan_surat)
        val buttonDokumenLampiran = view.findViewById<ImageButton>(R.id.buttonDokumenLampiran)
        var lampiran = ""

        init {
            buttonDokumenLampiran.setOnClickListener {
                listener.onItemClickCekLampiran(adapterPosition, lampiran)
            }
        }



        fun bindView(pengajuanKerjasamaDetailModel: DaftarPengajuanKerjasamaDetailModel, listener: (DaftarPengajuanKerjasamaDetailModel) -> Unit){
            kodeDokumen.text = pengajuanKerjasamaDetailModel.kodeDokumen
            jenisDokumen.text = pengajuanKerjasamaDetailModel.jenisDokumen
            noSurat.text = pengajuanKerjasamaDetailModel.noSurat
            tglDokumen.text = pengajuanKerjasamaDetailModel.tanggalDokumen
            keteranganSurat.text = pengajuanKerjasamaDetailModel.keteranganSurat
            lampiran = pengajuanKerjasamaDetailModel.lampiranLink
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