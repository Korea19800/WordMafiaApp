package com.ulimbridge.wordmafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity01 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. 이놈들이 타이틀바 없애는 코드들인듯
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main01);

        // 2. 밑은 splash 코드
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 1500); // 1000ms=1sec
    }

    // drawable에서 splash 파일 지웠다...

    private class splashhandler implements Runnable {

        public void run() {
            startActivity(new Intent(getApplication(), Menu03.class));
            MainActivity01.this.finish();
        }
    }
}
