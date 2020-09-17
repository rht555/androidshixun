package com.rht.mypay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView img_add;
    private ListView lv;
    private List<moneyList> lm = new ArrayList<>();
    private moneyAdapter adapter;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_add = findViewById(R.id.img_btn);
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPayActivity.class);
                startActivity(intent);
            }
        });
        lv = findViewById(R.id.listview);
        initData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemId = (int) adapter.getItemId(position);
                moneyList mlist_index = lm.get(itemId);
                String id1 = mlist_index.getId();
                Intent intent = new Intent(MainActivity.this, xougaiActivity.class);
                intent.putExtra("m_id", id1);
                startActivity(intent);
            }
        });
        lv.setOnCreateContextMenuListener(new AdapterView.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("选择操作");
                menu.add(0,0,0,"删除");
            }
        });
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = (int)info.id;
        moneyList mlist_index = lm.get(id);
        String id1 = mlist_index.getId();
        boolean b = sqLiteHelper.deleteData(id1);
        if (b){
            Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        showQueryHelperData();
        return true;
    }
    protected void initData() {
        sqLiteHelper = new SQLiteHelper(this);
        showQueryHelperData();
    }

    private void showQueryHelperData() {
        if (lm != null) {
            lm.clear();
        }
        lm = sqLiteHelper.query();
        adapter = new moneyAdapter(this, lm);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            showQueryHelperData();
        }
    }

}
