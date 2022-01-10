package com.example.cryptotracker3.ui.news;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptotracker3.R;

public class NewsWebActivity extends AppCompatActivity {
    NewsHeadlines headlines;
    //TextView txtContent;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);

        //txtContent = findViewById(R.id.text_content);
        webView = findViewById(R.id.webpage);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        //txtContent.setText(headlines.getContent());

        webView.loadUrl(headlines.getUrl());


    }
}