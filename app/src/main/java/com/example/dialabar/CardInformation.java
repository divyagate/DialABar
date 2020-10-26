package com.example.dialabar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CardInformation extends AppCompatActivity {
Button placeorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_information);
        placeorder = findViewById(R.id.placeorder);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CardInformation.this,OrderPlaced.class);
                startActivity(i);
            }
        });
    }
}