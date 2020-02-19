package com.ulimbridge.wordmafia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;
import com.ulimbridge.wordmafia.R;

public class RoleSelection07_04 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection07_04);

        StyleableToast.makeText(getApplicationContext(), "소리를 키시면 더 재밌게 즐기실 수 있습니다", Toast.LENGTH_LONG, R.style.mytoast).show();

        Button button = findViewById(R.id.button7_4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Morning08.class);
                startActivity(intent2);
                finish();
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
