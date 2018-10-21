package saugumui.tavo.pranesimas.pranesimastavosaugumui;

import android.app.Activity;
import android.content.Intent;
import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileActivity extends Activity {

    private ProgressBar progress;
    private TextView currentLevel;

    private  int expToAdd = 5400;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        View mapMenuButton = this.findViewById(R.id.action_map);
        mapMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapActivity();
            }
        });

        View mapMenuButton2 = this.findViewById(R.id.action_report);
        mapMenuButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReportActivity();
            }
        });

        progress = this.findViewById(R.id.progressBar2);
        currentLevel = this.findViewById(R.id.currentLevelText);

        checkIfNeedToAddExp();
        setLevelText(1);

    }

    protected void openMapActivity() {
        Intent myIntent = new Intent(this, MapsActivity.class);
        startActivity(myIntent);
    }

    protected void openReportActivity() {
        Intent myIntent = new Intent(this, CameraAttemptNumberThree.class);
        startActivity(myIntent);
    }

    private void checkIfNeedToAddExp() {
        int currentExp = this.progress.getProgress();
        if (this.expToAdd > 0) {
            if (currentExp + this.expToAdd > 1000) {
                this.expToAdd = this.expToAdd - (1000 - currentExp);
                doAnimation(1000);
            } else {
                doAnimation(currentExp + this.expToAdd);
                this.expToAdd = 0;
            }
        }
    }

    private void addExp(int expAmount) {
        int currentExp = this.progress.getProgress();

        int resultExp = currentExp + expAmount;

        if (resultExp >= 1000) {
            int oldExp = currentExp;
            currentExp = currentExp - 1000;
        }
    }

    private void doAnimation(int expAmount) {
        final int currentExp = this.progress.getProgress();

//        final int resultExp = currentExp + expAmount;
//
//        if (resultExp > 1000) {
////            int oldExp = currentExp;
//           resultExp = 1000;
////            currentExp = currentExp - 1000;
//        }

        ProgressBarAnimation anim = new ProgressBarAnimation(progress, currentExp, expAmount) ;

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (getProgress().getProgress() == 1000) {
                    getProgress().setProgress(0);
                    showLevelUpAnimation();
                    addLevel();
                }
                checkIfNeedToAddExp();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim.setDuration(2000);
        progress.startAnimation(anim);
    }

    private void showLevelUpAnimation() {
        Toast toast = Toast.makeText(this, "level up!", Toast.LENGTH_SHORT);
        toast.show();
    }

    private ProgressBar getProgress() {
        return this.progress;
    }

    private void addLevel() {
        setLevelText(Integer.valueOf(currentLevel.getText().toString()) + 1);
    }

    private void setLevelText(int level) {
        currentLevel.setText(String.valueOf(level));
    }

    private void getCitizen() {

    }

//    private List<NormalDisturbanceDto> getReports() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//
////        final List<NormalDisturbanceDto> NormalDisturbanceDtos = new ArrayList<>();
//
//        StringRequest stringRequest = new StringRequest(
//                Request.Method.GET,
//                HOST + "/v1/good-citizens/1",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        String responseBody = new String(response.getBytes());
//                        Gson gson = new Gson();
//                        GoodCitizen goodCitizen = gson.fromJson(responseBody, GoodCitizen.class);
//                        expToAdd()
//                        addExp();
////                        setLevelText(goodCitizen.getExp() / 1000);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        mTextView.setText("That didn't work!");
//                    }
//                }
//        );
//
//        queue.add(stringRequest);
//
//        return NormalDisturbanceDtos;
////    }
//
//    private void setExpToAdd(int expToAdd) {
//        this.expToAdd = expToAdd;
//    }
}
