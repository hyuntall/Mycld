package com.example.mycld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MonthViewActivity extends AppCompatActivity {
    int year;
    int month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar mCal; // 캘린더 선언
        Intent intent = getIntent();
        year = intent.getIntExtra("year", -1);
        month = intent.getIntExtra("month", -1);

        if (year == -1 || month == -1){ //아무것도 입력되지 않았을 때 현재 날짜 정보를 불러온다.
           year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH)+1;
        }

        TextView year_monthTv = findViewById(R.id.year_month); // 현재 년도와 월을 텍스트에 표시한다.
        year_monthTv.setText(year + "년 " + month + "월");

        Button Prevbtn = findViewById(R.id.previous);
        Button Nextbtn = findViewById(R.id.next);
        Prevbtn.setOnClickListener(new View.OnClickListener(){ // 이전 버튼을 클릭했을 때,
            @Override                                          // 현재 월 -1을 한다.
            public void onClick(View v){                       // 만약 표시된 월이 1월일 경우
                if (month == 1) {                              // 월 수를 12로 되돌리고 년도에 -1을 한다.
                    month = 13;
                    year--;
                }
                Intent intent = new Intent(getApplicationContext(), MonthViewActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month-1);

                startActivity(intent);
                finish();
            }
            });
        Nextbtn.setOnClickListener(new View.OnClickListener(){ // 다음 버튼을 클릭했을 때,
            @Override                                          // 현재 월 +1을 한다/
            public void onClick(View v){                       // 이 때 모듈러 연산을 사용하여
                if (month == 12)                               // 12월에서 +1이 될 경우 1월로 되돌아가고
                    year++;                                    // 년도에 +1을 한다.
                month = month%12+1;
                Intent intent = new Intent(getApplicationContext(), MonthViewActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);

                startActivity(intent);
                finish();
            }
        });
        ArrayList<String> dayList = new ArrayList<String>();

        mCal = Calendar.getInstance();
        // year 변수에 저장된 값을 Year에, month변수에 저장된 값을 Month에, 1을 Day에 넣는다.
        mCal.set(Integer.parseInt(String.valueOf(year)), Integer.parseInt(String.valueOf(month)) - 1, 1);
        // mCal의 날짜 정보는 year년 month월 1일이 된다.
        // mCal에 저장된 year년 month월 1일이 무슨 요일인지 판단하기 위해 Calendar.DAY_OF_WEEK 함수를 사용한다.
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 선언해둔 dayList에 공백 요소를 추가한다.
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }
        int dayMax = mCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //해당 달의 최대 일 수를 구하기 위해 .getActualMaximum(Calendar.DAY_OF_MONTH) 함수를 사용한다.
        for (int i = 1; i < dayMax+1; i++){ // 최대 일 수만큼 dayList에 요소를 추가한다.
            dayList.add(String.valueOf(i));
        }

        //어댑터 준비 (배열 객체 이용, simple_list_item_1 리소스 사용
        ArrayAdapter<String> adapt
                = new ArrayAdapter<String>(
                this,
                R.layout.item_month,R.id.item_gridview,
                dayList); // 기존에 simple_list_item_1 리소스를 사용하였으나 텍스트 정렬을 위해
                          // item_month 레이아웃을 만들어 그 내부에 만든 item_gridview를 사용하였다.

        // id를 바탕으로 화면 레이아웃에 정의된 GridView 객체 로딩
        GridView gridview = (GridView) findViewById(R.id.gridview);
        // 어댑터를 GridView 객체에 연결
        gridview.setAdapter(adapt);

        // 해당 날짜를 클릭하면 날짜 정보를 토스트 메세지로 출력하기 위한 코드이다.
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position >= dayNum-1) { //그리드뷰의 n번째 요소의 내용이 1일 이상일 때 메시지를 출력한다.
                    Toast.makeText(MonthViewActivity.this,
                            year + "." + month + "." + (position -(dayNum-2)),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}