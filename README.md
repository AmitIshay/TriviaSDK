# Trivia SDK


### This is an Android SDK for integrating trivia question functionalities into your application. The SDK fetches trivia questions from a backend server, supports AI-generated questions, and organizes questions by difficulty level.

#### Features:


  - Fetch trivia questions based on difficulty level.
  - Option to enable AI-generated questions.
  - Simplified interface for developers.


#### Installation:


Step 1: Add the SDK to your project


  - Clone or download the SDK.
  - Copy the SDK files into your Android project under the com.example.triviasdk package.


Step 2: Add dependencies


  - Ensure the following dependencies are added to your build.gradle file:
    ```python
    dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    }


#### Usage:


Initialize the SDK


The SDK automatically initializes its API client when you call its methods. No additional setup is required.


Fetch Questions


To fetch trivia questions:
```python
TriviaSdk triviaSdk = new TriviaSdk();

triviaSdk.fetchQuestions("easy", false, new TriviaSdk.QuestionCallback() {
    @Override
    public void onSuccess(List<Question> questions) {
        // Handle the list of questions
        for (Question question : questions) {
            Log.d("Trivia", "Question: " + question.getQuestion());
        }
    }

    @Override
    public void onError(String error) {
        // Handle the error
        Log.e("Trivia", "Error: " + error);
    }
});



