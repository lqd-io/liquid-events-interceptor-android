Liquid-Mixpanel
==================

Forward mixpanel events to Liquid.


Install
-----

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
}
```

Usage

Make sure that you initialize Liquid singleton before initializing mixpanel

```java
Liquid.initialize(this, "LIQUID_TOKEN", true);

mixpanel = MixpanelAPI.getInstance(this, "MIXPANEL_TOKEN");
```
