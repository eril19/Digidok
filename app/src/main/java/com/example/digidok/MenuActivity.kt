package com.example.digidok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
//import meow.bottomnavigation.MeowBottomNavigation

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

//        val apps = AppsFragment()
//        val dashboard = DashboardFragment()
//        val pelaporan = PelaporanFragment()

//        supportFragmentManager.beginTransaction().add(){
////            replace(R.id.)
//        }



        supportActionBar?.hide()
        addFragment(DashboardFragment.newInstance())
        val bottomNavigation = findViewById<MeowBottomNavigation>(R.id.bottomNavigation)
        bottomNavigation.show(2)
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_baseline_file_copy_50))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_baseline_dashboard_24))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_baseline_apps_24))

        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                1 -> {
//                    Toast.makeText(this@MenuActivity, "option 1" , Toast.LENGTH_SHORT).show()
                    replaceFragment(PelaporanFragment.newInstance())
                }

                2 -> {
//                    Toast.makeText(this@MenuActivity, "option 2" , Toast.LENGTH_SHORT).show()
                    replaceFragment(DashboardFragment.newInstance())

                }

                3 -> {
//                    Toast.makeText(this@MenuActivity, "option 3" , Toast.LENGTH_SHORT).show()
                    replaceFragment(AppsFragment.newInstance())

                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(fragment::class.java.simpleName).commit()

    }

    private fun addFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(fragment::class.java.simpleName).commit()
    }


}