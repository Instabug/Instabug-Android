Change Log
==========

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