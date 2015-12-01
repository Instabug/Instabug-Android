Instabug Sample
========

An in-depth journey depicting all the ways to use [Instabug Android SDK][1].

For more information about Instabug check [our website][2].


Usage
--------

1. <b>Adding Instabug to your dependencies</b>

    Grab it via Gradle:
    ```groovy
    compile 'com.instabug.library:instabugsupport:1.7'
    ```
    or via Maven: (if you're that kind of person :bowtie:)
    ```xml
    <dependency>
      <groupId>com.instabug.library</groupId>
      <artifactId>instabugsupport</artifactId>
      <version>1.7</version>
    </dependency>
    ```

1. <b>Initialising Instabug</b>

    In your `Application` class add the following:
    ```
            @Override
            public void onCreate() {
                super.onCreate();
                // ...
                Instabug
                        .initialize(this, "<yourAppToken>")
                        .setInvocationEvent(Instabug.IBGInvocationEvent.IBGInvocationEventShake);
                // ...
            }
    ```

1. <b>You're set</b>
    
For Modifications and FAQs check the [wiki][wiki]

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
