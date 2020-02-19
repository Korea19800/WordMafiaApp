package com.ulimbridge.wordmafia;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Singleton_Setting99 {

    // static 쓰면 싱글톤 쓰는 이유가 없다
    public int time;
    public int total; // playerArrayList.size()가 나중에 정확히 인원을 세지 못할 때 이용+ 09Vote에서 totalCount 다시 채워주는 역할 함
    public int mafia;
    public int citizen;
    // 밑의 count는 Role_Selection03에서 몇번째 플레이어인지 표시하기 위한 변수 ex> RoleSelection02 layout에  count번째 이런식으로 나오게 하려고
//    public int totalCount=total; 이거 굳이 안써도 될듯...
    public int count = 1;
    public int totalCount; // 값을 RoleSelection에서 지정함 + Vote09에서 다시 값을 채워줌 왜냐하면 10에서 다시 써야 하기 때문에
    //  07RoleSelection에서 0으로 초기화!
    public String keyCategory;
    public String CitizenkeyWord; // mafia-word, citizen-word
    public String MafiakeyWord; // mafia-word, citizen-word
    public int mafiaNightCount = 0;//10_03과 10_04에서 마피아의 중복 죽임을 막기 위한 변수+BackPress메소드에 게임종료시 0으로 초기화 시켜줘야 함!+2번째 턴 밤에도 중복막기 위해 09Vote에서 0으로 초기화 시킴
    public int victimOverlapID; // 10_04에서 마피아 2번 살인 방지 위해 mafiaNightCount와 함께 사용 + 09에서 0으로 리셋 +  이후 10_04에서 실제 죽인 사람의 id값으로 바뀜
    public int executeeID; // 09_02에 처형결과 띄우기 위한 변수
    ArrayList<PlayerData99> playerArrayList = new ArrayList<PlayerData99>();
    ArrayList<String> ListViewCategory = new ArrayList<String>();


    // 1. 생성자를 private으로 해 생성자 호출 제한
    private Singleton_Setting99() {

    }
    // 2. 자신의 인스턴스를 LazyHolder안에서 생성

    private static class LazyHolder {

        public static final Singleton_Setting99 INSTANCE = new Singleton_Setting99();

    }
    // 3. getInstance 메소드정의 : 이 메소드가  다른 데에서 객체 갖고오는 메소드인듯

    public static Singleton_Setting99 getInstance() {
        // 4. 생성한 인스턴스를 리턴
        return LazyHolder.INSTANCE;
    }

    // 이 init을 실행시켜줘야 직업이 배분됨!! RoleSelection07에서 직업 배정한다!!
    public void init() {
        Log.v("태그", " init 시행 됨 ");
        for (int i = 1; i <= total; i++) {
            PlayerData99 player = new PlayerData99();
            Log.v("태그", " 객체 생성됨 ");
            player.setID(i);  // id 자동으로 지정 id 1부터 시작
            Log.v("태그", " 객체 id " + i + " 입니다");
            player.death = false; // 생명 지정
            playerArrayList.add(player);
            Log.v("태그", " 객체 ArrayList에 추가함 ");
        }
    }

    public void JobSelection() {
        Log.v("태그", " JobSelection 시행됨 ");
        int a[] = new int[mafia]; // 내가 뽑을 숫자 만큼 int형 array선언
        Random r = new Random(); //랜덤 객체생성
        PlayerData99 player = new PlayerData99();
        Log.v("태그", " 직업 배정 위한 for문 돌림");
        for (int i = 0; i < mafia; i++)    // 마피아 명수만큼 뽑기위한 for문
        {
            a[i] = r.nextInt(total) + 1; //1~total 숫자중 랜덤으로 하나를 뽑아 a[0]~a[total]에 저장
            for (int j = 0; j < i; j++) //중복제거를 위한 for문
            {
                if (a[i] == a[j]) {
                    i--;
                }
            }
            // 이 부분서 잘못됨 예를 들어 3번플레이여가 마피아라면 3번이 setMafia되야 하는데 여기선 그냥 player class 에다가 setMafia 함
            if (playerArrayList.get(a[i] - 1).id == a[i]) { // 마피아를 고르는 if문
                playerArrayList.get(a[i] - 1).setMafia();
            }
            Log.v("태그", " 마피아의 id 는" + playerArrayList.get(a[i] - 1).id + "이며 a[i] 는 " + a[i] + "임");
        }
    }
}
