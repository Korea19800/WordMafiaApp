package com.ulimbridge.wordmafia;

import android.util.Log;

public class PlayerData99 {

    // 데이터 모델의 Id들은 웬만해선 private을 설정하고 setter와 getter를 public으로 한 후 접근하는 게 좋다.
    int id; // id는 Player의 순번을 의미!!
    boolean death;
    boolean isMafia;
    boolean alreadyNight;
    Singleton_Setting99 s1 = Singleton_Setting99.getInstance();

    public PlayerData99() {
        ; // 이것 대신에 setter 이용해도 됨
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setMafia() {
        isMafia = true;
    }

    public void setDeath(int id) {
        // Vote09, MorningSad에 메소드 있음!!
        death = true;
        if (death == true) {
            if (s1.playerArrayList.get(id - 1).isMafia == true) {
                s1.mafia = s1.mafia - 1;
                s1.total = s1.total - 1;
                s1.totalCount = s1.totalCount - 1; // 투표또는 마피아가 사람 죽이면 반복시 사람수 만큼 반복하게 하기 위해 setDeath에서 -1 해줘야함
                // Q 만약 Night10_04에서 마피아가 시민 죽이면 그 시점에서 total이 줄어들어 시민화면이 넘어가는거 아님?? 그래서 08_02에 배치함
            } else if (s1.playerArrayList.get(id - 1).isMafia == false) {
                s1.citizen = s1.citizen - 1;
                s1.total = s1.total - 1;
                s1.totalCount = s1.totalCount - 1;
            }
            Log.v("태그", "마피아 수" + s1.mafia + "로 줄어들음");
            Log.v("태그", "시민 수" + s1.citizen + "로 줄어들음");
            // 죽을때 시민, 마피아 수 1개씩 줄여주기
            s1.playerArrayList.remove(id - 1);
            s1.playerArrayList.add(id - 1, null);
            // 이렇게 해야 객체를 제거하고도 그 빈공간에 다른 객체가 안밀려서 들어옴(ArrayList특성상 1개 제거하면 다른 1개가 밀려서 들어옴!)
            Log.v("태그", "Player " + id + " 죽음");
        }
    }

    // 중복 방지 코드 > 10_03에 10_04로 가거나 10_05로 간다면 alreadyNight=true로 만들게 하는 메소드임
    public void setOverlapCheck(int id) {
        s1.playerArrayList.get(id - 1).alreadyNight = true;
    }
}

