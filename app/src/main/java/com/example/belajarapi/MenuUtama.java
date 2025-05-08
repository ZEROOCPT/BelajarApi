package com.example.belajarapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MenuUtama extends AppCompatActivity {

    private Button btnliga;
    private Button btnpremire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_utama);

        btnliga = findViewById(R.id.btnliga);
        btnpremire = findViewById(R.id.btnpremier);

        btnliga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuUtama.this, MainActivity.class);
                intent.putExtra("checker", 0); // premier
                startActivity(intent);
            }
        });

        btnpremire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuUtama.this, MainActivity.class);
                intent.putExtra("checker", 1); // spain
                startActivity(intent);
            }
        });

    }
}