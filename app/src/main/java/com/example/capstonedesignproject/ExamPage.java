package com.example.capstonedesignproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamPage extends AppCompatActivity {

    private LoggedInUser loggedInUser;
    private static final String TAG = "ExamPage";
    private static final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Iiwicm9sZXMiOiJST0xFX1VTRVIiLCJpYXQiOjE2ODY2OTcwMTMsImV4cCI6MTY4Njc4MzQxM30.BucuSVNmTAOGY6vxktIj23pCRPuu24ab_i4aZSmlB5E";
    private static final String categoryNum = "20200101";
    private static final int quantity = 5;

    private TextView testNameTextView;
    private ImageView selectTestImageView;
    private TextView selectedTestTextView;
    private TextView questionTextView;
    private RadioGroup choiceRadioGroup;
    private RadioButton choiceARadioButton;
    private RadioButton choiceBRadioButton;
    private RadioButton choiceCRadioButton;
    private RadioButton choiceDRadioButton;
    private ImageButton nextButton;
    private ImageButton hBackButton;

    private TestInterface testInterface;

    private List<TestResponse.Result> questionList;
    private int currentQuestionIndex;
    private long paperIdx; // 문제지 인덱스
    private long userIdx; // 사용자 인덱스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_page);
        loggedInUser = new LoggedInUser(getLoggedInUserId());

        testNameTextView = findViewById(R.id.test_name);
        selectTestImageView = findViewById(R.id.select_test_view);
        questionTextView = findViewById(R.id.problem);
        choiceRadioGroup = findViewById(R.id.choiceRadio);
        choiceARadioButton = findViewById(R.id.choiceA);
        choiceBRadioButton = findViewById(R.id.choiceB);
        choiceCRadioButton = findViewById(R.id.choiceC);
        choiceDRadioButton = findViewById(R.id.choiceD);
        nextButton = findViewById(R.id.next);
        hBackButton = findViewById(R.id.h_back);

        testInterface = RetrofitClient.getTestApiService();

        hBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousQuestion();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });

        questionList = null;
        currentQuestionIndex = 0;

        loadQuestions();
    }


    private void loadQuestions() {
        Call<TestResponse> call = testInterface.getPapers(ACCESS_TOKEN, categoryNum, quantity);
        call.enqueue(new Callback<TestResponse>() {
            @Override
            public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {
                if (response.isSuccessful()) {
                    TestResponse testResponse = response.body();
                    if (testResponse != null && testResponse.isSuccess()) {
                        questionList = testResponse.getResults();
                        if (questionList != null && !questionList.isEmpty()) {
                            showCurrentQuestion();

                            // 문제 리스트가 로드되면 첫 번째 문제의 정보를 할당
                            TestResponse.Result firstQuestion = questionList.get(0);
                            paperIdx = firstQuestion.getPaperIdx();
                        } else {
                            Log.e(TAG, "문제를 불러오는 데 실패했습니다.");
                        }
                    } else {
                        Log.e(TAG, "통신 실패: " + testResponse.getMessage());
                    }
                } else {
                    Log.e(TAG, "통신 실패: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<TestResponse> call, Throwable t) {
                Log.e(TAG, "통신 실패: " + t.getMessage());
            }
        });
    }

    private void showCurrentQuestion() {
        TestResponse.Result currentQuestion = questionList.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestion());
        choiceARadioButton.setText(currentQuestion.getChoiceA());
        choiceBRadioButton.setText(currentQuestion.getChoiceB());
        choiceCRadioButton.setText(currentQuestion.getChoiceC());
        choiceDRadioButton.setText(currentQuestion.getChoiceD());

        // 이전에 선택한 라디오 버튼이 있다면 그대로 선택되도록 설정
        if (currentQuestion.getSelectedAnswer() != null) {
            switch (currentQuestion.getSelectedAnswer()) {
                case "1":
                    choiceARadioButton.setChecked(true);
                    break;
                case "2":
                    choiceBRadioButton.setChecked(true);
                    break;
                case "3":
                    choiceCRadioButton.setChecked(true);
                    break;
                case "4":
                    choiceDRadioButton.setChecked(true);
                    break;
            }
        } else {
            // 이전에 선택한 라디오 버튼이 없다면 초기화
            choiceRadioGroup.clearCheck();
        }
    }

    private void showNextQuestion() {
        saveSelectedAnswer();

        if (currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            showCurrentQuestion();
        } else {
            List<ResultDto> resultDtoList = createResultDtoList();

            // loggedInUser 객체를 사용하여 userIdx 값을 전달
            long loggedInUserId = loggedInUser.getUserIdx();
            userIdx = loggedInUserId;

            // ResultDto 객체 생성
            ResultDto resultDto = new ResultDto();
            resultDto.setUserIdx(loggedInUserId);

            // ResultDto 리스트 생성

            resultDtoList.add(resultDto);

            // ResultSaveRequest 객체 생성
            ResultSaveRequest request = new ResultSaveRequest(userIdx, resultDtoList);

            // ResultSaveActivity를 호출하여 결과 저장
            Intent intent = new Intent(ExamPage.this, ResultSaveActivity.class);
            intent.putExtra("resultSaveRequest", request);
            startActivity(intent);

            // ExamEnd 액티비티로 전환
            Intent endIntent = new Intent(ExamPage.this, ExamEnd.class);
            startActivity(endIntent);

            finish();
        }
    }


    private Long getLoggedInUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        long loggedInUserId = sharedPreferences.getLong("userIdx", 0L);

        return loggedInUserId;
    }

    private void showPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            saveSelectedAnswer();
            currentQuestionIndex--;
            showCurrentQuestion();
        } else {
            // 첫 번째 문제이므로 뒤로 갈 수 없음
            // 필요한 동작 추가
        }
    }

    private void saveSelectedAnswer() {
        TestResponse.Result currentQuestion = questionList.get(currentQuestionIndex);
        int checkedRadioButtonId = choiceRadioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(checkedRadioButtonId);
            String selectedAnswer = selectedRadioButton.getText().toString();
            switch (selectedAnswer) {
                case "1":
                    currentQuestion.setSelectedAnswer("1");
                    break;
                case "2":
                    currentQuestion.setSelectedAnswer("2");
                    break;
                case "3":
                    currentQuestion.setSelectedAnswer("3");
                    break;
                case "4":
                    currentQuestion.setSelectedAnswer("4");
                    break;
                default:
                    currentQuestion.setSelectedAnswer(null);
                    break;
            }

            // ResultDto 객체에 선택한 답변을 저장
            ResultDto resultDto = new ResultDto();
            resultDto.setPaperIdx(currentQuestion.getPaperIdx());
            resultDto.setUserIdx(getLoggedInUserId());
            resultDto.setCategoryNum(categoryNum);
            resultDto.setUserAnswer(selectedAnswer);
            resultDto.setCorrect(currentQuestion.getSelectedAnswer() != null && currentQuestion.getSelectedAnswer().equals(selectedAnswer));

            // 서버로 결과 전송
            ResultSaveRequest request = new ResultSaveRequest(getLoggedInUserId(), Collections.singletonList(resultDto));
            ResultSaveActivity.saveResult(this, request);
        } else {
            // 라디오 버튼이 선택되지 않은 경우
            currentQuestion.setSelectedAnswer(null);
        }
    }




    private List<ResultDto> createResultDtoList() {
        List<ResultDto> resultList = new ArrayList<>();

        for (TestResponse.Result question : questionList) {
            ResultDto resultDto = new ResultDto();
            resultDto.setPaperIdx(question.getPaperIdx());
            resultDto.setUserAnswer(question.getSelectedAnswer());
            int isCorrect = question.getCorrectAnswer().equals(question.getSelectedAnswer()) ? 1 : 0;
            resultDto.setCorrect(true);  // 1 (참)로 설정
            resultDto.setCorrect(false); // 0 (거짓)로 설정
            resultList.add(resultDto);
        }

        return resultList;
    }
}