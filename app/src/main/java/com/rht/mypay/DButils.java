package com.rht.mypay;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DButils {
    public static final String DATABASE_NAME = "moneyPad";//数据库名
    public static final String DATABASE_TABLE = "Note";
    public static final int DATABASE_VERSION = 1;
    public static final String NOTE_TYPE = "moneyType";
    public static final String NOTE_REMARKS = "moneyRemarks";
    public static final String NOTE_MONEY = "money";
    public static final String NOTE_TIME = "moneyTime";
    public static final String NOTE_ID = "moneyID";
    public static final String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日 ");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);

    }
}
