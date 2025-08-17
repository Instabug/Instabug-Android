# Changelog

## 16.0.0 (Aug 4, 2025)

### Breaking Changes

- Removes deprecated APIs.

### Features

- Adds support for detecting dialogs in our automatic Composable tracking approach.
- Adds an API to control the screen-capturing delay for better customization. The new API can be used as follows:
```
    // Can be used before building the SDK
    Instabug.setScreenshotCaptureDelay(int delay) // Capturing delay in milliseconds
```

### Enhancements

- Optimizes console log retrieval for fatal crashes to avoid potential ANRs on low-end devices.
- Optimizes `Activity.onDestroy()` logic to reduce chances of ANRs on low-end devices.
- Migrated termination snapshot serialization from Java to JSON for improved reliability.
- Adds a human-readable description to data sent with ANR reports for improved debugging.
- Adds dynamic width support for the video recording hint bubble to accommodate longer localized text.

### Bug Fixes

- Fixes a UI glitch in the bug reporting screen making it scrollable to support long forms and accessibility needs.
- Fixes a crash when using incompatible APM and Compose APM versions.

## 15.0.2 (Jul 6, 2025)

- Adds support for user steps and private views in Flutter apps.

## 15.0.1 (Jun 4, 2025)

- Improves the configurability of network logging on React Native apps.

## 15.0.0 (Jun 2, 2025)

- No breaking changes – Version bumped to 15.0.0 to align with iOS and cross-platform SDK versioning.
- Fixes an issue with incident-session linking.
- Fixes an issue causing Compose dialog destinations not to be recorded in repro steps.
- Optimizes session-ending operations on the main thread to prevent ANRs on low-end devices.
- Enhances screen recording behavior when the app goes to the background during an active recording session. 
- Fixes a typo in BR’s disclaimer copy in the Spanish locale.
- Fixes an issue that occurs under certain conditions when using WebViews.
- Fixes a behavior causing fatal Dart crashes to end the running session on Flutter apps.
- Various bug fixes and internal enhancements.