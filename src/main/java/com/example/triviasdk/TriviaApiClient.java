package com.example.triviasdk;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TriviaApiClient {
    private static final String BASE_URL = "http://10.100.102.5:5000/"; // הכתובת של השרת
    private static TriviaApiService apiService;

    public static TriviaApiService getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(TriviaApiService.class);
        }
        return apiService;
    }
}
