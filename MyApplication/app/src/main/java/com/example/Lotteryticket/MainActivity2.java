package com.example.Lotteryticket;

import static android.widget.Toast.makeText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.clock.scratch.ScratchView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    private ScratchView scratchView;
    private ImageView[] imageViews= new ImageView[10];
    private TextView[] textViews= new TextView[10];
    private View view;
    private ImageView btn,btn1,btn2,btn3;
    private TextView textView;
    private Random random = new Random();
    private Random[] ran = new Random[5];
    int[] Winning=new int[5];
    int[] percent=new int[5];
    int[] check=new int[5];
    int[] tmp2=new int[5];
    String price[]=new String[5];
    boolean WinFlag=false;
    int i=0;
    int countReset=0;
    private long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView=findViewById(R.id.info);
        view = findViewById(R.id.view_1);
        view.bringToFront();
        btn = findViewById(R.id.imagebtn);
        btn1 = findViewById(R.id.imagebtn1);
        btn2 = findViewById(R.id.imagebtn2);
        btn3 = findViewById(R.id.imagebtn3);
        scratchView=findViewById(R.id.scratch_vie);
        for(int i=0;i<5;i++){
            tmp2[i]=0;
        }
        reset();
        /*int tmp=0,per=0;
        while (tmp<1811){
            WinFlag=false;
            reset();
            if(tmp%18==0){
                Log.i("ing", per+"%");
                for(int i=0;i<5;i++){
                    Log.i("ing", (i+1)+"등 : "+tmp2[i]);
                }
                per++;
            }
            tmp++;
        }*/

        scratchView.setEraserSize(30f);
        scratchView.setMaskColor(Color.rgb(36,185,149));
        btn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (v.getId()) {
                    case R.id.imagebtn:
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn.setImageResource(R.drawable.reset3);
                            Log.e("nn", "ok1");

                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            btn.setImageResource(R.drawable.reset2);
                            scratchView.reset();
                            WinFlag = false;
                            reset();
                        }
                        break;
                }
                return false;
            }
        });
        btn1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (v.getId()) {
                    case R.id.imagebtn1:
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn1.setImageResource(R.drawable.info3);
                            view.setVisibility(View.INVISIBLE);
                            textView.setTextColor(Color.rgb(255,255,255));
                            textView.setVisibility(View.VISIBLE);
                            textView.bringToFront();
                            Log.e("nn","ok2");
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            btn1.setImageResource(R.drawable.info2 );
                            textView.setVisibility(View.INVISIBLE);
                            view.setVisibility(View.VISIBLE);
                        }
                        break;
                }
                return false;
            }
        });
        btn2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (v.getId()) {
                    case R.id.imagebtn2:
                        Intent intent=new Intent(MainActivity2.this,rank2.class);
                        startActivity(intent);
                        finishAffinity();
                        break;
                }
                return false;
            }
        });
        btn3.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (v.getId()) {
                    case R.id.imagebtn3:
                        Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                        startActivity(intent);
                        finishAffinity();
                        break;
                }
                return false;
            }
        });
    }
    void reset(){
        percent[0] = 5000000;
        percent[1] = 100000;
        percent[2] = 1000;
        percent[3] = 40;
        percent[4] = 5;
        price[0] = "5 억원";
        price[1] = "2 천만원";
        price[2] = "10 만원";
        price[3] = "5 천원";
        price[4] = "1 천원";
        random.setSeed(System.currentTimeMillis());
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        int rank;
        for (i = 1; i < 11; i++) {
            int resImgview = getResources().getIdentifier("img" + i, "id", getPackageName());
            int resTxtview = getResources().getIdentifier("txt" + i, "id", getPackageName());
            imageViews[i - 1] = findViewById(resImgview);
            if (i < 6) {
                textViews[i - 1] = findViewById(resTxtview);
            }
        }
        i = 0;
        int posL = 21, posR = 21, floor = 11;

        while (i < 5) {
            ran[i] = new Random();
            ran[i].setSeed(System.currentTimeMillis()*i);
            Winning[i] = ran[i].nextInt(percent[i]) + 1;
            ran[i].setSeed(percent[i]);
            check[i] = ran[i].nextInt(percent[i]) + 1;
            Log.e("mm", check[i] + "//" + Winning[i]);
            int img = random.nextInt(20) + 1;
            int resDrawable = getResources().getIdentifier("p" + img, "drawable", getPackageName());

            if (Winning[i] == check[i] && WinFlag == false) {
                switch (i+1){
                    case 1:
                        rank=pref.getInt("1st",0);
                        rank++;
                        editor.putInt("1st", rank);
                        tmp2[0]++;
                        break;
                    case 2:
                        rank=pref.getInt("2nd",0);
                        rank++;
                        editor.putInt("2nd", rank);
                        tmp2[1]++;
                        break;
                    case 3:
                        rank=pref.getInt("3rd",0);
                        rank++;
                        editor.putInt("3rd", rank);
                        tmp2[2]++;
                        break;
                    case 4:
                        rank=pref.getInt("4th",0);
                        rank++;
                        editor.putInt("4th", rank);
                        tmp2[3]++;
                        break;
                    case 5:
                        rank=pref.getInt("5th",0);
                        rank++;
                        editor.putInt("5th", rank);
                        tmp2[4]++;
                        break;
                }

                WinFlag = true;
                int pos = random.nextInt(10);
                imageViews[pos].setImageResource(resDrawable);
                posL = pos;
                if (pos % 2 == 0) {
                    imageViews[pos + 1].setImageResource(resDrawable);
                    posR = pos + 1;
                } else {
                    imageViews[pos - 1].setImageResource(resDrawable);
                    posR = pos - 1;
                }
                floor = pos / 2;
                textViews[floor].setText(price[i]);
                Log.e("mm", "당첨위치: " + floor + " " +i);
                int a[] = new int[8];
                int posarray[] = new int[4];
                int otherPrice[] = new int[4];

                for (int j = 0; j < 8; j++) {
                    a[j] = random.nextInt(20) + 1;
                    for (int k = 0; k < j; k++) {
                        if (a[j] == a[k] || a[j] == img) {
                            j--;
                        }
                    }
                }
                for (int j = 0; j < 4; j++) {
                    posarray[j] = random.nextInt(5);
                    for (int k = 0; k < j; k++) {
                        if (posarray[j] == posarray[k]) {
                            j--;
                        }
                        if (posarray[j] == floor) {
                            j--;
                        }
                    }
                }
                for (int j = 0; j < 4; j++) {
                    otherPrice[j] = random.nextInt(5);
                    for (int k = 0; k < j; k++) {
                        if (otherPrice[j] == otherPrice[k]) {
                            j--;
                        }
                        if (otherPrice[j] == i) {
                            j--;
                        }
                    }
                   // Log.e("mm","금액"+otherPrice[j]);
                }
                int k = 0;
                for (int j = 0; j < 10; j++) {
                    if (j != posL && j != posR) {
                        resDrawable = getResources().getIdentifier("p" + a[k], "drawable", getPackageName());
                        imageViews[j].setImageResource(resDrawable);
                        k++;
                    }
                    if (j < 4) {
                        textViews[posarray[j]].setText(price[otherPrice[j]]);
                        Log.e("mm", posarray[j] + " , " + otherPrice[j]);
                    }
                }
            }
            i++;
        }

        if (!WinFlag) {
            int a[] = new int[20];
            int pos[] = new int[5];
            for (int j = 1; j < 11; j++) {
                a[j - 1] = random.nextInt(20) + 1;
                for (int k = 0; k < j - 1; k++) {
                    if (a[j - 1] == a[k]) {
                        j--;
                    }
                }
            }
            for (int j = 1; j < 6; j++) {
                pos[j - 1] = random.nextInt(5);
                for (int k = 0; k < j - 1; k++) {
                    if (pos[j - 1] == pos[k]) {
                        j--;
                    }
                }
            }
            for (int j = 0; j < 10; j++) {
                int resDrawable = getResources().getIdentifier("p" + a[j], "drawable", getPackageName());
                imageViews[j].setImageResource(resDrawable);
                if (j < 5) {
                    textViews[pos[j]].setText(price[j]);
                }
            }
            rank=pref.getInt("1st",0);
            editor.putInt("1st", rank);
            rank=pref.getInt("2nd",0);
            editor.putInt("2nd", rank);
            rank=pref.getInt("3rd",0);
            editor.putInt("3rd", rank);
            rank=pref.getInt("4th",0);
            editor.putInt("4th", rank);
            rank=pref.getInt("5th",0);
            editor.putInt("5th", rank);
        }


        countReset=pref.getInt("reset",0);
        Log.e("vv",String.valueOf(countReset));
        countReset+=1000;
        editor.putInt("reset", countReset);
        editor.commit();
    }

    public void onBackPressed() {
        //super.onBackPressed();
        // 기존 뒤로 가기 버튼의 기능을 막기 위해 주석 처리 또는 삭제
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지났으면 Toast 출력
        // 2500 milliseconds = 2.5 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = makeText(this, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else {
            toast.cancel();
            toast = makeText(this, "또 놀러오세요ㅠㅠ", Toast.LENGTH_SHORT);
            toast.show();
            finishAffinity();

        }
    }
}