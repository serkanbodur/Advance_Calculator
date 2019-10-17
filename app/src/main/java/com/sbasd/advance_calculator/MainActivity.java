package com.sbasd.advance_calculator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;




public class MainActivity extends AppCompatActivity {


    TextView sonuc;
    TextView infix;
    TextView postfix;
    Button calculate;
    EditText islem;
    String inf=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sonuc=findViewById(R.id.rsltTxt);
        infix=findViewById(R.id.infixTxt);
        postfix=findViewById(R.id.postfixTxt);
        calculate=findViewById(R.id.button);
        islem=findViewById(R.id.editText);

    }


    public void hesapla(View view) {
        infix.setText(islem.getText());
        inf = islem.getText().toString();
        PostFixConverter pc =new PostFixConverter(inf);
        postfix.setText(pc.printExpression());
        Calculator cal=new Calculator(pc.getPostfixAsList());
        BigDecimal x = new BigDecimal(100);
        x=cal.result();
        sonuc.setText(x.toString());
    }
}



