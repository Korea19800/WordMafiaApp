package com.ulimbridge.wordmafia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class WordDBHelper999 extends SQLiteOpenHelper {
    // 특이하게 table 이름이 KeyWordDB임
    static final String TABLE_NAME = "KeyWordDB";

    public WordDBHelper999(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d(TAG, "WordDBHelper 생성자 호출");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
// OnCreate에선 앱 최초 설치 이후엔 Log안뜬다 따라서 Log칠 필요 없다
        String createQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CATEGORY TEXT NOT NULL, " +
                "WORD TEXT NOT NULL);";
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d(TAG, "Table onUpgrade");
        // 테이블 재정의하기 위해 현재의 테이블 삭제
        sqLiteDatabase.execSQL(WordContractDB999.SQL_DROP_TBL);
        Log.v("태그", "이전 TABLE 없애기");
//         이후 onCreate통해 새 TABLE 만들기
        onCreate(sqLiteDatabase);
        Log.v("태그", "새로운 TABLE 만들기! 새로운 버젼은 "+ newVersion);
    }
}


