package com.example.cryptotracker3.ui.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cryptotracker3.R;

public class NewsWebActivity extends AppCompatActivity {
    NewsHeadlines headlines;
    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);

        txtContent = findViewById(R.id.text_content);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        txtContent.setText(headlines.getContent());
    }
}