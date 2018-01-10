Instabug Android SDK
========

An in-depth journey depicting all the ways to use [Instabug Android SDK][1].

Instabug is a bug reporting and in-app feedback tool that provides a seamless two-way communication with your users and testers. They can [easily report bugs](https://instabug.com/bug-reporting) and Instabug will automatically capture all the information you would need to debug and iterate faster.

For more information check [Instabug.com][2].

Usage
--------
Using Instabug is as easy as "Get ready, Get set, Go".

1. <b>Adding Instabug to your dependencies</b> (Getting set)

    Grab via Gradle:
    ```groovy
        implementation 'com.instabug.library:instabug:4.8.1'
    ```

    or via Maven: (if you're that kind of person :bowtie:)

    ```xml
        <dependency>
          <groupId>com.instabug.library</groupId>
          <artifactId>instabug</artifactId>
          <version>4.8.1</version>
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
                            .setInvocationEvent(InstabugInvocationEvent.FLOATING_BUTTON)
                            .build();
                    // ...
                }
        ```

For technical documentation check the [API reference][api_reference]

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


 [1]: https://docs.instabug.com/docs/android-integration
 [2]: https://instabug.com/
 [api_reference]: https://github.com/Instabug/android-sample/wiki

