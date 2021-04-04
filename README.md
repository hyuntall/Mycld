# Mycld
<h1>안드로이드 팀 프로젝트 1</h1>
<h3>그리드뷰 레이아웃을 활용한 간단한 달력 만들기</h3>
<h4>MonthViewActivity</h4>
<p> 이전 버튼에 사용되는 코드</p>
<pre>
<code>
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
  </code>
  </pre>
  <p> 다음 버튼에 사용되는 코드</p>
  <pre>
  <code>
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
  </code>
  </pre>
  
