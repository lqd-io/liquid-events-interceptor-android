Liquid Events Interceptor
==================

[![Maven Central](https://img.shields.io/maven-central/v/com.onliquid/liquid-mixpanel.svg)](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22liquid-mixpanel%22) Mixpanel

[![Maven Central](https://img.shields.io/maven-central/v/com.onliquid/liquid-localytics.svg)](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22liquid-localytics%22) Localytics

[![Maven Central](https://img.shields.io/maven-central/v/com.onliquid/liquid-googleanalytics.svg)](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22liquid-googleanalytics%22) Google Analytics

Quick way to integrate Liquid basic analytics using your current mixpanel/localytics/google analytics integration.



### Install

1. Add the plugin to your `buildscript's` `dependencies` section:
```groovy
classpath 'com.uphyca.gradle:gradle-android-aspectj-plugin:0.9.+'
```

2. Apply the `android-aspectj` plugin:
```groovy
apply plugin: 'android-aspectj'
```

3. Add Liquid dependency
```groovy
dependencies {
  // Your Dependencies
  compile 'io.lqd:liquid-android:+@aar'
}
```

### Usage

#### Mixpanel
1. Add mixpanel dependency:
```groovy
compile 'com.onliquid:liquid-mixpanel:+@aar'
```

2. Make sure that you initialize Liquid singleton before initializing mixpanel:

```java
Liquid.initialize(this, "LIQUID_TOKEN", true);

MixpanelAPI.getInstance(this, "MIXPANEL_TOKEN");
```

#### Localytics
1. Add localytics dependency:
```groovy
compile 'com.onliquid:liquid-localytics:+@aar'
```

2. Make sure that you initialize Liquid singleton before initializing localytics:

```java
Liquid.initialize(this, "LIQUID_TOKEN", true);

registerActivityLifecycleCallbacks(
  new LocalyticsActivityLifecycleCallbacks(this, "LOCALYTICS_TOKEN")
);
```


#### Google Analytics
1. Add Google Analytics dependency:
```groovy
compile 'com.onliquid:liquid-googleanalytics:+@aar'
```

2. Make sure that you initialize Liquid singleton before send data to google analytics

```java
Liquid.initialize(this, "LIQUID_TOKEN", true);
```



### Full integration

To use all the Liquid features please integrate our  [SDK](https://github.com/lqd-io/liquid-sdk-android).

We recommend you to read the full [documentation](https://www.onliquid.com/documentation/android).


# Author

Liquid Data Intelligence, S.A.

# License

Liquid is available under the Apache license. See the LICENSE file for more info.
