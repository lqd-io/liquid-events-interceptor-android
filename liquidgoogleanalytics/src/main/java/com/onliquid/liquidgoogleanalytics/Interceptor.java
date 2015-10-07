package com.onliquid.liquidgoogleanalytics;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;

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

        HashMap ashMap = (HashMap) joinPoint.getArgs()[0];

        String eventName;

        if (ashMap.containsKey("&el")) {
            eventName = ashMap.get("&ec") + ":" + ashMap.get("&ea") + ":" + ashMap.get("&el");}
        else{
            eventName = ashMap.get("&ec") + ":" + ashMap.get("&ea");
        }

        HashMap<String, Object> attributes = new HashMap<>();

        if(ashMap.containsKey("&ev")) {
            attributes.put("value", ashMap.get("&ev"));
            Liquid.getInstance().track(eventName, attributes);
        }
        else{
            Liquid.getInstance().track(eventName);
        }

    }


    @Pointcut(IDENTIFY)
    public void identify(){}

    @Before("identify()")
    public void onIdentify(JoinPoint joinPoint) {

        Liquid.getInstance().identifyUser((String) joinPoint.getArgs()[1]);
    }

    @Pointcut(SCREENNAME)
    public void screenname(){}

    @Before("screenname()")
    public void onScreenName(JoinPoint joinPoint){

        Liquid.getInstance().track((String) joinPoint.getArgs()[0]);
    }
}