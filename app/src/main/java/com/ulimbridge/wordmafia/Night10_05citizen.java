package com.ulimbridge.wordmafia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

public class Night10_05citizen extends AppCompatActivity {
    String inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night10_05citizen_revised);

        Button button = findViewById(R.id.button10_04);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 게임 End 조건 코드
                Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
                Log.v("태그", "10_05에서 totalCount의 값은" + s1.totalCount);
                EditText editText1 = (EditText) findViewById(R.id.editTextCitizenAnswer);
                inputText = editText1.getText().toString();
                // 화면반복하기 위한 기능
                try {
                    Log.v("태그", "10_05 Total Count는" + s1.totalCount);
                    int citizenFavorite = Integer.parseInt(inputText);
                    if (s1.totalCount >= 1) {
                        s1.totalCount = s1.totalCount - 1;// 10_03으로 가는 놈에만 totalCount-1해줘야함
                        Log.v("태그", "10_05에서 좋아하는 숫자 선택 후의 totalCount의 값은" + s1.totalCount);
                        if (s1.totalCount < 1) {
                            Intent intent = new Intent(getApplicationContext(), Morning08_02sad.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(getApplicationContext(), Night10_03Distinguish.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                } catch (NumberFormatException e) {
                    if (inputText instanceof String) {
                        StyleableToast.makeText(getApplicationContext(), "숫자를 입력해주세요"
                                , Toast.LENGTH_SHORT, R.style.mytoast).show();
//                        Toast.makeText(getApplicationContext(), "숫자를 입력해주세요",
//                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("게임을 종료하시겠습니까?");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Singleton_Setting99 s1 = Singleton_Setting99.getInstance();

//                Log.v("태그", "마지막에 삭제된 playerArrayList의 객체수는 " + s1.playerArrayList.size());
                s1.playerArrayList.clear();
                Log.v("태그", "마지막에 삭제된 playerArrayList의 객체수는 "+ s1.playerArrayList.size());
                s1.ListViewCategory.clear();
                Log.v("태그", "마지막에 삭제된 ListViewCategory의 객체수는 "+ s1.ListViewCategory.size());
                s1.totalCount=0;
                s1.count = 1;
                s1.mafiaNightCount = 0;
                finishAffinity();
            }
        });
        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        alBuilder.setTitle("프로그램 종료");
        alBuilder.show();
    }
}
