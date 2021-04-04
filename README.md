# Mycld
<h1>안드로이드 팀 프로젝트 1</h1>
<h3>그리드뷰 레이아웃을 활용한 간단한 달력 만들기</h3>
<h4>MonthViewActivity</h4>
<img src="https://user-images.githubusercontent.com/71054445/113508791-fbf48c00-958c-11eb-93a2-a950b5c65eab.png"  width="300" height="600">
<p>앱을 실행 시 현재 날짜의 달력이 표시된다.</p>
<hr>
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
 <img src="https://user-images.githubusercontent.com/71054445/113508815-20e8ff00-958d-11eb-8762-f43d3f38a629.png"  width="300" height="600">
 <p>이전 버튼 클릭시 이전 달의 달력이 표시된다.</p>
 <hr>
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
  <img src="https://user-images.githubusercontent.com/71054445/113508814-20e8ff00-958d-11eb-81ec-3eb281b2c5f3.png"  width="300" height="600">
<p>다음 버튼 클릭시 다음 달의 달력이 표시된다.</p>
<hr>
<p>일 수 클릭 시 해당 날짜를 토스트 메시지로 전송하는데에 사용되는 코드</p>
<pre>
<code>
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
</code>
</pre>
<img src="https://user-images.githubusercontent.com/71054445/113508813-1fb7d200-958d-11eb-97e7-ceb07fd0c148.jpg"  width="300" height="500">

