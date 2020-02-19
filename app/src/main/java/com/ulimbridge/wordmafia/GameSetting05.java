package com.ulimbridge.wordmafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;

public class GameSetting05 extends AppCompatActivity {

    private Spinner spinner2;
    private Spinner spinner_total;
    private Spinner spinner_mafia;
    ArrayList<Integer> arrayList;
    ArrayList<Integer> arrayList2;
    ArrayList<Integer> arrayList3;
    ArrayAdapter<Integer> arrayAdapter;
    ArrayAdapter<Integer> arrayAdapter2;
    ArrayAdapter<Integer> arrayAdapter3;
    TextView textView;
    TextView textView2;
    TextView textView3;

    Button button;

    // 1. Singleton 객체 소환
    Singleton_Setting99 s1 = Singleton_Setting99.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesetting05);

        textView = findViewById(R.id.textViewResult);// 총인원
        textView2 = findViewById(R.id.textViewResult2);// 마피아 수
        textView3 = findViewById(R.id.textViewResult3);// 시민 수
        button = findViewById(R.id.button5);

// 1. 시간제한 그냥 없애자
//        arrayList = new ArrayList<>();
//        arrayList.add(5);
//        arrayList.add(10);
//        arrayList.add(15);
//        arrayList.add(20);

        arrayList2 = new ArrayList<>();
        for (int i = 3; i <= 20; i++) {
            arrayList2.add(i);
        }

        arrayList3 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            arrayList3.add(i);
        }
        // 밑의 2개의 Listener 중 어떤 것도 작동하지 않을때 citizen수 맞추기 위해 대비한 코드
        s1.total = arrayList2.get(2); // spinner가 처음 맞춰줘 있는 값으로 s1.total, mafia 설정함!
        s1.mafia = arrayList3.get(1);
        s1.citizen = s1.total - s1.mafia;
        textView3.setText(s1.citizen + " 명"); // 이렇게 해야 citizen수를 spinner가 움직이든 말든 영향받기 않고 볼 수 있음!

//        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
//                android.R.layout.simple_spinner_dropdown_item,
//                arrayList);
        arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList2);
        arrayAdapter3 = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList3);

//        spinner2 = (Spinner) findViewById(R.id.spinner2);
//        // setAdapter통해 스피너에 어뎁터를 설정해줌! 즉, 스피너가 이때부터 실행된다고 보면됨
//        spinner2.setAdapter(arrayAdapter);
//        // setSelection은 초기 값을 지정해줌!
//        spinner2.setSelection(2);

//        // 이놈이 On 붙었으니 스피너에 대한 Listener임!
//
//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                // 이렇게 하면 설정값이  싱글톤의 타임에 저장되나?
//                s1.time = arrayList.get(i);
//                //
////                Toast.makeText(getApplicationContext(), arrayList.get(i) + " 초로 선택되었습니다.",
////                        Toast.LENGTH_SHORT).show();
//                StyleableToast.makeText(getApplicationContext(), arrayList.get(i) + " 초로 선택되었습니다.", Toast.LENGTH_SHORT, R.style.mytoast).show();
//                textView.setText(arrayList2.get(i) + " 명");
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        spinner_total = (Spinner) findViewById(R.id.spinner_total);
        spinner_total.setAdapter(arrayAdapter2);
        spinner_total.setSelection(2);
        spinner_total.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 요놈이 스피너의 리스너임!!
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s1.total = arrayList2.get(i);         // 이렇게 하면 총 인원이 싱글톤에 저장되는거?
                s1.citizen = s1.total - s1.mafia;
//                Toast.makeText(getApplicationContext(), arrayList2.get(i) + " 명이 선택되었습니다.",
//                        Toast.LENGTH_SHORT).show();
                StyleableToast.makeText(getApplicationContext(), arrayList2.get(i) + " 명이 선택되었습니다.", Toast.LENGTH_SHORT, R.style.mytoast).show();
                textView.setText(s1.total + " 명");
                textView2.setText(s1.mafia + " 명");
                textView3.setText(s1.citizen + " 명"); // 이렇게 해야 citizen수를 spinner가 움직이든 말든 영향받기 않고 볼 수 있음!
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText(" ");
            }
        });

        spinner_mafia = (Spinner) findViewById(R.id.spinner_mafia);
        spinner_mafia.setAdapter(arrayAdapter3);
        spinner_mafia.setSelection(1);
        spinner_mafia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                // 이렇게 하면 싱글톤에 마피아 수 저장되는거?

                s1.mafia = arrayList3.get(i);
                s1.citizen = s1.total - s1.mafia;
                StyleableToast.makeText(getApplicationContext(), arrayList3.get(i) + " 명이 선택되었습니다.", Toast.LENGTH_SHORT, R.style.mytoast).show();
                textView.setText(s1.total + " 명");
                textView2.setText(s1.mafia + " 명");
                textView3.setText(s1.citizen + " 명"); // 이렇게 해야 citizen수를 spinner가 움직이든 말든 영향받기 않고 볼 수 있음!
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView2.setText(" ");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s1.mafia >= s1.citizen) {
                    StyleableToast.makeText(getApplicationContext(), "마피아의 수는 시민 수보다 적어야 합니다", Toast.LENGTH_SHORT, R.style.mytoast).show();
//                    Toast.makeText(getApplicationContext(), " 마피아의 수는 시민 수보다 적어야 합니다.",
//                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), CategorySelection06.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
