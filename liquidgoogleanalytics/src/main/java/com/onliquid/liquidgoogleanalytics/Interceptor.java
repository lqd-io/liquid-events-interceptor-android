package com.onliquid.liquidgoogleanalytics;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

import io.lqd.sdk.Liquid;


/**
 * Created by alexis on 10/5/2015.
 */
@Aspect
public class Interceptor {
    private static final String TAG = Interceptor.class.getName();

    public static final String TRACK = "call(* com.google.android.gms.analytics.Tracker.send(..))";
    public static final String IDENTIFY = "call(* com.google.android.gms.analytics.Tracker.set(..))";
    public static final String SCREENNAME = "call (* com.google.android.gms.analytics.Tracker.setScreenName(..))";

    @Pointcut(TRACK)
    public void track() {
    }

    @Before("track()")
    public void onTrack(JoinPoint joinPoint) {

        HashMap hash = (HashMap) joinPoint.getArgs()[0];
        String eventName = hash.get("&ec") + ":" + hash.get("&ea");

        if (hash.containsKey("&el")) {
            eventName = eventName + ":" + hash.get("&el");
        }

        Map<String, Object> attributes = new HashMap<>();

        if(hash.containsKey("&ev")) {
            attributes.put("value", hash.get("&ev"));
            Liquid.getInstance().track(eventName, attributes);
        }
        else{
            Liquid.getInstance().track(eventName);
        }

    }

    @Pointcut(IDENTIFY)
    public void identify() {
    }

    @Before("identify()")
    public void onIdentify(JoinPoint joinPoint) {
        Liquid.getInstance().identifyUser((String) joinPoint.getArgs()[1]);
    }

    @Pointcut(SCREENNAME)
    public void screenname() {

    }

    @Before("screenname()")
    public void onScreenName(JoinPoint joinPoint) {
        Liquid.getInstance().track((String) joinPoint.getArgs()[0]);
    }
}
