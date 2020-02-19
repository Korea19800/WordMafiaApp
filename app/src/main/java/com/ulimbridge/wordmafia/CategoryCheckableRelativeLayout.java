package com.ulimbridge.wordmafia;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.RelativeLayout;

public class CategoryCheckableRelativeLayout extends RelativeLayout implements Checkable {
    // 만약 CheckBox가 아닌 View를 추가한다면 아래의 변수 사용 가능.
    // private boolean mIsChecked ;

    public CategoryCheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // mIsChecked = false ;
    }

    /* boolean isCheck() : 현재 Checked 상태를 리턴.
     void setChecked(boolean checked) : Checked 상태를 checked 변수대로 설정.
     void toggle() : 현재 Checked 상태를 바꿈. (UI에 반영)*/

    /*1. isChecked에서 cb.isChecked는 체크됐으면 true, 안됐으면 false임 2.이 cb.isChecked가 setChecked로 넘어가 체크를 만듬 3.toggle은 1,2에서 설정한 값이 checked면
     * UI에 그림으로 표시함!
     * */

    @Override
    public boolean isChecked() {
        CheckBox cb = (CheckBox) findViewById(R.id.checkBoxMafia);
        return cb.isChecked();
        // return mIsChecked ;
    }

    @Override
    public void toggle() {
        CheckBox cb = (CheckBox) findViewById(R.id.checkBoxMafia);

        setChecked(cb.isChecked() ? false : true);
        // setChecked(mIsChecked ? false : true) ;
    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox cb = (CheckBox) findViewById(R.id.checkBoxMafia);

        if (cb.isChecked() != checked) {
            cb.setChecked(checked);
        }
        // CheckBox 가 아닌 View의 상태 변경.

        // 이 밑에 onClickListener 달아보기 > 그래야 id인식 가능능


    }

    public void CountId(){

        CategoryCheckableRelativeLayout layout1 = (CategoryCheckableRelativeLayout) findViewById(R.id.CheckableLayout);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.CheckableLayout :
                        Log.d("태그","click layout01");
                        break;
                }
            }
        };
        layout1.setOnClickListener(clickListener);
    }
}

