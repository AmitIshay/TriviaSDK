package com.example.triviasdk;

//import retrofit2.Call;
//import retrofit2.http.GET;
//import retrofit2.http.Query;
//import java.util.List;
//
//public interface TriviaApiService {
//    @GET("/getQuestions")
//    Call<List<Question>> getQuestions(@Query("level") String level);
//    @GET("/generateQuestion")
//    Call<Question> generateQuestion(@Query("level") String level);
//
//}

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaApiService {
//    @GET("/getQuestions")
//    Call<List<Question>> getQuestions(@Query("level") String level);
    @GET("/getQuestions")
    Call<List<Question>> getQuestions(@Query("level") String level, @Query("use_ai") String useAi);
}