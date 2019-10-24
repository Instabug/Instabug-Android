Change Log
==========

## [Deprecated] 
Please refer to our [releases](https://github.com/Instabug/Instabug-Android/releases) page for latest changes and releases.

Version 8.7.0 *(2019-09-16)*
----------------------------
* More Detailed Instabug Prompt:
    * To make sure your users pick the correct option when they invoke Instabug, we added a description to each option to help them decide whether their report is a bug, an improvement, or a support question.
* Nested Report Categories:
    * You can now benefit from flexible hierarchies while setting up your report categories. Add up to 4 levels deep and up to 20 categories in each level.
    * Each category consists of a title and a description. A description helps your users understand when to select a specific option.
    * With Nested Categories, you can reach a more efficient automated workflow. Auto-reply to your customers with the relevant answer they need based on their selection of categories and automatically route the report to the correct team.
    * You can find more details about how to benefit from the Report Categories here: https://docs.instabug.com/docs/reporting-categories
* Detecting app status during a crash (foreground/background)

Version 8.6.3 *(2019-08-31)*
----------------------------
* Introducing our new logo and branding.  Meet the new Instabug: the platform for Real-Time Contextual Insights \[Logo Updated]

Version 8.6.2 *(2019-08-27)*
----------------------------
* Bug fix: Fixing ANR issue

Version 8.6.1 *(2019-08-19)*
----------------------------
* Introducing our new logo and branding.  Meet the new Instabug: the platform for Real-Time Contextual Insights.

Version 8.6.0 *(2019-08-05)*
----------------------------
* We're updating the SDK flow when they pick "Ask a question" to match with "Report a bug" and "Suggest an improvement". As part of the change, some APIs have been deprecated. While they are still functional, we recommend that you start using the new APIs, as the deprecated ones will be completely removed in a future release. 
* Various bug fixes and stability improvements.


Version 8.5.0 *(2019-07-01)*
----------------------------
* With SDK V8.5, we’re introducing major improvements to the Repro Steps feature to make it more readable and usable for both technical and non-technical customers:
  1. Improving the algorithm we follow to capture text from the app’s UI to help your users understand which part of the app their user was interacting with.
  2. Capturing the icon when the user interacts with buttons or navigation items.
  3. Adding support to new UI components: switch, text fields, and sliders.
  4. Instabug can now capture all the user steps and repro steps out of the box without any required code addition on your side.

* We've removed a previous restriction on the App Version field in surveys targeting. You can now target surveys/announcements by app version no matter what format you use.
* Adding new API to capture screenshots Using MediaProjection to capture H/W accelerated views.
* Various Bug Fixes


Version 8.4.0 *(2019-05-26)*
----------------------------
* Putting the screenshots back in the Repro Steps.
* Support private views on Android
* Archiving Session Count In SDK
* Archiving Survey Interactions In SDK
* Archiving User Attributes In SDK
* Support Custom URL in App Version Update
* Add localization support to Surveys
* SDK improvements.


Version 8.3.1 *(2019-05-11)*
----------------------------
* Bug fix: Fixing an issue with proguard.

Version 8.3.0 *(2019-05-06)*
----------------------------
* Users now will be able to see the auto screen recordings before submitting a bug report.
* View hierarchy is back again.
* Auto screen recording will work with bug reporting only.
* Surveys & Announcements Localization support: you can now create localized surveys and announcements with support for over 20 languages.
* Various Bug Fixes.

Version 8.2.2 *(2019-04-11)*
----------------------------
* Bug fix: Removing duplicate color resource to solve a build error.

Version 8.2.1 *(2019-04-08)*
----------------------------
* Bug fix: welcome message title override key was overriding subtitle by wrong.

Version 8.2.0 *(2019-03-26)*
----------------------------
* Introducing new in-app survey templates: What’s New, Version Update, and App Ratings.

Version 8.1.2 *(2019-03-06)*
----------------------------
* Bug fix: making the Surveys setState API public.

Version 8.1.1 *(2019-03-03)*
----------------------------
* In this version of the Instabug SDK, various APIs have been deprecated. Please refer to this migration guide for detailed instructions on how to migrate from previous versions of the SDK to version 8.1.


Version 4.11.1 *(2018-02-27)*
----------------------------
* Fix issue with proguard

Version 4.11.0 *(2018-02-26)*
----------------------------
* Enhanced in-app notifications
* Extended bug report fields
* Enhanced bug report categories
* Several bug fixes

Version 4.10.2 *(2018-02-18)*
----------------------------
* Network performance monitoring
* Various bug fixes

Version 4.10.1 *(2018-02-14)*
----------------------------
* Fix issue with proguard

Version 4.10.0 *(2018-02-11)*
----------------------------
* Introducing Auto screen recording
* Fix Chinese localizations

Version 4.9.1 *(2018-01-29)*
----------------------------
* Various bug fixes

Version 4.9.0 *(2018-01-15)*
----------------------------
* Introduce visual user steps
* Various bug fixes

Version 4.8.1 *(2018-01-10)*
----------------------------
* Fix bug with setAttachmentTypesEnabled API
* Fix bug when closing prompt options fragment

Version 4.8.0 *(2018-01-07)*
----------------------------
* Introduce NPS surveys
* Fix issue with survey's submit button

Version 4.7.0 *(2018-01-01)*
----------------------------
* Opt in surveys
* Remove audio attachments
* Various bug fixes

Version 4.6.1 *(2018-10-26)*
----------------------------
* Fix build fail with enabled proguard

Version 4.6.0 *(2017-12-24)*
----------------------------
* Multiple Questions and Star Rating surveys
* Fix bug with Instabug when migrating to a new version from a disabled one
* Removing non-translatable strings to fix warnings while building APKs

Version 4.5.0 *(2017-12-10)*
----------------------------
* Enhance shape detection in annotation view
* Fix orientation issue with android 27
* Various bug fixes

Version 4.3.3 *(2017-10-30)*
----------------------------
* upgrade gradle version to 3.0
* Fix NPE when invoking instabug

Version 4.3.2 *(2017-10-24)*
----------------------------
* Fix issue in which user had to send application to background and back to foreground to enable invocation with event after enabling it in runtime.
* Fix an issue in which user events are not sent with PreSendingHandler.
* Fix other bugs and crashes.

Version 4.3.0 *(2017-09-22)*
----------------------------
* Fix issue in which user had to send application to background and back to foreground to enable invocation with event after enabling it in runtime.
* Fix an issue in which user events are not sent with PreSendingHandler.
* Fix other bugs and crashes.

Version 4.2.11 *(2017-09-12)*
----------------------------
* Fix screenshot issue with GLSurfaceView

Version 4.2.10 *(2017-08-06)*
----------------------------
* Introduce `addExtraReportField` API to add extra fields to the bug/feedback reports
* Fix email field hiding after calling `identifyUser` API

Version 4.2.9 *(2017-08-17)*
----------------------------
* New API to add file attachment as an array of bytes
* Fix issue with receiving notifications for already read messages
* Fix issue with taking screenshots for GLSurfaceView

Version 4.2.8 *(2017-08-10)*
----------------------------
* Adding the ability to differentiate between ui elements with the same type in user steps

Version 4.2.7 *(2017-08-06)*
----------------------------
* Introduce `setVideoRecordingFloatingButtonCorner` to change the position of the screen recording floating button
* Fix mic remains muted after screen recording
* Remove `No known package when getting name for resource number 0xffffffff` warning while collecting view hierarchy
* Fix user attributes issue with crash reporting

Version 4.2.6 *(2017-07-21)*
----------------------------
* Increase the length of InstabugLog to 10K
* Fix Logs not being sent with created crash report when added in PreSendingRunnable
* Force PreInvocation Runnable to run in the main thread

Version 4.2.5 *(2017-07-16)*
----------------------------
* Fix concurrency issue with logs
* Fix bug happens randomly when sending extra screenshot as first message

Version 4.2.4 *(2017-07-10)*
----------------------------
* Increase file attachments to 10 files
* Increase the length of network logs, instabug logs & user events to 10K
* File attachment can be up to 50 MB
* Increase file attachments to 10 files

Version 4.2.2 *(2017-06-28)*
----------------------------
* Fix issue with intro dialog not shown
* Optimize the user events insertions

Version 4.2.1 *(2017-06-23)*
----------------------------
* fix issue with pro-guard
* Fix in-App chat title in RTL views
* Fix bug with attaching image from gallery
* Fix bug with dragging the magnifier in annotation screen
* Fix wrong email is displayed in email field after deleting it by the user
* Fix an issue with screenshot on Oppo devices

Version 4.2.0 *(2017-06-19)*
----------------------------
* Introducing `Instabug.setPreInvocation()` API to run before invocation event
* Fix for screenshot gesture with Samsung Galaxy S8
* Fix bug with in-app messaging if the first message in conversation is an image
* Fix issue with RTL support
* Fix issue in Surveys with dark theme

Version 4.1.1 *(2017-06-05)*
----------------------------
* Fix for status bar color
* Fix for attachment view in dark theme

Version 4.1.0 *(2017-05-31)*
----------------------------
* Introducing `setRequestedOrientation`, `getRequestedOrientation` and `resetRequestedOrientation` for manually control the orientation of the SDK
* Adjust ImageViews in bug reporting view
* Fix for surveys re-appearing after being answered
* Other minor bug fixes

Version 4.0.10 *(2017-05-09)*
----------------------------
* Fix issue with status bar color
* Fix for Instabug views appearing in screenshots
* Fix “no chat with id” bug
* Other fixes in the core SDK

Version 4.0.8 *(2017-04-24)*
----------------------------
* Bug fixes

Version 4.0.7 *(2017-04-13)*
----------------------------
* Bug fixes

Version 4.0.6 *(2017-03-22)*
----------------------------
* Bug fixes

Version 4.0.3 *(2017-03-09)*
----------------------------
* Reduce memory footprint of the SDK
* Bug fixes

Version 4.0.2 *(2017-02-17)*
----------------------------
* Bug fixes

Version 4.0.1 *(2017-02-07)*
----------------------------
* Bug fixes

Version 4.0.0 *(2017-01-02)*
----------------------------
* You can now send surveys to your users through Instabug! Our mobile-optimized surveys let you get feedback from your users that help you make informed decisions about prioritizing your product roadmap. You can target users based on various criteria, including app version, the date they started using the app as well as custom user attributes and events.
* Bug reports now include a complete 3D view of your UI. You'll be able to debug your complete view hierarchy from your Instabug dashboard!
* We've redesigned our screen recording experience. You can now add voice notes while recording the screen, and while we're at it, we've thrown in a brand new, more-intuitive UI.
* Track custom user events that happen throughout your application with our new `Instabug.logUserEvent()`API.
* Bug fixes

Version 3.3.0 *(2017-01-04)*
----------------------------
* Introducing APIs for logging network requests performed by your application. Requests details along with their responses are going to be sent with each report. For more info, check: http://docs.instabug.com/docs/network-requests-logging-android
* Fix bug with Screenshot gesture

Version 3.2.0 *(2016-12-18)*
----------------------------

* `InstabugLog` now has multiple log levels that appear on your Instabug dashboard with different colors. Check `InstabugLog.d()`, `InstabugLog.i()`, `InstabugLog.v()`, `InstabugLog.e()` and `InstabugLog.w()`.
* You can now add custom user attributes to be sent along with your reports. Check `Instabug.setUserAttribute()`.
* We have added the ability to start a chat with users from your Instabug dashboard.

Version 3.1.4 *(2016-12-15)*
----------------------------

* Bug fixes

Version 3.1.3 *(2016-12-8)*
----------------------------

* Bug fixes

Version 3.1.1 *(2016-12-2)*
----------------------------

* Bug fixes

Version 3.1.0 *(2016-11-24)*
----------------------------

* Message Actions: allows you to add actionable buttons to messages you send to your users. Each button take users to a URL you specify through the Instabug dashboard. You can use Message Actions for things like asking your users to review your app on the Play Store, or to complete a short survey.
* Reporting Categories: allows you to show a predefined set of categories for users to choose from when reporting a bug or sending feedback. Selected category shows up on your Instabug dashboard as a tag to make filtering through issues easier.
* We have a new experience for adding attachments to bug reports and feedback.
* Bug fixes


Version 3.0.6 *(2016-11-10)*
----------------------------

* Add `setEnableSystemNotificationSound` & `setEnableInAppNotificationSound` for controlling notifications sound of the in-App chat
* Bug fixes

Version 3.0.5 *(2016-10-30)*
----------------------------

* Bug fixes

Version 3.0.4 *(2016-10-20)*
----------------------------

* Bug fixes

Version 3.0.0 *(2016-10-16)*
----------------------------

* We have completely redesigned how the SDK looks and given you even more customization options to better fit the style of your app.
* Added the ability to attach a screen recording to bug reports and conversations.
* Enhanced our in-app messaging experience to enable users to start conversations with your team right from your app, complete with screenshots, screen recordings and voice notes attachments support.
* We have localized the SDK into new languages. We now support a total of 20 languages.

Version 2.6.2 *(2016-09-09)*
----------------------------

 * Bug fixes


Version 2.6.1 *(2016-08-03)*
----------------------------

 * Bug fixes

Version 2.6.0 *(2016-08-03)*
----------------------------

 * Add `Instabug.invokeConversations()`
 * Add `IBGCustomTextPlaceHolder` & `Instabug.setCustomTextPlaceHolders(IBGCustomTextPlaceHolder ibgPlaceHolders)` for replacing instabug strings with custom strings.

Version 2.5.1 *(2016-07-11)*
----------------------------

 * Various bug fixes

Version 2.5.0 *(2016-06-27)*
----------------------------

 * Exclude GCM from Instabug SDK
 * Add `Instabug.setPushNotificationRegistrationToken()`, `Instabug.isInstabugNotification()` and `Instabug.showNotification()` for supporting in-app messaging’s notifications for GCM and FCM.

Version 2.4.0 *(2016-06-05)*
----------------------------

 * New: `Instabug.AddTags(Strings... )`, `Instabug.getTags()`, `Instabug.resetTags()` for adding custom tags for feedback and crash reports.
 * New: `onSdkInvokedCallback` & `onSdkDismissedCallback`
 * New: Czech localization
 * New: URLs are now clickable in in-app chat
 * Various bug fixes 
