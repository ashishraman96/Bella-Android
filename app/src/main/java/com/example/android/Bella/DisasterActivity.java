package com.example.android.Bella;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import io.ghyeok.stickyswitch.widget.StickySwitch;

public class DisasterActivity extends AppCompatActivity {
    Toolbar toolbar;
    private static String TAG = PowergridActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    public String id;
    String info,nlat,nlng,lat,lng;
    int  l;
    String city;
    StickySwitch s1,s2,s3,s4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disaster);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        s1 = (StickySwitch)findViewById(R.id.toggleButton2);
        s2 = (StickySwitch)findViewById(R.id.toggleButton3);
        s3 = (StickySwitch)findViewById(R.id.toggleButton4);
        s4 = (StickySwitch)findViewById(R.id.toggleButton5);

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return ;
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return ;
            }
        });

        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return ;
            }
        });


        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return ;
            }
        });




        l = getIntent().getIntExtra("l",0);
        Log.d("l: ",Integer.toString(l));
        if(l==1) {
            info = "https://raw.githubusercontent.com/Bella-Assistant/Bella-Jsons/master/Tsunami.json";
            nlat = "41.775177";
            nlng = "-124.197371";
        } else if(l == 2) {
            info = "https://raw.githubusercontent.com/Bella-Assistant/Bella-Jsons/master/cyclone.json";
            nlat = "25.7959";
            nlng ="- 80.2870";
        } else if(l == 4) {
            info = "https://raw.githubusercontent.com/Bella-Assistant/Bella-Jsons/master/volcano.json";
            nlat = "19.539722";
            nlng = "-155.141389";
        } else if(l == 3) {
            info = "https://raw.githubusercontent.com/Bella-Assistant/Bella-Jsons/master/flood.json";
            nlat = "35.256527";
            nlng = "-80.964532";

        } else {
            info = "https://raw.githubusercontent.com/Bella-Assistant/Bella-Jsons/master/normal.json";

        }

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisasterActivity.super.onBackPressed();
            }
        });
        city = "Bangalore";

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        makeJsonObjectRequest();
    }

    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, info, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    city = response.getString("c_name");
                    lat = response.getString("lat");
                    lng =  response.getString("lng");
                    if (city.equals("Hawaii")) {
                        notif("Warning! Volcano Alert","ETA: 1 hr");
                        s4.setSwitchColor(getResources().getColor(R.color.red));
                        s4.setDirection(StickySwitch.Direction.RIGHT);
                    } else if (city.equals("Miami")) {
                        notif("Warning! Cyclone Alert","ETA: 1 hr");
                        s2.setSwitchColor(getResources().getColor(R.color.red));
                        s2.setDirection(StickySwitch.Direction.RIGHT);
                    } else if (city.equals("California")) {
                        notif("Warning! Tsunami Alert","ETA: 1 hr");
                        s1.setSwitchColor(getResources().getColor(R.color.red));
                        s1.setDirection(StickySwitch.Direction.RIGHT);
                    } else if (city.equals("North Carolina")) {
                        notif("Warning! Flood Alert","ETA: 1 hr");
                        s3.setSwitchColor(getResources().getColor(R.color.red));
                        s3.setDirection(StickySwitch.Direction.RIGHT);
                    } else {
                        notif("Mother Earth looks good","Have a nice day");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppCont.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private void notif(String title, String notif) {
        int notifId=1;
        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr="+lat+","+lng+"&daddr="+nlat+","+nlng);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
       // startActivity(intent);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.bella_launcher);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText("Navigate to a safe location ");
        mBuilder.setSubText(notif);
        mBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        NotificationManager mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder.setContentIntent(resultPendingIntent);
        mNM.notify(notifId,mBuilder.build());
    }
}