package com.alisonnakashima.flexcalculator

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var etConsum1 : EditText
    private lateinit var etConsum2 : EditText
    private lateinit var etCost1 : EditText
    private lateinit var etCost2 : EditText

    private lateinit var btCalculate : Button
    private lateinit var btClean : Button

    private lateinit var spFuelTypes1: Spinner
    private lateinit var spFuelTypes2: Spinner

    private lateinit var tvResult : TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result_better1 = getText(R.string.result_better1).toString()
        val result_better2 = getText(R.string.result_better2).toString()
        val result_better3 = getText(R.string.result_better3).toString()
        val result_better4 = getText(R.string.result_better4).toString()
        val result_better5 = getText(R.string.result_better5).toString()
//        val result_better = listOf(result_better1,result_better2, result_better3, result_better4, result_better5)

        val result_iqual1 = getText( R.string.result_iqual1).toString()
        val result_iqual2 = getText( R.string.result_iqual2).toString()
        val result_iqual3 = getText( R.string.result_iqual3).toString()
        val result_iqual4 = getText( R.string.result_iqual4).toString()
//        var result_iqual = listOf(result_iqual1, result_iqual2, result_iqual3, result_iqual4)

        etConsum1 = findViewById( R.id.etConsum1 )
        etConsum2 = findViewById( R.id.etConsum2 )
        etCost1 = findViewById( R.id.etCost1 )
        etCost2 = findViewById( R.id.etCost2 )

        spFuelTypes1 = findViewById( R.id.spFuelTypes1)
        spFuelTypes2 = findViewById( R.id.spFuelTypes2)

        btCalculate = findViewById( R.id.btCalculate )
        btClean = findViewById( R.id.btClean )

        tvResult = findViewById( R.id.tvResult)

        //getting the messages


        btClean.setOnClickListener{
            btCleanOnClick()
        }

        btCalculate.setOnClickListener{
            btCalculateOnClick(result_better1, result_better2 ,result_better3, result_better4, result_better5, result_iqual1, result_iqual2, result_iqual3, result_iqual4)
        }
    }

    private fun btCleanOnClick() {

        etConsum1.setText( "" )
        etConsum2.setText( "" )
        etCost1.setText( "" )
        etCost2.setText( "" )
        tvResult.setText( "" )
        etConsum1.setBackgroundColor(Color.TRANSPARENT);
        etConsum2.setBackgroundColor(Color.TRANSPARENT);
        etCost1.setBackgroundColor(Color.TRANSPARENT);
        etCost2.setBackgroundColor(Color.TRANSPARENT);
        spFuelTypes1.setBackgroundColor(Color.TRANSPARENT);
        spFuelTypes2.setBackgroundColor(Color.TRANSPARENT);
    }

    private fun btCalculateOnClick( result_better1: String, result_better2: String, result_better3: String, result_better4: String, result_better5: String, result_iqual1: String, result_iqual2: String, result_iqual3: String, result_iqual4: String) {

        //Empty error + Zero error
        if ( etConsum1.text.toString().isEmpty() || etConsum2.text.toString().isEmpty() || etCost1.text.toString().isEmpty() || etCost2.text.toString().isEmpty() || etConsum1.text.toString() == "0" || etConsum2.text.toString() == "0" || etCost1.text.toString() == "0" || etCost2.text.toString() == "0" ) {
            if (etConsum1.text.toString().isEmpty()) {
                etConsum1.error = getString(R.string.error_consum)
                etConsum1.requestFocus()
            }

            else if (etConsum2.text.toString().isEmpty()) {
                etConsum2.error = getString(R.string.error_consum)
                etConsum2.requestFocus()
            }

            else if (etCost1.text.toString().isEmpty()) {
                etCost1.error = getString(R.string.error_cost)
                etCost1.requestFocus()
            }

            else if (etCost2.text.toString().isEmpty()) {
                etCost2.error = getString(R.string.error_cost)
                etCost2.requestFocus()
            }
            //Zero error
            else if (etConsum1.text.toString() == "0") {
                etConsum1.error = getString(R.string.error_zero)
                etConsum1.requestFocus()
            }

            else if (etConsum2.text.toString() == "0") {
                etConsum2.error = getString(R.string.error_zero)
                etConsum2.requestFocus()
            }

            else if (etCost1.text.toString() == "0") {
                etCost1.error = getString(R.string.error_zero)
                etCost1.requestFocus()
            }

            else if (etCost2.text.toString() == "0") {
                etCost2.error = getString(R.string.error_zero)
                etCost2.requestFocus()
            }
        }
        else {

            var consum1 = etConsum1.text.toString().toDouble()
            var consum2 = etConsum2.text.toString().toDouble()
            var cost1 = etCost1.text.toString().toDouble()
            var cost2 = etCost2.text.toString().toDouble()

            var calculate = Calc();
            var costPerDistance1 = calculate.CalcValues(consum1, cost1)
            var costPerDistance2 = calculate.CalcValues(consum2, cost2)

            var resultCompare = FinalCompare();
            var resultIqual = FinalIqual();

            var fuel1= spFuelTypes1.selectedItem.toString()
            var fuel2 = spFuelTypes2.selectedItem.toString()


            if (costPerDistance1 > costPerDistance2) {
//                tvResult.text = "2 melhor que 1"
//                Thread.sleep(10000)
                var better = fuel2
                var worst = fuel1
                etConsum2.setBackgroundColor(Color.parseColor("#047A33"))
                etCost2.setBackgroundColor(Color.parseColor("#047A33"))
                spFuelTypes2.setBackgroundColor(Color.parseColor("#047A33"))
                etConsum1.setBackgroundColor(Color.parseColor("#FFE16666"))
                etCost1.setBackgroundColor(Color.parseColor("#FFE16666"))
                spFuelTypes1.setBackgroundColor(Color.parseColor("#FFE16666"))

                tvResult.text = resultCompare.Compare(better , worst, costPerDistance2, costPerDistance1, result_better1, result_better2, result_better3, result_better4, result_better5)

            } else if (costPerDistance1 < costPerDistance2) {
//                tvResult.text = "1 melhor que 2"
//                Thread.sleep(10000)
                var better = fuel1
                var worst = fuel2
                etConsum1.setBackgroundColor(Color.parseColor("#047A33"))
                etCost1.setBackgroundColor(Color.parseColor("#047A33"))
                spFuelTypes1.setBackgroundColor(Color.parseColor("#047A33"))
                etConsum2.setBackgroundColor(Color.parseColor("#FFE16666"))
                etCost2.setBackgroundColor(Color.parseColor("#FFE16666"))
                spFuelTypes2.setBackgroundColor(Color.parseColor("#FFE16666"))
                tvResult.text = resultCompare.Compare(better, worst, costPerDistance1, costPerDistance2, result_better1, result_better2, result_better3, result_better4, result_better5)

            } else {
//                tvResult.text = "1 igual a 2"
//                Thread.sleep(10000)
                etConsum1.setBackgroundColor(Color.parseColor("#1FB3B2"))
                etConsum2.setBackgroundColor(Color.parseColor("#1FB3B2"))
                etCost1.setBackgroundColor(Color.parseColor("#1FB3B2"))
                etCost2.setBackgroundColor(Color.parseColor("#1FB3B2"))
                spFuelTypes1.setBackgroundColor(Color.parseColor("#1FB3B2"))
                spFuelTypes2.setBackgroundColor(Color.parseColor("#1FB3B2"))
                tvResult.text  = resultIqual.IqualCons(fuel1, fuel2, costPerDistance1, result_iqual1, result_iqual2, result_iqual3, result_iqual4)

            }
        closeKeyBoard() //close keyboard when things go right
        }
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    class Calc{
        fun CalcValues ( consum: Double, cost: Double): Double{
            var costPerDistance = cost/consum
            return (costPerDistance)
        }
    }


    class FinalCompare {
        fun Compare(good: String, bad: String, goodCost: Double, badCost: Double, result1: String, result2: String, result3: String, result4: String, result5: String): String {
//            var goodCost = goodCost.toString()
//            var badCost = badCost.toString()
//            goodCost = String.format("%.3f", goodCost)
//            badCost = String.format("%.3f", badCost)

            var resultText = result1+" "+good+" "+result2+" "+String.format("%.3f", goodCost)+" "+result3+" "+bad+result4+" "+String.format("%.3f", badCost)+" "+result5;
            return (resultText)
        }
    }

    class FinalIqual {
        fun IqualCons (fuel1: String, fuel2: String, price: Double, result1: String, result2: String, result3: String, result4: String): String {
            price.toString()
            var price = price.toString()
//            price = String.format("%.3f", price)
            var resultText = result1+" "+fuel1+" "+result2+" "+fuel2+" "+result3+" "+price+" "+result4;
            return (resultText)
        }
    }

}
