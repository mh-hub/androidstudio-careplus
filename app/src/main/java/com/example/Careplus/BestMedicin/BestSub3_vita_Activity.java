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

public class BestSub3_vita_Activity extends AppCompatActivity {
    final int STEP_NONE = 0 ;
    final int STEP_AMOUNT = 1 ;
    final int STEP_FUNCTION = 2 ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_vi_sub3); //성분페이지의 xml

        Intent intent = new Intent(this.getIntent());

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

                    if (startTag.equals("NTK_MTHD_vi3")) {
                        step = STEP_AMOUNT ;
                    } else if (startTag.equals("PRIMARY_FNCLTY_vi3")) {
                        step = STEP_FUNCTION ;
                    }


                    else {
                        step = STEP_NONE ;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    String endTag = parser.getName() ;
                    if (
                            (endTag.equals("NTK_MTHD_vi3") && step != STEP_AMOUNT) || (endTag.equals("PRIMARY_FNCLTY_vi3") && step != STEP_FUNCTION)

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
    public void BuyVita3Clicked(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.shopping.naver.com/search/all.nhn?query=%EC%96%BC%EB%9D%BC%EC%9D%B4%EB%B8%8C+%EC%9B%90%EC%8A%A4%EB%8D%B0%EC%9D%BC%EB%A6%AC&cat_id=&frm=NVSHATC"));
        startActivity(intent);
    }
}
