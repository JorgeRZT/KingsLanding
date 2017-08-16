package com.pellsoft.kingslanding;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.Properties;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    WebView wb ;
    private Properties p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SharedPreferences settings = getSharedPreferences("prefs", 0);
            SharedPreferences.Editor editor = settings.edit();
            String guid = settings.getString("guid",null);
            Toast.makeText(this, guid, Toast.LENGTH_LONG).show();

            if(guid == null){
                guid = UUID.randomUUID().toString();
                editor.putString("guid",guid);
                editor.commit();
                Toast.makeText(this,"GUID "+settings.getString("guid",null), Toast.LENGTH_LONG).show();
            }


            wb = (WebView) findViewById(R.id.wb);

            WebSettings webSettings = wb.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wb.loadUrl("file:///android_asset/home.html?guid="+guid);

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());
        }
    }
}
