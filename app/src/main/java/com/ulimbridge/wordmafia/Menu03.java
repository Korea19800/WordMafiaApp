package com.ulimbridge.wordmafia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

public class Menu03 extends AppCompatActivity {
    private static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu03);

        mp = MediaPlayer.create(this, R.raw.menu_music03);
        mp.setLooping(false); // 반복 방지
        mp.start();

        StyleableToast.makeText(getApplicationContext(), "소리를 키시면 더 재밌게 즐기실 수 있습니다", Toast.LENGTH_LONG, R.style.mytoast).show();

        Button b1 = (Button) findViewById(R.id.buttonTutorial);
        Button b2 = (Button) findViewById(R.id.buttonGameStart);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Tutorial04.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameSetting05.class);
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

        mp.stop();
        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
