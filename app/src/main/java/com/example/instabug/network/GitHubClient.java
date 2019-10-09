package com.example.instabug.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubClient {
   @GET("users/instabug/repos")
   Call<List<RepoList>> UserRepositories();
}