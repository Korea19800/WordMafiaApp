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

public class Night10 extends AppCompatActivity {
    private static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night10);

        mp = MediaPlayer.create(this, R.raw.night10_01narrator);
        mp.setLooping(false); // 반복 방지
        mp.start();

        Button button=findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Night10_02everybody.class);
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

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Singleton_Setting99 s1=Singleton_Setting99.getInstance();
                mp.pause();

                s1.playerArrayList.clear();
                Log.v("태그", "마지막에 삭제된 playerArrayList의 객체수는 "+ s1.playerArrayList.size());
                s1.ListViewCategory.clear();
                Log.v("태그", "마지막에 삭제된 ListViewCategory의 객체수는 "+ s1.ListViewCategory.size());
                s1.totalCount=0;
                s1.count=1;
                s1.mafiaNightCount=0;
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
