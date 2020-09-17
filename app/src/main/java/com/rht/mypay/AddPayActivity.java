package com.rht.mypay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddPayActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<String>();
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private TextView time;
    private TextView money_text;
    private TextView text3;
    private SQLiteHelper sqLiteHelper;
    private Button btn_save;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pay);
        spinner = findViewById(R.id.spinner1);
        time = findViewById(R.id.time_1);
        time.setText(DButils.getTime());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView spinnertext = (TextView) spinner.getChildAt(0);
                spinnertext.setTextColor(Color.BLACK);
                result = parent.getItemAtPosition(position).toString();//获取选项的值
                Toast.makeText(AddPayActivity.this, result, Toast.LENGTH_SHORT).show();//显示
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        initData();
        money_text = findViewById(R.id.text_money);
        text3 = findViewById(R.id.text3);
        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mtext,ttext,timetext;
                mtext = money_text.getText().toString();
                ttext = text3.getText().toString();
                timetext = time.getText().toString();
                if (!mtext.equals("")){
                    boolean b = sqLiteHelper.insertData(mtext, result, ttext,timetext);
                    if (b){
                        Toast.makeText(AddPayActivity.this, "添加成功", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(AddPayActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(AddPayActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
                    startActivity(intent);
                }else {
                    Toast.makeText(AddPayActivity.this, "你输入的金额为空请输入金额", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected void initData(){
        sqLiteHelper = new SQLiteHelper(this);
    }

}
