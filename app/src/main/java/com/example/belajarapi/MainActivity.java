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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerViewTeams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Hubungkan ProgressBar dengan ID-nya
        pb = findViewById(R.id.pb);

        // Ambil nilai "checker" dari Intent
        // 0 = Liga Spanyol, 1 = tampilkan Premier League
        int checker = getIntent().getIntExtra("checker", 0);

        // Panggil  untuk ambil data dari API sesuai pilihan
        fetchTeams(checker);
    }

    // untuk mengambil data dari API
    private void fetchTeams(int checker) {
        SportsApi api = ApiClient.getClient().create(SportsApi.class); // Inisialisasi Retrofit

        Call<TeamResponse> call;

        // ngecek kalau 1 ke liga english selain itu ke spayol
        if (checker == 1) {
            call = api.getTeamsByLeague("English Premier League");
        } else {
            call = api.getTeamsByCountry("Soccer", "Spain");
        }

        //loading ui
        pb.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        // Jalankan API secara async
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                //if succeed
                if (response.isSuccessful() && response.body() != null) {
                    List<Team> teams = response.body().getTeams(); // Ambil daftar tim

                    // kebutuhan recylerview
                    adapter = new TeamAdapter(teams);

                    // tampil data ke RecyclerView
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    pb.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                // nampilin pesan error
                Toast.makeText(MainActivity.this, "GAGAL: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", "Gagal " + t.getMessage());
                pb.setVisibility(View.GONE); // Sembunyikan loading walaupun gagal
            }
        });
    }
}