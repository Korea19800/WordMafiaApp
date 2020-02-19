package com.ulimbridge.wordmafia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class WordContractDB999 { // 1. 사실상 이 WordContractDB 클래스는 필요없음
    private WordContractDB999() {
    }

    public static final String TBL_WordDB = "WordDB_TB";
    public static final String COL_NO = "NO";
    public static final String COL_Category = "CATEGORY";
    public static final String COL_Word = "WORD";

//    public static final String SQL_CREATE_TBL = " CREATE TABLE IF NOT EXISTS " + TBL_WordDB + " " +
//            "(" +
//            COL_NO + " INTEGER PRIMARY KEY AUTOINCREMENT " + ", " +
//            COL_Category + " TEXT " + ", " +
//            COL_Word + " TEXT " +
//            ")";

    // 1. 유일하게 이 메소드는 쓸 듯 DROP TABLE IF EXISTS CONTACT_T
    public static final String SQL_DROP_TBL = " DROP TABLE IF EXISTS " + WordDBHelper999.TABLE_NAME;

    // SELECT * FROM CONTACT_T + Random으로 뽑게 만듬 ORDER BY RAND()
    public static final String SQL_SELECT = "SELECT * FROM " + TBL_WordDB + "ORDER BY RAND() ";

    public static final String SQL_INSERT = "INSERT OR REPLACE INTO " + TBL_WordDB + " " +
            "(" + COL_Category + ", " + COL_Word + ") VALUES ";

    // DELETE FROM CONTACT_T
    public static final String SQL_DELETE = "DELETE FROM " + TBL_WordDB;
    // 1. 원래 이 밑의 코드는 없는건데 내가 추가함!!
}

