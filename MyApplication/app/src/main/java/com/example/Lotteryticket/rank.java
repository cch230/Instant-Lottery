package com.example.Lotteryticket;

import androidx.appcompat.app.AppCompatActivity;

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

import static android.widget.Toast.makeText;

import java.text.DecimalFormat;

public class rank extends AppCompatActivity {
    private TextView[] textViews= new TextView[6];
    private TextView winPrice;
    private ImageView imageView;
    int[] ints=new int[6];
    int winPrice_tmp=0;
    private long backKeyPressedTime = 0;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        imageView=findViewById(R.id.imagebtn3);
        winPrice=findViewById(R.id.winPrice);
        DecimalFormat formatter = new DecimalFormat("###,###");

        ints[0]=pref.getInt("reset",0);
        ints[1]=pref.getInt("1st",0);
        ints[2]=pref.getInt("2nd",0);
        ints[3]=pref.getInt("3rd",0);
        ints[4]=pref.getInt("4th",0);
        ints[5]=pref.getInt("5th",0);

        for(int i=1;i<7;i++){
            int resTxtview = getResources().getIdentifier("rankTxt" + i, "id", getPackageName());
            textViews[i-1]=findViewById(resTxtview);
            if(i==1){
                textViews[i-1].setText(formatter.format(ints[i-1])+" 원");
            }
            else{
                if(i==2){
                    winPrice_tmp+=ints[i-1]*500000000;
                }
                else if(i==3){
                    winPrice_tmp+=ints[i-1]*20000000;
                }
                else if(i==4){
                    winPrice_tmp+=ints[i-1]*100000;
                }
                else if(i==5){
                    winPrice_tmp+=ints[i-1]*5000;
                }
                else if(i==6){
                    winPrice_tmp+=ints[i-1]*1000;
                }
                textViews[i-1].setText(formatter.format(ints[i-1])+" 번");
            }
            textViews[i-1].setTextColor(Color.rgb(36,185,149));
        }
        winPrice.setText("누적 당첨금액: "+formatter.format(winPrice_tmp)+" 원");
        winPrice.setTextColor(Color.rgb(36,185,149));
        imageView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (v.getId()) {
                    case R.id.imagebtn3:
                        Intent intent=new Intent(rank.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return false;
            }
        });
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