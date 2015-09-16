Liquid-Mixpanel
==================

Forward mixpanel events to Liquid.

Usage
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
  compile 'com.onliquid:liquid-mixpanel:+@aar'
}
```
