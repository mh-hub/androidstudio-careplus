package com.example.Careplus.BestMedicin;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Careplus.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;


public class BestSub1_sto_Activity extends AppCompatActivity {

    final int STEP_NONE = 0 ;
    final int STEP_AMOUNT = 1 ;
    final int STEP_FUNCTION = 2 ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_sto_sub1);

        Intent intent = new Intent(this.getIntent()); //성분페이지의 xml

        // AssetManager 객체 참조 획득.
        AssetManager am = getResources().getAssets() ;
        InputStream is = null ;

        try {
            String amount = null ;
            String function = null ;
            // String caution = null;

            // XML 파일 스트림 열기
            is = am.open("contact.xml") ;

            // XML 파서 초기화
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser() ;

            // XML 파서에 파일 스트림 지정.
            parser.setInput(is, "UTF-8") ;

            int eventType = parser.getEventType() ;
            int step = STEP_NONE ;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    // XML 데이터 시작
                } else if (eventType == XmlPullParser.START_TAG) {
                    String startTag = parser.getName() ;

                    if (startTag.equals("NTK_MTHD_sto1")) {
                        step = STEP_AMOUNT ;
                    } else if (startTag.equals("PRIMARY_FNCLTY_sto1")) {
                        step = STEP_FUNCTION ;
                    }


                    else {
                        step = STEP_NONE ;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    String endTag = parser.getName() ;
                    if (
                            (endTag.equals("NTK_MTHD_sto1") && step != STEP_AMOUNT) || (endTag.equals("PRIMARY_FNCLTY_sto1") && step != STEP_FUNCTION)

                    )
                    {
                        // TODO : error
                    }
                    step = STEP_NONE ;
                } else if (eventType == XmlPullParser.TEXT) {
                    String text = parser.getText() ;

                    if (step == STEP_AMOUNT) {
                        amount = text ;
                    } else if (step == STEP_FUNCTION) {
                        function = text ;
                    }

                }

                eventType = parser.next();
            }

            if ( amount == null || function == null) {
                // ERROR : XML is invalid.
            } else {


                TextView editTextUsage = (TextView) findViewById(R.id.text1);
                editTextUsage.setText(amount);

                TextView editTextEffect = (TextView) findViewById(R.id.text2);
                editTextEffect.setText(function);

            }
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }
    //하이퍼링크
    public void BuySto1Clicked(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.shopping.naver.com/search/all.nhn?origQuery=%EB%9D%BD%ED%86%A0%ED%95%8F%EC%83%9D%EC%9C%A0%EC%82%B0%EA%B7%A0%EA%B3%A8%EB%93%9C&pagingIndex=1&pagingSize=40&viewType=list&sort=rel&frm=NVSHTTL&query=%EB%9D%BD%ED%86%A0%ED%95%8F%EC%83%9D%EC%9C%A0%EC%82%B0%EA%B7%A0%EA%B3%A8%EB%93%9C"));
        startActivity(intent);
    }
}
