Liquid Events Interceptor
==================

Mixpanel [![Maven Central](https://img.shields.io/maven-central/v/com.onliquid/liquid-mixpanel.svg)](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22liquid-mixpanel%22)

Localytics [![Maven Central](https://img.shields.io/maven-central/v/com.onliquid/liquid-localytics.svg)](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22liquid-localytics%22)

Forward mixpanel events to Liquid.


### Install

Add the plugin to your `buildscript`'s `dependencies` section:
```groovy
classpath 'com.uphyca.gradle:gradle-android-aspectj-plugin:0.9.+'
```

Apply the `android-aspectj` plugin:
```groovy
apply plugin: 'android-aspectj'
```

Add lib dependency

```groovy
dependencies {
  // Your Dependencies
  compile 'io.lqd:liquid-android:+@aar'


  compile 'com.onliquid:liquid-mixpanel:+@aar'
  // Or (not both)
  compile 'com.onliquid:liquid-localytics:+@aar'
}
```

### Usage

#### Mixpanel
Make sure that you initialize Liquid singleton before initializing mixpanel:

```java
Liquid.initialize(this, "LIQUID_TOKEN", true);

MixpanelAPI.getInstance(this, "MIXPANEL_TOKEN");
```
#### Localytics

```java
Liquid.initialize(this, "LIQUID_TOKEN", true);

registerActivityLifecycleCallbacks(
  new LocalyticsActivityLifecycleCallbacks(this, "LOCALYTICS_TOKEN")
);
```
