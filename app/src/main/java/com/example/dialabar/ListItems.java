package com.example.dialabar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListItems extends AppCompatActivity {
 Button addtocard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        addtocard = findViewById(R.id.addtocard);
        addtocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListItems.this,PaymentMethod.class);
                startActivity(i);
            }
        });
    }
}