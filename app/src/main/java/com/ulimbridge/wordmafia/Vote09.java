package com.ulimbridge.wordmafia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

public class Vote09 extends AppCompatActivity {
    String inputText;
    int executeeID;
    private static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote09_revised);

        mp = MediaPlayer.create(this, R.raw.vote09_narrator);
        mp.setLooping(false); // 반복 방지
        mp.start();

        Button button = findViewById(R.id.buttonVote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
                PlayerData99 playerData99 = new PlayerData99();
                // totalCount 다시 세줌 + mafiaNightCount 다시 세줌(2번째 턴부터 필요하기에)
                s1.totalCount = s1.total;
                s1.mafiaNightCount = 0;
                s1.victimOverlapID = 990811; // 여기서 990811로 셋함
                // 밑의 for문은 alreadyNight false처리시키기 위한 코드
                for (int i = 0; i < s1.playerArrayList.size(); i++) {
                    if (s1.playerArrayList.get(i) == null) {
                        Log.v("태그", "09에서 alreadyNight의 값 초기화시 null은 스킵");
                    } else {
                        s1.playerArrayList.get(i).alreadyNight = false;
                    }
                }
                Log.v("태그", "09에서 totalCount의 값은" + s1.totalCount);
                Log.v("태그", "09에서 mafiaNightCount의 값은" + s1.mafiaNightCount);
                Log.v("태그", "09에서 s1.victimID의 값은" + s1.victimOverlapID);
                Log.v("태그", "09에서 alreadyNight 의 값은 false로 초기화됨");
                // EditText값 받아서 executee에 넣기
                EditText editText1 = (EditText) findViewById(R.id.editTextVote);
                // EditText값 받는 거 ExceptionHandling해줘야함
                try {
                    inputText = editText1.getText().toString();
                    Log.v("태그", "inputText의 값은" + inputText);
                    executeeID = Integer.parseInt(inputText);//정수값 가져오기
                    Log.v("태그", "executeeID 값은" + executeeID);
                    s1.executeeID = executeeID;

                    if (executeeID > s1.playerArrayList.size()) {
                        StyleableToast.makeText(getApplicationContext(), "존재하지 않는 Player의 순번입니다", Toast.LENGTH_SHORT, R.style.mytoast).show();

                    } else if (executeeID == 0) {

                        Intent intent = new Intent(getApplicationContext(), Vote09_02.class);
                        startActivity(intent);
                        finish();
                    } else if (s1.playerArrayList.get(executeeID - 1) == null) {
                        StyleableToast.makeText(getApplicationContext(), "죽은 사람을 또 죽이는 것은 엄연한 고인 모독입니다"
                                , Toast.LENGTH_SHORT, R.style.mytoast).show();

                    } else { // 투표로 죽였을 때 무슨 화면으로 넘어가는지 에 대한 코드
                        playerData99.setDeath(executeeID);
                        Log.v("태그", executeeID + " 번 Player 투표로 죽음");
                        Log.v("태그", "09에서 투표로 죽인 후 totalCount의 값은" + s1.totalCount);

                        if (s1.mafia >= s1.citizen) {  // 게임 End 조건 코드
                            Intent intent2 = new Intent(getApplicationContext(), GameEnd11.class);
                            startActivity(intent2);
                            finish();

                        } else if (s1.mafia == 0) {
                            Intent intent2 = new Intent(getApplicationContext(), GameEnd11.class);
                            startActivity(intent2);
                            finish();

                        } else {
                            Intent intent = new Intent(getApplicationContext(), Vote09_02.class);
                            startActivity(intent);
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

    protected void onUserLeaveHint() {
        mp.pause();
        super.onUserLeaveHint();
    }

    public void onResume() {
        mp.start();
        super.onResume();
    }

    public void onDestroy() {
        mp.stop();
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("게임을 종료하시겠습니까?");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
                mp.stop();

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
