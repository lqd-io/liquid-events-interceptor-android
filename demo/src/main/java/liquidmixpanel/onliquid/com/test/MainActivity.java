package liquidmixpanel.onliquid.com.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.localytics.android.Localytics;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    MixpanelAPI mixpanel;
    private HashMap<String, Object> mUserProps;
    private HashMap<String, String> mUserProps2;
    private JSONObject mTrackProps;
    private boolean mUseLocalytics = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUseLocalytics = true;

        mixpanel = MixpanelAPI.getInstance(this, "MIXPANEL_TOKEN");

        initTrackProps();
        initUserProps();
        initUserProps2();
    }

    public void track(View v) {
        Log.d(TAG, "VIEW track");
        if (mUseLocalytics)
            Localytics.tagEvent("lel");
        else
            mixpanel.track("lel");
    }

    public void trackProps(View v) {
        Log.d(TAG, "VIEW track props");

        if (mUseLocalytics)
            Localytics.tagEvent("lel", mUserProps2);
        else
            mixpanel.track("lel2", mTrackProps);
    }

    public void identify(View v) {
        Log.d(TAG, "VIEW identify");
        if (mUseLocalytics)
            Localytics.setCustomerId("123");
        else
            mixpanel.identify("123");
    }

    public void identifyPeople(View v) {
        Log.d(TAG, "VIEW identify people");
        if (mUseLocalytics)
            Localytics.setCustomerId("123");
        else
            mixpanel.getPeople().identify("123");
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
