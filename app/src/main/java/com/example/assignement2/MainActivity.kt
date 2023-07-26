package com.example.assignement2

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val loan_cal_non_employee =CaculateLoan();
        val loan_cal_employee = CaculateLoan_emp();
        val contactUs = contactus();
        val rateus = rate_Us();
        when(item.itemId) {
            R.id.laon_cal -> supportFragmentManager.beginTransaction().apply {
                replace(R.id.first_fargment,loan_cal_non_employee)
                commit()
            }
R.id.loan_employee_btn-> supportFragmentManager.beginTransaction().apply {
    replace(R.id.first_fargment,loan_cal_employee)
    commit()
}
            R.id.contact_btn-> supportFragmentManager.beginTransaction().apply {
                replace(R.id.first_fargment,contactUs)
                commit()
            }
            R.id.rate_btn-> supportFragmentManager.beginTransaction().apply {
                replace(R.id.first_fargment,rateus)
                commit()
            }

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bank_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

}