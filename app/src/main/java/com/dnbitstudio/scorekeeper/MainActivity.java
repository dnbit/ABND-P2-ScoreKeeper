package com.dnbitstudio.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String RESET_VALUE = "0";
    private static final String SCORE_HOME = "score_home";
    private static final String YELLOW_CARD_HOME = "yellow_card_home";
    private static final String RED_CARD_HOME = "red_card_home";
    private static final String SCORE_VISITOR = "score_visitor";
    private static final String YELLOW_CARD_VISITOR = "yellow_card_visitor";
    private static final String RED_CARD_VISITOR = "red_card_visitor";
    private TextView mScoreHome;
    private Button mGoalButtonHome;
    private TextView mYellowCardHome;
    private TextView mRedCardHome;
    private TextView mScoreVisitor;
    private Button mGoalButtonVisitor;
    private TextView mYellowCardVisitor;
    private TextView mRedCardVisitor;
    private Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();

        if (savedInstanceState == null) {
            startNewMatch();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(SCORE_HOME, mScoreHome.getText().toString());
        outState.putString(YELLOW_CARD_HOME, mYellowCardHome.getText().toString());
        outState.putString(RED_CARD_HOME, mRedCardHome.getText().toString());
        outState.putString(SCORE_VISITOR, mScoreVisitor.getText().toString());
        outState.putString(YELLOW_CARD_VISITOR, mYellowCardVisitor.getText().toString());
        outState.putString(RED_CARD_VISITOR, mRedCardVisitor.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mScoreHome.setText(savedInstanceState.getString(SCORE_HOME));
        mYellowCardHome.setText(savedInstanceState.getString(YELLOW_CARD_HOME));
        mRedCardHome.setText(savedInstanceState.getString(RED_CARD_HOME));
        mScoreVisitor.setText(savedInstanceState.getString(SCORE_VISITOR));
        mYellowCardVisitor.setText(savedInstanceState.getString(YELLOW_CARD_VISITOR));
        mRedCardVisitor.setText(savedInstanceState.getString(RED_CARD_VISITOR));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goal_button_home:
                increaseCount(mScoreHome);
                break;
            case R.id.goal_button_visitor:
                increaseCount(mScoreVisitor);
                break;
            case R.id.reset_button:
                startNewMatch();
                break;
            default:
                if (view instanceof TextView) {
                    increaseCount((TextView) view);
                }
        }
    }

    private void initViews() {
        mScoreHome = findViewById(R.id.score_home);
        mGoalButtonHome = findViewById(R.id.goal_button_home);
        mYellowCardHome = findViewById(R.id.yellow_card_home);
        mRedCardHome = findViewById(R.id.red_card_home);
        mScoreVisitor = findViewById(R.id.score_visitor);
        mGoalButtonVisitor = findViewById(R.id.goal_button_visitor);
        mYellowCardVisitor = findViewById(R.id.yellow_card_visitor);
        mRedCardVisitor = findViewById(R.id.red_card_visitor);
        mResetButton = findViewById(R.id.reset_button);
    }

    private void setListeners() {
        mGoalButtonHome.setOnClickListener(this);
        mYellowCardHome.setOnClickListener(this);
        mRedCardHome.setOnClickListener(this);
        mGoalButtonVisitor.setOnClickListener(this);
        mYellowCardVisitor.setOnClickListener(this);
        mRedCardVisitor.setOnClickListener(this);
        mResetButton.setOnClickListener(this);
    }

    public void startNewMatch(){
        mScoreHome.setText(RESET_VALUE);
        mYellowCardHome.setText(RESET_VALUE);
        mRedCardHome.setText(RESET_VALUE);
        mScoreVisitor.setText(RESET_VALUE);
        mYellowCardVisitor.setText(RESET_VALUE);
        mRedCardVisitor.setText(RESET_VALUE);
    }

    public void increaseCount(TextView textView) {
        if (textView == null) {
            return;
        }

        int currentValue = Integer.parseInt(textView.getText().toString());
        textView.setText(String.valueOf(++currentValue));
    }
}
