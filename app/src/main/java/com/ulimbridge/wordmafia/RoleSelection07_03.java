package com.ulimbridge.wordmafia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RoleSelection07_03 extends AppCompatActivity {
    Singleton_Setting99 s1 = Singleton_Setting99.getInstance();
    PlayerData99 player = new PlayerData99();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection07_03);

        TextView t1 = (TextView) findViewById(R.id.textJobConfirm);
        TextView t2 = (TextView) findViewById(R.id.textHisWordIs);
        TextView t3 = (TextView) findViewById(R.id.textKeyWord);

        if (s1.playerArrayList.get(s1.count - 1).isMafia == true) {
            Log.v("태그", "마피아는 " + s1.playerArrayList.get(s1.count - 1) + " 이며 id는 " + (s1.count - 1) + " 이다");
            t1.setText(" 마피아 ");
        } else {
            t1.setText(" 시민 ");
        }
        if (s1.playerArrayList.get(s1.count - 1).isMafia == true) {
            Log.v("태그", "마피아는 " + s1.playerArrayList.get(s1.count - 1) + " 이며 id는 " + (s1.count - 1) + " 이다");
            t2.setText(" 마피아의 단어는 ");
        } else {
            t2.setText(" 시민의 단어는 ");
        }
        if (s1.playerArrayList.get(s1.count - 1).isMafia == true) {
            Log.v("태그", "마피아는 " + s1.playerArrayList.get(s1.count - 1) + " 이며 id는 " + (s1.count - 1) + " 이다");
            t3.setText(s1.MafiakeyWord);
        } else {
            t3.setText(s1.CitizenkeyWord);
        }

        Button button = findViewById(R.id.button7_3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.totalCount = s1.totalCount - 1;
                Log.v("태그", "totalCount의 크기는 " + s1.totalCount);
                // 버튼 몇번 눌렀나 세 줌.
                s1.count = s1.count + 1;
                if (s1.totalCount >= 1) {
                    Intent intent1 = new Intent(getApplicationContext(), RoleSelection07_02.class);
                    startActivity(intent1);
                    finish();
                } else {
                    Intent intent2 = new Intent(getApplicationContext(), RoleSelection07_04.class);
                    startActivity(intent2);
                    finish();
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
