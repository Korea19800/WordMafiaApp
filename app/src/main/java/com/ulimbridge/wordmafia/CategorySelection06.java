package com.ulimbridge.wordmafia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

public class CategorySelection06 extends AppCompatActivity {
    Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
    ListView listview;
    // 리스트뷰에 클릭리스너 달기
    // Adapter 생성
    CategoryAdapter06 adapter = new CategoryAdapter06();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection06);

        Log.v("태그", "설정된 총인원은 " + s1.total + " 명");
        Log.v("태그", "설정된 마피아는 " + s1.mafia + " 명");
        Log.v("태그", "설정된 시민은 " + s1.citizen + " 명");
        Log.v("태그", "totalCount의 값은" + s1.totalCount);
        Log.v("태그", "count의 값은" + s1.count);

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 아이템 추가하기
        adapter.addItem("애완동물");
        adapter.addItem("아이돌그룹");
        adapter.addItem("게임");
        adapter.addItem("국가");
        adapter.addItem("도시이름");
        adapter.addItem("한국도시");
        adapter.addItem("지하철");
        adapter.addItem("유명인물");
        adapter.addItem("한국영화");
        adapter.addItem("외국영화");
        adapter.addItem("영화감독");
        adapter.addItem("스포츠");
        adapter.addItem("축구선수");
        adapter.addItem("NBA선수");
        adapter.addItem("MLB선수");
        adapter.addItem("동음이의어");
        adapter.addItem("음료");
        adapter.addItem("분식집메뉴");
        adapter.addItem("애니메이션");
        adapter.addItem("패스트푸드");
        adapter.addItem("색");
        adapter.addItem("교통수단");
        adapter.addItem("악기");
        adapter.addItem("의류");
        adapter.addItem("전자제품");
        adapter.addItem("패션브랜드");
        adapter.addItem("차");
        adapter.addItem("취미생활");
        adapter.addItem("보드게임");
        adapter.addItem("카페메뉴");
        adapter.addItem("학과");
        adapter.addItem("대기업");
        adapter.addItem("신체기관");
        //        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background),
//                "화장실");화장실 삭제함

        Button b1 = (Button) findViewById(R.id.buttonNext);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount(); // 전체 자료의 갯수 새기 위한 메소드

                Log.v("태그", "ListView의 count는 " + count);

                for (int i = 0; i < count; i++) {
                    if (checkedItems.get(i)) {
                        s1.ListViewCategory.add(adapter.listViewItemList.get(i).getText());
//                        Log.v("태그", "제외된 카테고리는 " + s1.ListViewCategory.get(i));
                        Log.v("태그", "06에서 제외된 카테고리는 " + adapter.listViewItemList.get(i).getText());
                    }
                }
                Log.v("태그", "06에서 설정된 s1.ListViewCateogry의 크기는 " + s1.ListViewCategory.size());

                // 1. 밑의 코드는 체크박스 상태 초기화시키는 코드
                listview.clearChoices();
                Intent intent = new Intent(getApplicationContext(), RoleSelection07.class);
                startActivity(intent);
                finish();
            }
        });
        Button b2 = (Button) findViewById(R.id.clickAll);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;
                count = adapter.getCount();
                for (int i = 0; i < count; i++) {
                    listview.setItemChecked(i, true);
                }
            }
        });
        Button b3 = (Button) findViewById(R.id.cancelAll);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listview.clearChoices();
            }
        });
    }

    public void onBackPressed() {
        Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
        s1.ListViewCategory.clear(); // Category Select하던 도중 중간에 나가면 s1의 ArrayList 값 초기화 해줌!
        Log.v("태그", "마지막에 삭제된 ListViewCategory의 객체수는 " + s1.ListViewCategory.size());
        listview.clearChoices(); // 이 코드가 있어야 중간에 나가도 체크박스 체크상태 초기화 가능
        super.onBackPressed(); // 이 코드가 있어야 나갈 수 있음
    }
}
