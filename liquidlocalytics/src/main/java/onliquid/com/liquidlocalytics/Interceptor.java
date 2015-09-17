package onliquid.com.liquidlocalytics;

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

import java.util.HashMap;

import io.lqd.sdk.Liquid;


@Aspect
public class Interceptor {
    private static final String TAG = Interceptor.class.getName();

    public static final String TRACK = "call(* com.localytics.android.Localytics.tagEvent(..))";
    public static final String SET_PROP = "call(* com.localytics.android.Localytics.setProfileAttribute(..))";
    public static final String IDENTIFY = "call(* com.localytics.android.Localytics.setCustomerId(..))";

    @Pointcut(TRACK)
    public void track() {
    }

    @Before("track()")
    public void onTrack(JoinPoint joinPoint) {

        if (joinPoint.getArgs().length == 1) {
            Liquid.getInstance().track((String) joinPoint.getArgs()[0]);
        } else {
            Liquid.getInstance().track((String) joinPoint.getArgs()[0], (HashMap<String, Object>) joinPoint.getArgs()[1]);
        }
        Log.d(TAG, " LOCALYTICS: track");
    }

    @Pointcut(SET_PROP)
    public void onPropSet() {
    }

    @Before("onPropSet()")
    public void onsetPropBefore(JoinPoint joinPoint) {
        Liquid.getInstance().setUserAttribute((String) joinPoint.getArgs()[0], joinPoint.getArgs()[1]);
        Log.d(TAG, "LOCALYTICS: set attribute");
    }

    @Pointcut(IDENTIFY)
    public void identify() {
    }

    @Before("identify()")
    public void onIdentify(JoinPoint joinPoint) {
        Log.d(TAG, "LOCALYTICS: identify");
        Liquid.getInstance().identifyUser((String) joinPoint.getArgs()[0]);
    }
}
