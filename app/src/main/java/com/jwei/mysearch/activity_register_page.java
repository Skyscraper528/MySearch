package com.jwei.mysearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jwei.mysearch.R;

public class activity_register_page extends AppCompatActivity {
    private Button register_next;
    private Button register_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        register_next = (Button) findViewById(R.id.register_next);
        register_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_register_page.this,activity_validate_page.class);
                startActivity(intent);
            }
        });

        register_back = (Button) findViewById(R.id.register_back);
        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_register_page.this,activity_login_page.class);
                startActivity(intent);
            }
        });
    }
}
