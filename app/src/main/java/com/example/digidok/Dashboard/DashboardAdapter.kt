package com.example.digidok.Dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.R
import java.text.DecimalFormat

class DashboardAdapter(private val context: Context, val dashboardViewModel: DashboardViewModel, val listener: (DashboardModel) -> Unit)
    : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>(){

    private var Dashboard: List<DashboardModel> = dashboardViewModel.mData

    class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val jenis_mitra = view.findViewById<TextView>(R.id.jenis_mitra)
        val jumlahNilai = view.findViewById<TextView>(R.id.totalNilai)
        val total = view.findViewById<TextView>(R.id.jumlahKerjasama)
        var formatter : DecimalFormat = DecimalFormat("#,##0.00")
        fun bindView(dashboardModel: DashboardModel, listener: (DashboardModel) -> Unit){
            nama_mitra.text = dashboardModel.nama_mitra
            jenis_mitra.text = dashboardModel.jenis_mitra
            total.text = dashboardModel.jumlah_kerjasama
            jumlahNilai.text = "Rp. " +  formatter.format(dashboardModel.total_nilai.toLong())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return DashboardViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_card_dashboard, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bindView(Dashboard[position], listener)
    }

    override fun getItemCount(): Int = Dashboard.size

}
