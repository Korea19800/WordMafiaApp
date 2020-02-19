package com.ulimbridge.wordmafia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Morning08_02sad extends AppCompatActivity {
    private static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning08_02sad_revised);

        mp = MediaPlayer.create(this, R.raw.morning_death_compilation08_02);
        mp.setLooping(false);// 반복 방지
        mp.start();

        TextView textView = (TextView) findViewById(R.id.textViewDeathReport);
        // 10_04Mafia한테 죽은 사람이 누군지 알려주는 메소드
        Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
        Log.v("태그", "08_02서 totalCount의 값은" + s1.totalCount);
        // 10_04에서 SetDeath하던걸 여기로 갖고옴
        PlayerData99 playerData99 = new PlayerData99();
        playerData99.setDeath(s1.victimOverlapID);
        textView.setText(s1.victimOverlapID + " 번 Player가 사망하셨습니다... ");

        Button button = findViewById(R.id.button8_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
                if (s1.mafia >= s1.citizen) {
                    Intent intent2 = new Intent(getApplicationContext(), GameEnd11.class);
                    startActivity(intent2);
                    finish();
                } else if (s1.mafia == 0) {
                    Intent intent3 = new Intent(getApplicationContext(), GameEnd11.class);
                    startActivity(intent3);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Morning08.class);
                    startActivity(intent);
                    finish();
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
                Log.v("태그", "마지막에 삭제된 playerArrayList의 객체수는 " + s1.playerArrayList.size());
                s1.playerArrayList.clear();
                Log.v("태그", "마지막에 삭제된 playerArrayList의 객체수는 " + s1.playerArrayList.size());
                s1.ListViewCategory.clear();
                Log.v("태그", "마지막에 삭제된 ListViewCategory의 객체수는 " + s1.ListViewCategory.size());
                s1.totalCount = 0;
                s1.count = 1;
                s1.mafiaNightCount = 0;

                mp.stop();
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

