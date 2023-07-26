package com.example.assignement2

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView

import com.example.assignement2.R

class CaculateLoan_emp : Fragment(R.layout.fragment_caculate_loan_emp) {
    var sp:String = "home loan"
    var checkYearMonth:Int=2;
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_caculate_loan_emp, container, false)
        val Spinner1: Spinner = view.findViewById<Spinner>(R.id.loan_details)
        val intrest: TextView = view.findViewById(R.id.intrestRate_txt)
        val loan_term:EditText = view.findViewById(R.id.loanTerm_txt)
        val totalamount:EditText=view.findViewById(R.id.totalAmount_txt)
        val submitbtn:Button=view.findViewById(R.id.submit_btn)
        val paymentRadio = view.findViewById<RadioGroup>(R.id.payment)
        val totalPayment:TextView = view.findViewById(R.id.payment_txt)
        val monthlysalary:EditText=view.findViewById(R.id.monthly_salary_txt)
        paymentRadio.setOnCheckedChangeListener{
                group, checkedId ->
            val radioButton = view.findViewById<RadioButton>(checkedId)
            // Perform actions based on the selected RadioButton
            when (checkedId) {
                R.id.yearly_radio_btn -> {
                    checkYearMonth=2;
                   val txt12=view.findViewById<TextView>(R.id.payment12)
                    txt12.text="yearly payment";
                }
                R.id.monthly_radio_btn -> {
                    checkYearMonth=1;
                    view.findViewById<TextView>(R.id.payment12).text="mothly payment"
                }

            }
        }
        var options = arrayOf("home loan" , "car loan" , "personal loan")
        Spinner1.adapter= ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,options)
        Spinner1.onItemSelectedListener=object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               sp = options[p2]
            }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        submitbtn.setOnClickListener{view->
            var tamount : Double= totalamount.text.toString().toDouble();
            var lterm:Double = loan_term.text.toString().toDouble();
            var salary:Double=monthlysalary.text.toString().toDouble();
    if(sp == "home loan"){
       var rate:Double=hcalintrest(tamount,lterm,salary);
      intrest.text = rate.toString();
      }
            else if(sp=="car loan"){
        var rate:Double=car_calintrest(tamount,lterm,salary);
        intrest.text = rate.toString();
            }
            else{
        var rate:Double=personal_calintrest(tamount,lterm,salary);
        intrest.text = rate.toString();
            }
            var x:Double=intrest.text.toString().toDouble();
           var y:Double = cal_payment(tamount,lterm,x,checkYearMonth)
            totalPayment.text=formatDoubleToTwoDecimalPlaces(y).toString();

        }
        return view;
    }
    private fun hcalintrest(totalamount:Double,loanterm:Double,salary: Double):Double{
    var rate:Double= 3.0

   // rate+=100.0
    if(totalamount < 10000.0 ){
        rate += 0.5;
    }
        else if(totalamount < 100000.0){
        rate += 1.5;
        }
        else {
        rate += 3.0;
        }

        if(loanterm < 3.0 ){
            rate+= 0.5;
        }
        else if(loanterm <7.0){
            rate+=1.5;
        }
        else {
            rate+=3;
        }
if(salary_checker(totalamount,loanterm,rate,salary))
    return rate
        else
            return 0.0
}
    fun formatDoubleToTwoDecimalPlaces(number: Double): String {
        return String.format("%.2f", number)
    }
    private fun car_calintrest(totalamount:Double,loanterm:Double,salary: Double):Double{
        var rate:Double= 2.0

        // rate+=100.0
        if(totalamount < 1000.0 ){
            rate += 0.5;
        }
        else if(totalamount < 10000.0){
            rate += 1.0;
        }
        else {
            rate += 2;
        }

        if(loanterm < 3.0 ){
            rate+= 0.5;
        }
        else if(loanterm <7.0){
            rate+=1.0;
        }
        else {
            rate+=loanterm/2
        }

        if(salary_checker(totalamount,loanterm,rate,salary))
            return rate
        else
            return 0.0
    }
    private fun personal_calintrest(totalamount:Double,loanterm:Double,salary: Double):Double{
        var rate:Double= 2.0
        // rate+=100.0
        if(totalamount < 1000.0 ){
            rate += 1.0;
        }
        else if(totalamount < 100000.0){
            rate += 1.5;
        }
        else {

            rate += 2;
        }

        if(loanterm < 3.0 ){
            rate+= 0.5;
        }
        else if(loanterm <7.0){
            rate+=1.0;
        }
        else {

            rate+=loanterm/2
        }

        if(salary_checker(totalamount,loanterm,rate,salary))
            return rate
        else
            return 0.0
    }
    public fun cal_payment(TotalAmount: Double,Year :Double,Intrest:Double,pay_method:Int):Double{

        val yearlyInterestRateDecimal = Intrest / 100.0
        if(pay_method==1){
            var yearlyPayment:Double = TotalAmount * (yearlyInterestRateDecimal * Math.pow(1 + yearlyInterestRateDecimal, Year))
            return yearlyPayment/12

        }
        else{
            var yearlyPayment:Double = TotalAmount * (yearlyInterestRateDecimal * Math.pow(1 + yearlyInterestRateDecimal, Year))
            return yearlyPayment

        }


    }
    public fun salary_checker(TotalAmount: Double,Year :Double,Intrest:Double,salary:Double):Boolean{
        val yearlyInterestRateDecimal = Intrest / 100.0
            var yearlyPayment:Double = TotalAmount * (yearlyInterestRateDecimal * Math.pow(1 + yearlyInterestRateDecimal, Year))
        var salary_percentage:Double = salary * 80/100;
        yearlyPayment=yearlyPayment/12
        if(yearlyPayment>salary_percentage)
            return false
        else
            return true
    }
}
