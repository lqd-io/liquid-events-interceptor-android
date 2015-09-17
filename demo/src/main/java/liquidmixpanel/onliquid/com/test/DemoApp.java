package liquidmixpanel.onliquid.com.test;

import android.app.Application;

import com.localytics.android.LocalyticsActivityLifecycleCallbacks;

import io.lqd.sdk.Liquid;

public class DemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Liquid.initialize(this, "LIQUID_TOKEN", true);

        registerActivityLifecycleCallbacks(new LocalyticsActivityLifecycleCallbacks(this, "LOCALYTICS_TOKEN") );
    }
}
