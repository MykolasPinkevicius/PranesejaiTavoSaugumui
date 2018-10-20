//package saugumui.tavo.pranesimas.pranesimastavosaugumui;
//
//import android.app.Activity;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.util.Base64;
//import android.view.View;
//import android.widget.Button;
//
//import com.camerakit.CameraKitView;
//
//
//public class CamActivity extends Activity {
//
//    private CameraKitView cameraKitView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.camera_kit_view);
//        cameraKitView = findViewById(R.id.camera);
//
//
//
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//
//
//        cameraKitView.setCameraListener(new CameraKitView.CameraListener() {
//            @Override
//            public void onPictureTaken(byte[] picture) {
//                super.onPictureTaken(picture);
//
//                // Create a bitmap
//                Bitmap result = BitmapFactory.decodeByteArray(picture, 0, picture.length);
//            }
//        });
//
//        cameraKitView.captureImage();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        cameraKitView.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        cameraKitView.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        cameraKitView.onPause();
//        super.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        cameraKitView.onStop();
//        super.onStop();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//}
