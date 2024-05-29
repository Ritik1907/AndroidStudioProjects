package com.example.medico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btninsert, btntrigger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btninsert = findViewById(R.id.btn_insert);
        btntrigger = findViewById(R.id.btn_trigger);
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,insertdata.class);
                startActivity(it);
            }
        });
        btntrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trigger = new Intent(MainActivity.this,triggeractivity.class);
                startActivity(trigger);
            }
        });
    }
}