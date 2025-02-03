package com.example.myapplication_library;

import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.triviasdk.Question;
import com.example.triviasdk.TriviaSdk;
//import com.github.AmitIshay.triviasdk.TriviaSdk;
//import com.github.AmitIshay.triviasdk.Question;

import com.example.triviasdk.TriviaSdk.QuestionCallback;

import java.util.List;
public class MainActivity extends AppCompatActivity {

    private Spinner spinnerDifficulty;
    private Button btnFetchQuestion, btnCheckAnswer, btnGenerateQuestion;
    private TextView tvQuestion;
    private EditText etUserAnswer;

    private Question currentQuestion;
    private List<Question> questionsList; // רשימת השאלות

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerDifficulty = findViewById(R.id.spinnerDifficulty);
        btnFetchQuestion = findViewById(R.id.btnFetchQuestion);
        tvQuestion = findViewById(R.id.tvQuestion);
        etUserAnswer = findViewById(R.id.etUserAnswer);
        btnCheckAnswer = findViewById(R.id.btnCheckAnswer);
        btnGenerateQuestion = findViewById(R.id.btnGenerateQuestion);

        // מאזינים לכפתורים
        btnFetchQuestion.setOnClickListener(v -> fetchQuestions());
        btnCheckAnswer.setOnClickListener(v -> checkAnswer());
        btnGenerateQuestion.setOnClickListener(v -> generateQuestionWithAI());
    }

    private void fetchQuestions() {
        String level = spinnerDifficulty.getSelectedItem().toString();

        TriviaSdk triviaSdk = new TriviaSdk();
        triviaSdk.fetchQuestions(level, false, new QuestionCallback() { // false - ללא AI
            @Override
            public void onSuccess(List<Question> questions) {
                if (questions != null && !questions.isEmpty()) {
                    questionsList = questions; // שמירת הרשימה
                    displayNextQuestion();
                } else {
                    tvQuestion.setText("No questions found.");
                }
            }

            @Override
            public void onError(String error) {
                tvQuestion.setText("Error: " + error);
                Log.e("MainActivity", "Error fetching question: " + error);
            }
        });
    }

    private void displayNextQuestion() {
        if (questionsList != null && !questionsList.isEmpty()) {
            currentQuestion = questionsList.remove(0); // בוחר את השאלה הראשונה ומסיר אותה מהרשימה
            tvQuestion.setText(currentQuestion.getQuestion());
            etUserAnswer.setText(""); // מאפס את שדה התשובה
        } else {
            tvQuestion.setText("No more questions at this level.");
        }
    }

    private void checkAnswer() {
        if (currentQuestion == null) {
            tvQuestion.setText("No question to answer.");
            return;
        }

        String userAnswer = etUserAnswer.getText().toString().trim();
        if (userAnswer.equalsIgnoreCase(currentQuestion.getAnswer())) {
            tvQuestion.setText("Correct! Moving to next question...");
            displayNextQuestion();
        } else {
            tvQuestion.setText("Incorrect. Try again!");
        }
    }

    private void generateQuestionWithAI() {
        String level = spinnerDifficulty.getSelectedItem().toString().toLowerCase(); // רמת הקושי שנבחרה

        TriviaSdk triviaSdk = new TriviaSdk();
        triviaSdk.fetchQuestions(level, true, new QuestionCallback() { // true - שימוש ב-AI
            @Override
            public void onSuccess(List<Question> questions) {
                if (questions != null && !questions.isEmpty()) {
                    int index = questions.size()-1;
                    Question aiQuestion = questions.get(0); // קבלת השאלה שנוצרה
                    Log.e("lglglglgglglg", aiQuestion.getQuestion().toString());
                    Log.e("lhlhlhlhhlhlh", questions.get(index).getQuestion().toString());

                    currentQuestion = aiQuestion;
                    tvQuestion.setText(aiQuestion.getQuestion()); // הצגת השאלה
                    etUserAnswer.setText(""); // מאפס את שדה התשובה
                } else {
                    tvQuestion.setText("No AI-generated questions found.");
                }
            }

            @Override
            public void onError(String error) {
                tvQuestion.setText("Error generating question: " + error);
                Log.e("MainActivity", "Error generating question with AI: " + error);
            }
        });
    }
}
