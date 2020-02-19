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

public class Night10_03Distinguish extends AppCompatActivity {
    String inputText;
    int distinguishID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night10_03);

        Button button = (Button) findViewById(R.id.button10_03);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
                PlayerData99 playerData99 = new PlayerData99();
                EditText editText1 = (EditText) findViewById(R.id.editTextDistinguishJob);
                try {
                    inputText = editText1.getText().toString();
                    distinguishID = Integer.parseInt(inputText); //정수값 가져오기

                    if (s1.totalCount == 0) {
                        Log.v("태그", "10_03에서 totalCount의 값은" + s1.totalCount);
                        Intent intent4 = new Intent(getApplicationContext(), Morning08_02sad.class);
                        startActivity(intent4);
                        finish();
                    } else if (distinguishID > s1.playerArrayList.size() || distinguishID == 0) { // id가 total플레이여보다 많을때 또는 id가 0일때
                        StyleableToast.makeText(getApplicationContext(), "존재하지 않는 Player의 순번입니다"
                                , Toast.LENGTH_SHORT, R.style.mytoast).show();

                    } else if (s1.playerArrayList.get(distinguishID - 1) == null) {
                        StyleableToast.makeText(getApplicationContext(), "죽은 자는 말이 없다... "
                                , Toast.LENGTH_SHORT, R.style.mytoast).show();

                    } else if (s1.playerArrayList.get(distinguishID - 1).alreadyNight == true) { // 밤에 중복으로 일 못하게 하기 위한 메소드
                        StyleableToast.makeText(getApplicationContext(), "이미 밤에 할 일을 하셨습니다! "
                                , Toast.LENGTH_SHORT, R.style.mytoast).show();
                    } else if (s1.playerArrayList.get(distinguishID - 1).isMafia == true) {
                        s1.mafiaNightCount = s1.mafiaNightCount + 1;
                        Log.v("태그", "s1.mafiaNightCount 값은 " + s1.mafiaNightCount);
                        // 중복 방지 코드
                        try {
                            playerData99.setOverlapCheck(distinguishID);
                            Log.v("태그", "10_03에서 OverlapCheck()실행됨");
                        } catch (IndexOutOfBoundsException e) {

                        }
                        Intent intent = new Intent(getApplicationContext(), Night10_04mafia.class);
                        startActivity(intent);
                        finish();
                    } else if (s1.playerArrayList.get(distinguishID - 1).isMafia == false) {
                        try {
                            playerData99.setOverlapCheck(distinguishID);
                            Log.v("태그", "10_03에서 OverlapCheck()실행됨");
                        } catch (IndexOutOfBoundsException e) {

                        }
                        Intent intent2 = new Intent(getApplicationContext(), Night10_05citizen.class);
                        startActivity(intent2);
                        finish();
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

                s1.playerArrayList.clear();
                Log.v("태그", "마지막에 삭제된 playerArrayList의 객체수는 " + s1.playerArrayList.size());
                s1.ListViewCategory.clear();
                Log.v("태그", "마지막에 삭제된 ListViewCategory의 객체수는 " + s1.ListViewCategory.size());
                s1.totalCount = 0;
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
