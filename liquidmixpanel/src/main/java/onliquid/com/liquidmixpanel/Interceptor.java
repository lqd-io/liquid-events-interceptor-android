package onliquid.com.liquidmixpanel;

/*
 * Copyright 2014-present Liquid Data Intelligence S.A.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import android.util.Log;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import io.lqd.sdk.Liquid;


@Aspect
public class Interceptor {
    private static final String TAG = Interceptor.class.getName();

    public static final String TRACK = "call(* com.mixpanel.android.mpmetrics.MixpanelAPI.track(..))";
    public static final String SET_PROP = "call(* com.mixpanel.android.mpmetrics.MixpanelAPI.People.set(..))";
    public static final String SET_PROPS = "call(* com.mixpanel.android.mpmetrics.MixpanelAPI.People.setMap(..))";
    public static final String IDENTIFY_PEOPLE = "call(* com.mixpanel.android.mpmetrics.MixpanelAPI.People.identify(..))";
    public static final String IDENTIFY = "call(* com.mixpanel.android.mpmetrics.MixpanelAPI.identify(..))";
    public static final String IDENTIFY_ALL = "(" + IDENTIFY_PEOPLE + ") || (" + IDENTIFY + ")";

    @Pointcut(TRACK)
    public void track() { }

    @Before("track()")
    public void onTrack(JoinPoint joinPoint) {
        if(joinPoint.getArgs().length == 1) {
            Liquid.getInstance().track((String) joinPoint.getArgs()[0]);
        } else {
            try {
                Liquid.getInstance().track((String) joinPoint.getArgs()[0], jsonToMap((JSONObject) joinPoint.getArgs()[1]));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, " Liquid: track");
    }

    @Pointcut(SET_PROP)
    public void setProp() { }

    @Before("setProp()")
    public void onsetProp(JoinPoint joinPoint) {
        Liquid.getInstance().setUserAttribute((String) joinPoint.getArgs()[0], joinPoint.getArgs()[1]);
        Log.d(TAG, "Liquid: set attribute");
    }

    @Pointcut(SET_PROPS)
    public void setProps() { }

    @Before("setProps()")
    public void onsetProps(JoinPoint joinPoint) {
        Liquid.getInstance().setUserAttributes((HashMap<String, Object>) joinPoint.getArgs()[0]);
        Log.d(TAG, "Liquid: set map");
    }

    @Pointcut(IDENTIFY_ALL)
    public void identify() { }

    @Before("identify()")
    public void onIdentify(JoinPoint joinPoint) {
        Liquid.getInstance().identifyUser((String) joinPoint.getArgs()[0]);
        Log.d(TAG, "Liquid: identify");
    }

    public static HashMap jsonToMap(JSONObject jObject ) throws JSONException {
        HashMap<String, Object> map = new HashMap<>();
        Iterator<?> keys = jObject.keys();

        while(keys.hasNext()){
            String key = (String)keys.next();
            String value = jObject.getString(key);
            map.put(key, value);
        }
        return map;
    }
}