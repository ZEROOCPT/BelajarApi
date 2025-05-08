package com.example.belajarapi;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// MainActivity = tempat aplikasi dijalankan
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerViewTeams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pb = findViewById(R.id.pb);
        // Panggil method untuk ambil data tim dari API
        fetchTeams();
    }

    private void fetchTeams() {
        // Inisialisasi API client
        SportsApi api = ApiClient.getClient().create(SportsApi.class);
        Call<TeamResponse> call = api.getTeams("English Premier League");

        // Panggil API secara async
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    pb.setVisibility(View.GONE);

                    List<Team> teams = response.body().getTeams();
                    adapter = new TeamAdapter(teams);
                    recyclerView.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GAGAL "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", "Gagal mengambil data: " + t.getMessage());
            }
        });
    }
}