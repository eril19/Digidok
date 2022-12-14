package com.example.digidok

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PelaporanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PelaporanFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v : View = inflater.inflate(R.layout.fragment_pelaporan, container, false)

        val dokumenBtn : ImageButton = v.findViewById(R.id.repositoriDokumenBtn)

        dokumenBtn.setOnClickListener {
            val i : Intent = Intent(this@PelaporanFragment.requireContext(), RepositoriDokumenActivity::class.java)

            startActivity(i)
        }

        val kerjasamaBtn : ImageButton = v.findViewById(R.id.kerjasamaBtn)

        kerjasamaBtn.setOnClickListener {
            val i : Intent = Intent(this@PelaporanFragment.requireContext(), LaporanPengajuanActivity::class.java)

            startActivity(i)
        }

        val laporanAsetBtn : ImageButton = v.findViewById(R.id.laporanAsetBtn)

        laporanAsetBtn.setOnClickListener {
            val i : Intent = Intent(this@PelaporanFragment.requireContext(), LaporanAsetActivity::class.java)

            startActivity(i)
        }

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PelaporanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            PelaporanFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}