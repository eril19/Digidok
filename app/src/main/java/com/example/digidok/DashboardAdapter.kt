package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.DashboardModel
import com.example.digidok.R

class DashboardAdapter(private val context: Context, private val Dashboard: List<DashboardModel>, val listener: (DashboardModel) -> Unit)
    : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>(){

    class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nama_mitra = view.findViewById<TextView>(R.id.nama_mitra)
        val jenis_mitra = view.findViewById<TextView>(R.id.jenis_mitra)

        fun bindView(dashboardModel: DashboardModel, listener: (DashboardModel) -> Unit){
            nama_mitra.text = dashboardModel.nama_mitra
            jenis_mitra.text = dashboardModel.jenis_mitra
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
