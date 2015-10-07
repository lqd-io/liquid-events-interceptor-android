package liquidmixpanel.onliquid.com.test;

import android.app.Application;
import android.nfc.Tag;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.localytics.android.LocalyticsActivityLifecycleCallbacks;

import io.lqd.sdk.Liquid;

public class DemoApp extends Application {

    private static GoogleAnalytics analytics;
    private static Tracker tracker;
    private static final String TAG = DemoApp.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();

        Liquid.initialize(this, "WF2mhts2blNXzZkUCO33poxMz8m1Z--q", true);


        registerActivityLifecycleCallbacks(new LocalyticsActivityLifecycleCallbacks(this, "LOCALYTICS_TOKEN"));
    }

}
