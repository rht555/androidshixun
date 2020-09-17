package com.rht.mypay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class moneyAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<moneyList> lists;


    public moneyAdapter(Context context, List<moneyList> list) {
        this.layoutInflater = LayoutInflater.from(context);
        this.lists = list;
    }


    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_money, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        moneyList moneylist = (moneyList) getItem(position);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
        Date date = StringToDate(moneylist.getDay());
        viewHolder.textView_time.setText(simpleDateFormat.format(date));
        viewHolder.textView_money.setText(moneylist.getMoney());
        viewHolder.textView_type.setText(moneylist.getType());
        viewHolder.textView_remarks.setText(moneylist.getRemarks());
        return convertView;
    }

    class ViewHolder {
        TextView textView_time;
        TextView textView_money;
        TextView textView_type;
        TextView textView_remarks;

        public ViewHolder(View view) {
            textView_time = view.findViewById(R.id.item_time);
            textView_money = view.findViewById(R.id.item_money);
            textView_type = view.findViewById(R.id.item_type);
            textView_remarks = view.findViewById(R.id.item_remarks);
        }
    }
    public static Date StringToDate(String datetime){
        SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy 年 MM 月 dd 日 ");
        Date date = new Date();
        try {
            date = sdFormat.parse(datetime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

}
