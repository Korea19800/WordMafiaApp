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

public class GameEnd11 extends AppCompatActivity {
    Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
    private static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end11);

        mp = MediaPlayer.create(this, R.raw.victory_ssen_guy11);
        mp.setLooping(false); // 반복 방지
        mp.start();

        Log.v("태그", "게임 종료");
        TextView t1 = (TextView) findViewById(R.id.textViewWhoWin);
        if (s1.mafia == 0) {
            t1.setText(" 시민 승 !! ");
        } else {
            t1.setText(" 마피아 승 !! ");
        }

        Button b1 = (Button) findViewById(R.id.buttonGoMenu);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop(); // Menu로 갈 때  멈추기

                s1.playerArrayList.clear();
                Log.v("태그", "마지막에 삭제된 playerArrayList의 객체수는 " + s1.playerArrayList.size());
                s1.ListViewCategory.clear();
                Log.v("태그", "마지막에 삭제된 ListViewCategory의 객체수는 " + s1.ListViewCategory.size());
                s1.totalCount = 0;
                s1.count = 1;
                s1.mafiaNightCount = 0;

                Intent intent = new Intent(getApplicationContext(), Menu03.class);
                startActivity(intent);
                finish();
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
        mp.stop(); // backPress시 멈추기
        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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