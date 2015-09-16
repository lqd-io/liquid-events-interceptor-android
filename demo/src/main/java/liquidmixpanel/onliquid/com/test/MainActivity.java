package liquidmixpanel.onliquid.com.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.lqd.sdk.Liquid;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    MixpanelAPI mixpanel;
    private HashMap<String, Object> mUserProps;
    private JSONObject mTrackProps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Liquid.initialize(this, "YOUR_LIQUID_TOKEN", true);
        mixpanel = MixpanelAPI.getInstance(this, "YOUR_MIXPANEL_TOKEN");

        initTrackProps();
        initUserProps();
    }

    public void track(View v) {
        Log.d(TAG, "VIEW track");

        mixpanel.track("lel");
    }

    public void trackProps(View v) {
        Log.d(TAG, "VIEW track props");

        mixpanel.track("lel2", mTrackProps);
    }

    public void identify(View v) {
        Log.d(TAG, "VIEW identify");

        mixpanel.identify("123");
    }

    public void identifyPeople(View v) {
        Log.d(TAG, "VIEW identify people");

        mixpanel.getPeople().identify("123");
    }

    public void setProp(View v) {
        Log.d(TAG, "VIEW set prop");

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
}
