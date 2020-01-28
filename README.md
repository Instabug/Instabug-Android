# Instabug Android SDK

![Twitter](https://img.shields.io/badge/twitter-@Instabug-blue.svg)

Instabug is an in-app feedback and bug reporting tool for mobile apps. With just a simple shake, your users or beta testers can [report bugs](https://instabug.com/bug-reporting) or send in-app feedback and the SDK will capture an environment snapshot of your user's device including all console logs, [server-side network requests](https://instabug.com/network-logging) and bug reproduction steps compiling all these details in one organised dashboard to help you debug and fix bugs faster. 

Instabug also provides you with a [reliable crash reporter](https://instabug.com/crash-reporting) that automatically captures a detailed report of the running environment, the different threads’ states, [the steps to reproduce the crash](https://instabug.com/user-steps), and the network request logs. All the data is captured automatically with no need for breadcrumbs, and you can always [reply back to your users](https://instabug.com/in-app-chat) and they will receive your messages within the app.

For more info, visit [Instabug.com](https://www.instabug.com).

## Installation

### Gradle

Add this line to your build.gradle file.

```groovy
implementation 'com.instabug.library:instabug:9.0.5'
```

## Usage

1. In your `Application` class add this line to your `onCreate` method.

```java
new Instabug.Builder(this, "APP_TOKEN").build();
```

## Notes

Some permissions are automatically added to your AndroidManifest.xml file. Some of them are required to be able to fetch some information like the network and wifi connection. Others are used to allow the user to attach images, videos, and audio recordings.

Generally, the permission request doesn't appear unless the user attempts to use any of the features requiring the permission. The only exception, if you set the invocation event to be Screenshot. Then, the storage permission will be requested when the application launches.

This behavior is happening with the screenshot invocation because there isn't any native event that tells the SDK that a screenshot has been captured. The only way to do it is to monitor the screenshots directory. The SDK is invoked once a screenshot is added to the directory while the application is active.

```xml
<uses-permission android:name=“android.permission.ACCESS_NETWORK_STATE” />
<uses-permission android:name=“android.permission.WRITE_EXTERNAL_STORAGE” />
<uses-permission android:name=“android.permission.READ_EXTERNAL_STORAGE” />
<uses-permission android:name=“android.permission.ACCESS_WIFI_STATE” />
<uses-permission android:name=“android.permission.RECORD_AUDIO” />
<uses-permission android:name=“android.permission.MODIFY_AUDIO_SETTINGS” />
```

You can remove any of the permissions if you are not willing to use the feature associated with it as in the following example.

```xml
<uses-permission android:name=“android.permission.WRITE_EXTERNAL_STORAGE” tools:node=“remove”/>
```

## More

You can also check out our [API Reference](https://docs.instabug.com/reference) for more detailed information about our SDK.
