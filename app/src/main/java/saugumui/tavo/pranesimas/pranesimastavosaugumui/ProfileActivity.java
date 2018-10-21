package saugumui.tavo.pranesimas.pranesimastavosaugumui;

import android.app.Activity;
import android.content.Intent;
import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;

public class ProfileActivity extends Activity {

    private ProgressBar progress;


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


    }

    protected void openMapActivity() {
        Intent myIntent = new Intent(this, MapsActivity.class);
        startActivity(myIntent);
    }

    protected void openReportActivity() {
        Intent myIntent = new Intent(this, CameraAttemptNumberThree.class);
        startActivity(myIntent);
    }

    private void addExp(int expAmount) {
        int currentExp = this.progress.getProgress();

        int resultExp = currentExp + expAmount;

        if (resultExp >= 1000) {
        }
    }

    private void doAnimation() {
        ProgressBarAnimation anim = new ProgressBarAnimation(progress, currentExp, 1000) ;

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim.setDuration(5000);
        progress.startAnimation(anim);
    }
}
