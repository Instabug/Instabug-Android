Instabug Sample
========

An in-depth journey depicting all the ways to use [Instabug Android SDK][1].

For more information about Instabug check [our website][2].

Usage
--------
Using Instabug is as easy as "Get ready, Get set, Go".

1. <b>Determining which Instabug flavour to use</b> (Getting ready)
    * If your `minSdkVersion` is `14+`:
    
        Check `Above14` flavour
    
    * If your `minSdkVersion` is `10+`:

        Check `belowApi14` flavour

1. <b>Adding Instabug to your dependencies</b> (Getting set)

    Grab via Gradle:
    ```groovy
        compile 'com.instabug.library:instabug:3.0.6'
    ```

    or via Maven: (if you're that kind of person :bowtie:)

    ```xml
        <dependency>
          <groupId>com.instabug.library</groupId>
          <artifactId>instabug</artifactId>
          <version>3.0.6</version>
        </dependency>
    ```

1. <b>Using Instabug</b> (Good to go!)

    * Initializing Instabug:

        In your `Application` class add the following:
        ```
                @Override
                public void onCreate() {
                    super.onCreate();
                    // ...
                    new Instabug.Builder(this, "<YOUR_APP_TOKEN>")
                            .setInvocationEvent(InstabugInvocationEvent.InstabugInvocationEventFloatingButton)
                            .build();
                    // ...
                }
        ```

For technical documentation and FAQs check the [wiki][wiki]

License
=======

    Copyright 2012 Instabug, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: https://instabug.com/sdk-integration#android
 [2]: https://instabug.com/
 [wiki]: https://github.com/Instabug/android-sample/wiki

