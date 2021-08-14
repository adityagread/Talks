package com.example.talks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashboardActivity extends AppCompatActivity {

    EditText codetext;
    Button joinbutton,sharebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        codetext = findViewById(R.id.codetext);
        joinbutton = findViewById(R.id.joinbutton);
        sharebutton = findViewById(R.id.sharebutton);

        URL serverurl;

        try {
            serverurl = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultoptions = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverurl)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultoptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



        joinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(codetext.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(DashboardActivity.this,options);

            }
        });
    }
}