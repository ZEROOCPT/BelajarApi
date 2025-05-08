package com.example.belajarapi;

import com.example.belajarapi.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface SportsApi {

    @GET("search_all_teams.php")
    Call<TeamResponse> getTeamsByLeague(@Query("l") String league);
    // @Query("l") = parameter "l" di URL, misalnya ?l=English Premier League

    // Endpoint: https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?s=Soccer&c=Spain
    // Method ini digunakan untuk mengambil daftar tim berdasarkan jenis olahraga dan negara
    @GET("search_all_teams.php")
    Call<TeamResponse> getTeamsByCountry(@Query("s") String sport, @Query("c") String country);
    // @Query("s") = parameter "s" di URL, misalnya ?s=Soccer
    // @Query("c") = parameter "c" di URL, misalnya &c=Spain


}