package saugumui.tavo.pranesimas.pranesimastavosaugumui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.Collections;

public class CameraAttemptNumberThree extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static String encoded;

    private static String HOST = "http://158.129.225.30:8080";

    private Button sendButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_again);
        this.imageView = (ImageView) this.findViewById(R.id.imageView1);
        Button photoButton = (Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        this.sendButton = this.findViewById(R.id.sendButton);

        this.sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                sendReport();


            }
        });


//        Button mapButton = (Button) this.findViewById(R.id.mapButton);
//
//        mapButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMapsActivity();
//            }
//        });

        View mapMenuButton = this.findViewById(R.id.action_map);
        mapMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapsActivity();
            }
        });

        View mapMenuButton2 = this.findViewById(R.id.action_profile);
        mapMenuButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });

    }

    public CameraAttemptNumberThree getThis() {
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
            this.sendButton.setAlpha(1f);

        }
    }

    private void sendReport() {
        createImage();
    }

    private void createImage() {
        RequestQueue queue = Volley.newRequestQueue(getThis());

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                HOST + "/images",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String responseBody = new String(response.getBytes());
                        Gson gson = new Gson();
                        createReport(gson.fromJson(responseBody, Long.class));
                        // Display the first 500 characters of the response string.
//                                mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        mTextView.setText("That didn't work!");
                    }
                }
        ) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                String json = "{\"content\": \"" + encoded + "\"}";
//                        throw new RuntimeException(json);
                return json.getBytes();
//                        return encoded.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }


        };

        queue.add(stringRequest);
    }

    private void createReport(final Long imageId) {
        RequestQueue queue = Volley.newRequestQueue(getThis());

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                HOST + "/v1/disturbances",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                                mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        mTextView.setText("That didn't work!");
                    }
                }
        ) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                String json = gson.toJson(getDisturbanceDto(imageId));
                return json.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        queue.add(stringRequest);
    }

    private DisturbanceDto getDisturbanceDto(Long imageId) {
        return new DisturbanceDto("54.7224702,25.3378288", "WASTE", 1L, "Description", Collections.singletonList(imageId));
    }

    protected void openMapsActivity() {
        Intent myIntent = new Intent(this, MapsActivity.class);
        startActivity(myIntent);
    }

    protected void openProfileActivity() {
        Intent myIntent = new Intent(this, ProfileActivity.class);
        startActivity(myIntent);
    }
}

