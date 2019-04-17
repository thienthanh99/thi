package com.example.thi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String s1 = "moi nhap du lieu";
    String s2 = "nhap sai";

    double a,b;
    double s;

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> strings = new ArrayList<String>();
    EditText textView1,textView2;
    TextView tt;
    ListView listView;
    Button button;

    Pattern pattern;
    Matcher matcher1,matcher2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (EditText) findViewById(R.id.text1);
        textView2 = (EditText) findViewById(R.id.text2);
        tt = (TextView) findViewById(R.id.tt);
        listView = findViewById(R.id.item);

        pattern = Pattern.compile("[a-z]");
        matcher1 = pattern.matcher(textView1.getText().toString());
        matcher2 = pattern.matcher(textView2.getText().toString());

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                strings.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(matcher1.matches());
                if(textView1.getText().toString().equals("") || textView2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();
                }else if (matcher1.lookingAt()) {
                    Toast.makeText(getApplicationContext(),s2,Toast.LENGTH_LONG).show();
                }else{
                    a = Double.parseDouble(textView1.getText().toString());
                    b = Double.parseDouble(textView2.getText().toString());
                    s = a/b;
                    System.out.println(s);
                    String kk = a+"/"+b+"="+s;
                    strings.add(kk);
                    tt.setText(a+"/"+b+"="+s);
                    arrayAdapter.notifyDataSetChanged();
                }


            }
        });
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,strings);
        listView.setAdapter(arrayAdapter);
    }
}