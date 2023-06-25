package com.example.calci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


import soup.neumorphism.NeumorphButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

        TextView result_tv, solution_tv;
        NeumorphButton button_c, button_openBracket,button_closedBracket,button_divide,button_7,
                button_8,button_9,button_multiple,button_4,button_5,button_6,button_minus,
                button_3,button_2,button_1,button_add,button_AC,button_0,button_dot,button_equals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv=findViewById(R.id.result_tv);
        solution_tv=findViewById(R.id.solution_tv);

        assignId(button_c,R.id.button_c);
        assignId(button_openBracket,R.id.button_openBracket);
        assignId(button_closedBracket,R.id.button_closedBracket);
        assignId(button_divide,R.id.button_divide);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_multiple,R.id.button_multiple);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_minus,R.id.button_minus);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_add,R.id.button_add);
        assignId(button_AC,R.id.button_AC);
        assignId(button_0,R.id.button_0);
        assignId(button_dot,R.id.button_dot);
        assignId(button_equals,R.id.button_equals);
       // assignId(button_c,R.id.button_c);




    }
    void assignId(NeumorphButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        NeumorphButton button = (NeumorphButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();
        if(buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solution_tv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")){
            result_tv.setText(finalResult);
        }
    }
    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "javascript", 1, null).toString();
            if (finalResult.endsWith(".0"))
            {
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e)
        {
            return "Err";
        }

    }

}