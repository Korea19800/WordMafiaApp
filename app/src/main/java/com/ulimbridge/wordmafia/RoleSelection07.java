package com.ulimbridge.wordmafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;


public class RoleSelection07 extends AppCompatActivity {

    private WordDBRecord999 wordDBRecord;
    Singleton_Setting99 s1 = Singleton_Setting99.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection07);

        StyleableToast.makeText(getApplicationContext(), "소리를 키시면 더 재밌게 즐기실 수 있습니다", Toast.LENGTH_LONG, R.style.mytoast).show();

        wordDBRecord = new WordDBRecord999(this);
        // WordDBRecord에서 OpenHelepr 열때 필요한 context를 이 Activity에서 제공하게 만듬!!
        wordDBRecord.open();

        // 1. 밑의 if else문은 insert 한번만 하게 하는 코드임
        if (wordDBRecord.getRowCount("KeyWordDB") == 546) {
            Log.v("태그", "RoleSelection07에서 DB에 이미 insert했습니다, 따라서 아무것도 insert하지 않습니다");
        } else {
            wordDBRecord.insertRows(); // 2. 새로 만든 메소드
            Log.v("태그", "DB에 insert 처음합니다, 따라서 insert 합니다");
        }
        wordDBRecord.DBSearchCategory("KeyWordDB");
        wordDBRecord.DBSearchCitizenWord("KeyWordDB");
        wordDBRecord.DBSearchMafiaWord("KeyWordDB");
        wordDBRecord.close();

        // 여기서 init통해 객체를 만들고 직업배정!+total과 totalCount의 값을 설정
        Log.v("태그", "RoleSelection에서 init전 playerArrayList 수 결정하는 total의 크기는" + s1.total);
        s1.init();
        s1.JobSelection();
        s1.totalCount = s1.playerArrayList.size();
        s1.mafiaNightCount = 0;
        Log.v("태그", "RoleSelection에서 지정된 totalCount의 크기는" + s1.totalCount);
        Log.v("태그", "count의 값은" + s1.count);

        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RoleSelection07_02.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed() {
        Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
        s1.ListViewCategory.clear(); // Category Select하던 도중 중간에 나가면 s1의 ArrayList 값 초기화 해줌!
        Log.v("태그", "마지막에 삭제된 ListViewCategory의 객체수는 " + s1.ListViewCategory.size());
        // 여기서 playerArrayList비우지 않으면 여기서 back누를시 다음판에 5명 설정해도 전판에 7명 선택했을시 12명이 선택됨
        s1.playerArrayList.clear();
        Log.v("태그", "마지막에 삭제된 playerArrayList의 객체수는 " + s1.playerArrayList.size());
        s1.totalCount = 0;
        super.onBackPressed(); // 이 코드가 있어야 나갈 수 있음
    }
}
