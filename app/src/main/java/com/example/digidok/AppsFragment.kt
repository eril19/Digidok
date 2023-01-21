package com.example.digidok

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.digidok.DaftarKJPP.DaftarKjppActivity
import com.example.digidok.DaftarMitra.DaftarMitraActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AppsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AppsFragment : Fragment() {
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
        val v : View = inflater.inflate(R.layout.fragment_apps, container, false)

        val daftarmitrabtn : ImageButton = v.findViewById(R.id.mitraBtn)

        daftarmitrabtn.setOnClickListener {
            val i : Intent = Intent(this@AppsFragment.requireContext(), DaftarMitraActivity::class.java)

            startActivity(i)
        }

        val pengajuanKerjasamaabtn : ImageButton = v.findViewById(R.id.pengajuanBtn)

        pengajuanKerjasamaabtn.setOnClickListener {
            val i : Intent = Intent(this@AppsFragment.requireContext(), PengajuanKerjasamaActivity::class.java)

            startActivity(i)
        }

        val daftarKjppbtn : ImageButton = v.findViewById(R.id.kjppBtn)

        daftarKjppbtn.setOnClickListener {
            val i : Intent = Intent(this@AppsFragment.requireContext(), DaftarKjppActivity::class.java)

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
         * @return A new instance of fragment AppsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AppsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}