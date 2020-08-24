package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zero.setOnClickListener { appendExpression("0",true)}
        one.setOnClickListener { appendExpression("1",true)}
        two.setOnClickListener { appendExpression("2",true)}
        three.setOnClickListener { appendExpression("3",true)}
        four.setOnClickListener { appendExpression("4",true)}
        five.setOnClickListener { appendExpression("5",true)}
        six.setOnClickListener { appendExpression("6",true)}
        seven.setOnClickListener { appendExpression("7",true)}
        eight.setOnClickListener { appendExpression("8",true)}
        nine.setOnClickListener { appendExpression("9",true)}
        dot.setOnClickListener { appendExpression(".",true)}


        plus.setOnClickListener { appendExpression("+",false)}
        minus.setOnClickListener { appendExpression("-",false)}
        muply.setOnClickListener { appendExpression("*",false)}
        divide.setOnClickListener { appendExpression("/",false)}

        open.setOnClickListener { appendExpression("(",false)}
        close.setOnClickListener { appendExpression(")",false)}

        clear.setOnClickListener {
            mathExpression.text=""
            result.text=""
        }

        back.setOnClickListener {
            var string = mathExpression.text.toString();
            if (string.isNotEmpty()) {
                mathExpression.text=string.substring(0,string.length-1)
            }
            else
            {
                mathExpression.text=""
            }
        }

        equal.setOnClickListener {
            try {
                val expression = ExpressionBuilder(mathExpression.text.toString()).build()
                val mathResult = expression.evaluate()
                val longResult = mathResult.toLong()
                if(mathResult == longResult.toDouble()) {
                    result.text = longResult.toString()
                }
                else
                {
                    result.text = mathResult.toString()
                }
            }
            catch (e:Exception) {
                Log.d("Exception","message : " +e.message)
            }
        }
    }

    fun appendExpression( string : String , canClear : Boolean) {
        if (result.text.isNotEmpty()) {
            mathExpression.text =""
        }

        if (canClear) {
            result.text =""
            mathExpression.append(string)
        }
        else
        {
            mathExpression.append(result.text)
            mathExpression.append(string)
            result.text=""
        }
    }
}
