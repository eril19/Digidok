package com.example.digidok

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.utils.Injection
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var adapter: DashboardAdapter
var isLoading : Boolean = false
var dashboardList: ArrayList<DashboardModel> = ArrayList()
private var recyclerview: RecyclerView? = null


/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val calendar:Calendar = Calendar.getInstance()

        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)

        val dateText = v.findViewById<TextView>(R.id.dateText)

//        val bind = FragmentDashboardBinding.inflate(layoutInflater)
//
//        bind.profileArrow.setOnClickListener {
//
//            val i = Intent(this@DashboardFragment.requireContext(), ProfileOptionActivity::class.java)
//
//            startActivity(i)
//
//        }

        val dropdown_profile: ImageView = v.findViewById(R.id.profileArrow)
        recyclerview = v.findViewById(R.id.rv_list_dashboard)

        dateText.setText(currentDate)

        setListData()
        getBerita()

        dropdown_profile.setOnClickListener {
//            val i = Intent(this@LoginActivity, MenuActivity::class.java)
            startActivity(Intent(this@DashboardFragment.requireContext(), ProfileOptionActivity::class.java))
        }

        return v




//        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_dashboard)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = DashboardAdapter(this, DashboardList){
//
//        }

    }

    fun setListData() {
//        val sizeofData : TextView = v.findViewById(R.id.dataSize)


        recyclerview?.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerview?.setHasFixedSize(true)

//        val showData = DashboardList.size
//        sizeofData.setText(showData)
        recyclerview?.adapter = DashboardAdapter(this.requireContext(), dashboardList) {
            }
    }

    fun getBerita() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(requireContext())
        mRepository.getBerita("0", "10",  object : DataSource.BeritaDataCallback {
                override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                    isLoading = false
                    if (data.success) {
                        dashboardList.clear()
                        data.data?.forEach {
                            dashboardList?.add(DashboardModel(it?.editor.toString(), it?.tanggal.toString()))
                        }
                        setListData()
                    }
                }

                override fun onError(message: String) {
                    isLoading = false
                }

                override fun onFinish() {
                    isLoading = false
                }

            })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance() =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}