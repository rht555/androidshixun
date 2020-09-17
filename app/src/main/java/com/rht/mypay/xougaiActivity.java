package com.rht.mypay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class xougaiActivity extends AppCompatActivity {
private EditText xg_text;
private EditText xg_beizhu;
private Button xg_btn;
private SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xougai);
        Intent intent = getIntent();
        final String m_id = (String) intent.getStringExtra("m_id");
        xg_text = findViewById(R.id.xg_text_money);
        xg_btn = findViewById(R.id.btn_xg);
        xg_beizhu = findViewById(R.id.xg_text3);
        initData();
        xg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xg_text_t = xg_text.getText().toString();
                String xg_beizhu_t = xg_beizhu.getText().toString();
                boolean b = sqLiteHelper.updateData(m_id, xg_text_t, xg_beizhu_t);
                if (b){
                    Toast.makeText(xougaiActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(xougaiActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(xougaiActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//刷新
                startActivity(intent);
            }
        });
    }
    protected void initData(){
        sqLiteHelper = new SQLiteHelper(this);
    }
}
