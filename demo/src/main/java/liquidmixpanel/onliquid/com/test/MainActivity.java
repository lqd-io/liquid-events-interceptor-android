package liquidmixpanel.onliquid.com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.analytics.HitBuilders;
import com.localytics.android.Localytics;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import io.lqd.sdk.Liquid;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    MixpanelAPI mixpanel;
    private HashMap<String, Object> mUserProps;
    private HashMap<String, String> mUserProps2;
    private JSONObject mTrackProps;
    private boolean mUseLocalytics = false;

    private static GoogleAnalytics gAnalytics;
    private static Tracker gTracker;
    private Integer choice;
    private Button secondScreenButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        choice = extras.getInt("choice", 1);

        secondScreenButton = (Button) findViewById(R.id.anotherActivity);
        if(choice == 1 || choice == 2)
            secondScreenButton.setEnabled(false);

        if(choice==1)
            setTitle("Localytics");
        else if(choice==2)
            setTitle("MixPanel");
        else
            setTitle("Google Analytics");

        mixpanel = MixpanelAPI.getInstance(this, "MIXPANEL_TOKEN");

        initTrackProps();
        initUserProps();
        initUserProps2();

        gAnalytics = GoogleAnalytics.getInstance(this);
        gTracker = gAnalytics.newTracker("GOOGLE_ANALYTICS_ID");  // e.g UA-68307540-7
        gTracker.enableAutoActivityTracking(true);

 }


    public void track(View v) {
        Log.d(TAG, "VIEW track");
        if (choice == 1)
            Localytics.tagEvent("lel");
        else if(choice == 2)
            mixpanel.track("lel");
        else if(choice == 3) {
            gTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Liquids")              //required
                .setAction("Pumped")                 //required
                .setLabel("In your blood")           //optional
                .setValue(0)                         //optional
                .build());
        }

    }

    public void trackProps(View v) {
        Log.d(TAG, "VIEW track props");

        if (choice == 1)
            Localytics.tagEvent("lel", mUserProps2);
        else if(choice == 2)
            mixpanel.track("lel2", mTrackProps);
        else if(choice == 3){
            gTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Liquids")              //required
                .setAction("Pumped")                 //required
                .setLabel("In your blood")           //optional
                .setValue(0)                         //optional
                .build());}
    }

    public void identify(View v) {
        Log.d(TAG, "VIEW identify");
        if (choice == 1)
            Localytics.setCustomerId("123");
        else if(choice == 2)
            mixpanel.identify("123");
        else if(choice == 3) {
            gTracker.set("&uid", "999");
            gTracker.send(new HitBuilders.EventBuilder().setCategory("UX").setAction("User Sign In").build());
        }
    }

    public void identifyPeople(View v) {
        Log.d(TAG, "VIEW identify people");
         if (choice == 1)
            Localytics.setCustomerId("123");
        else if(choice == 2)
            mixpanel.getPeople().identify("123");
        else if(choice == 3)
            gTracker.set("&uid", "999");
    }

    public void setProp(View v) {
        Log.d(TAG, "VIEW set prop");

        if (mUseLocalytics)
            Localytics.setProfileAttribute("gender", "female");
        else
            mixpanel.getPeople().set("gender", "female");
    }

    public void setPropHash(View v) {
        Log.d(TAG, "VIEW set prop hash");

        mixpanel.getPeople().setMap(mUserProps);
    }

    public void anotherActivity(View v){
        Intent intent=new Intent(MainActivity.this, SecondScreen.class);
        String str =  SecondScreen.class.getSimpleName();
        gTracker.setScreenName(str);
        startActivity(intent);

    }

    private void initTrackProps() {
        mTrackProps = new JSONObject();

        try {
            mTrackProps.put("Gender", "Female");
            mTrackProps.put("Plan", "Premium");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initUserProps() {
        mUserProps = new HashMap<>();
        mUserProps.put("gender", "female");
        mUserProps.put("plan", "premium");
    }

    private void initUserProps2() {
        mUserProps2 = new HashMap<>();
        mUserProps2.put("gender", "female");
        mUserProps2.put("plan", "premium");
    }
}
