package com.example.triviasdk;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TriviaSdk {

    public interface QuestionCallback {
        void onSuccess(List<Question> questions);  // שינוי ל- List<Question> במקום Question[]
        void onError(String error);
    }

//    public void fetchQuestions(String level, QuestionCallback callback) {
//        TriviaApiService apiService = TriviaApiClient.getApiService();
//
//        apiService.getQuestions(level).enqueue(new Callback<List<Question>>() {  // שימוש ב- List<Question>
//            @Override
//            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    callback.onSuccess(response.body());  // שליחה של הרשימה
//                } else {
//                    callback.onError("Failed to fetch questions");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Question>> call, Throwable t) {
//                callback.onError(t.getMessage());
//                Log.e("TriviaSdk", "Error: " + t.getMessage());
//            }
//        });
//    }
public void fetchQuestions(String level, boolean useAi, QuestionCallback callback) {
    TriviaApiService apiService = TriviaApiClient.getApiService();

    // קריאה ל-API עם הפרמטר החדש use_ai
    apiService.getQuestions(level, useAi ? "true" : "false").enqueue(new Callback<List<Question>>() {
        @Override
        public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
            if (response.isSuccessful() && response.body() != null) {
                callback.onSuccess(response.body());
            } else {
                callback.onError("Failed to fetch questions");
            }
        }

        @Override
        public void onFailure(Call<List<Question>> call, Throwable t) {
            callback.onError(t.getMessage());
            Log.e("TriviaSdk", "Error: " + t.getMessage());
        }
    });
}



}