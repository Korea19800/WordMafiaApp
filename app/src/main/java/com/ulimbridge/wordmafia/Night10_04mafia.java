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

public class Night10_04mafia extends AppCompatActivity {
    String inputText;
    int victimID; // 이 변수는 s1.victimID 와 다른 변수임

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night10_04mafia_revised);

        Button button = findViewById(R.id.buttonMafiaMurder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
                Log.v("태그", "10_04에서 totalCount의 값은" + s1.totalCount);
                EditText editText1 = (EditText) findViewById(R.id.editTextMafiaMurder);
//                inputText = editText1.getText().toString();
                Log.v("태그", "10_04 Total Count는" + s1.totalCount);
                Log.v("태그", "10_04에서 s1.mafiaNightCount 의 값은" + s1.mafiaNightCount);
                Log.v("태그", "10_04에서 victimID의 값은" + victimID);
                Log.v("태그", "10_04에서 s1.victimID의 값은" + s1.victimOverlapID);
                Log.v("태그", "10_04에서 s1.playerArrayList.size의 값은" + s1.playerArrayList.size());

                try {
                    inputText = editText1.getText().toString();
                    // 화면 반복시키기 위한 변수
                    victimID = Integer.parseInt(inputText);
                    Log.v("태그", "10_04 Total Count는" + s1.totalCount);
                    Log.v("태그", "10_04에서 s1.mafiaNightCount 의 값은" + s1.mafiaNightCount);
                    Log.v("태그", "10_04에서 victimID의 값은" + victimID);
                    Log.v("태그", "10_04에서 s1.victimID의 값은" + s1.victimOverlapID);
                    Log.v("태그", "10_04에서 s1.playerArrayList.size의 값은" + s1.playerArrayList.size());

                    if (victimID == 0 || victimID > s1.playerArrayList.size()) {
                        StyleableToast.makeText(getApplicationContext(), "존재하지 않는 Player의 순번입니다"
                                , Toast.LENGTH_SHORT, R.style.mytoast).show();
                    } else if (s1.playerArrayList.get(victimID - 1) == null) {
                        StyleableToast.makeText(getApplicationContext(), "죽은 사람을 또 죽이는 것은 엄연한 고인 모독입니다 !! "
                                , Toast.LENGTH_SHORT, R.style.mytoast).show();
                    } else if (s1.mafiaNightCount == 1 && s1.victimOverlapID == 990811) { // 첫번째 마피아// 이 조건문은 이 페이지에 처음 들어오는 마피아를 의미
                        if (s1.playerArrayList.get(victimID - 1) == null) { //1. 09에서 투표로 죽은 사람을 마피아가 못죽이게 방지
                            StyleableToast.makeText(getApplicationContext(), "죽은 사람을 또 죽이는 것은 엄연한 고인 모독입니다 !! "
                                    , Toast.LENGTH_SHORT, R.style.mytoast).show();
                        } else if (victimID > s1.playerArrayList.size() || victimID == 0) { // id가 player 수 보다 많거나 0인 경우 에러 방지
                            StyleableToast.makeText(getApplicationContext(), "존재하지 않는 Player의 순번입니다"
                                    , Toast.LENGTH_SHORT, R.style.mytoast).show();
                        } else if (s1.totalCount >= 1) {
                            s1.victimOverlapID = victimID; // 두번째 마피아부터 중복 못하게 하기 위한 코드
                            Log.v("태그", "s1.vicitimId의 값은 " + s1.victimOverlapID);
                            s1.totalCount = s1.totalCount - 1; // 10_03으로 넘어가야지만 totalCount줄여야함!
                            Log.v("태그", "10_04에서 선택 후의 Total Count는" + s1.totalCount);
                            if (s1.totalCount >= 1) {
                                Intent intent = new Intent(getApplicationContext(), Night10_03Distinguish.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent4 = new Intent(getApplicationContext(), Morning08_02sad.class);
                                startActivity(intent4);
                                finish();
                            }
                        }
                    } else if (victimID != s1.victimOverlapID) { // 이놈은 최소 2번째 마피아인데 1번째 마피아가 죽인놈과 다른  놈을 죽인경우
                        // 여기선 s1.victimID가 0임 > 따라서 처음 애는 아무거나 입력 가능.// 하지만 밑에 10_03Distinguish로 가는 코드에 s1.victim=victim으로 저장했음 따라서 두번째 마피아부터는 중복 x
                        StyleableToast.makeText(getApplicationContext(), "동료 마피아가 입력한 번호는 " + s1.victimOverlapID + " 입니다"
                                , Toast.LENGTH_SHORT, R.style.mytoast).show();
                        if (victimID > s1.playerArrayList.size() || victimID == 0) { // id가 0인 넘 못죽이게 만듬
                            StyleableToast.makeText(getApplicationContext(), "존재하지 않는 Player의 순번입니다"
                                    , Toast.LENGTH_SHORT, R.style.mytoast).show();

                        }
                    } else if (victimID == s1.victimOverlapID) { // 최소 2번째 마피아인데 1번째 놈이 죽인놈과 같은 놈을 죽인경우
                        s1.totalCount = s1.totalCount - 1; // 10_03으로 넘어가야지만 totalCount줄여야함!
                        if (s1.totalCount >= 1) {
                            s1.victimOverlapID = victimID;
                            Log.v("태그", "s1.vicitimId의 값은 " + s1.victimOverlapID);
                            Log.v("태그", "10_04에서 선택 후의 Total Count는" + s1.totalCount);
                            Intent intent = new Intent(getApplicationContext(), Night10_03Distinguish.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent4 = new Intent(getApplicationContext(), Morning08_02sad.class);
                            startActivity(intent4);
                            finish();
                        }
                    }
                } catch (NumberFormatException e) {
                    if (inputText instanceof String) {
                        StyleableToast.makeText(getApplicationContext(), "숫자를 입력해주세요"
                                , Toast.LENGTH_SHORT, R.style.mytoast).show();
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

                s1.playerArrayList.clear();
                Log.v("태그", "마지막에 삭제된 playerArrayList의 객체수는 " + s1.playerArrayList.size());
                s1.ListViewCategory.clear();
                Log.v("태그", "마지막에 삭제된 ListViewCategory의 객체수는 " + s1.ListViewCategory.size());
                s1.totalCount = 0;
                s1.count = 1;
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
