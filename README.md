# 

## JavaFX References

* https://openjfx.io/openjfx-docs/#maven

### Maven

```bash
mvn clean javafx:run

set native.image=1 && mvn clean package -DskipTests -Pnative -Dprism.order=sw native:compile
```

## Java FX and Spring Boot playground

* blog: https://kenjdavidson.com/writing/2022/03/21/javafx-and-spring-boot
* code: https://github.com/kenjdavidson/gspro-connector



## Using GraalVM’s Native Image

The jpackage tool lets you build native applications for specific operating systems. The Java runtime is bundled with the application, and when the native application is executed, it will internally use the Java runtime to execute the bytecodes. Typically, the Java runtime contains a Just In Time (JIT) compiler that compiles Java bytecode to native code.

Another option for building a native application moves the compilation step to build time. With GraalVM’s Native Image, the Java code is compiled Ahead Of Time (AOT). This means the Java bytecode is compiled to native code before it is executed and before it is bundled into an application.

Although the GraalVM project (“Run Programs Faster Anywhere”) has been active for many years, it only recently became available as a product. GraalVM is still evolving, and parts of it are integrated with the OpenJDK project and vice versa. We recommend that you regularly keep an eye on the https://graalvm.org website and on the GitHub site for the open source code at https://github.com/oracle/graal.

While GraalVM provides the AOT compiler that translates Java bytecode into native code for a given platform, there are more actions needed in order to link the program code into an executable. A GraalVM maven plugin exists for Java applications on any platform, but this doesn’t deal with JavaFX applications. Fortunately, there are open source tools available that help developers achieve this. The GluonFX plugin (from gluonhq.com) lets developers create native images for Linux, macOS, and Windows based on existing Java and JavaFX code.

This same plugin also generates native images for mobile apps, which we discuss in the next chapter.

We will now show you how to build a native executable with the HelloFX sample application using the GluonFX plugin.

### Platform Requirements

In order to build a native image, you use JDK 17 or GraalVM JDK 17. We’ll briefly describe the requirements for both Maven and Gradle projects on macOS, Linux, and Windows.

You can download JDK 17 or GraalVM JDK 17 for each target system. For example, you can download AdoptOpenJDK from the following URL (choose the appropriate release for the target platform):
https://adoptopenjdk.net/releases.html

RedHat:

* https://access.redhat.com/products/openjdk/
* https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?downloadType=distributions&product=core.service.openjdk&version=17.0.14

GraalVM:

* You can download the Gluon GraalVM release from this URL (choose the appropriate release for the target platform):
  https://github.com/gluonhq/graal/releases/
* You can download the github GraalVM-CE release from this URL: https://github.com/graalvm/graalvm-ce-builds/releases/tag/jdk-17.0.9
* You can download the Oracle GraalVM release from this URL: https://www.oracle.com/java/technologies/javase/graalvm-jdk17-archive-downloads.html

```bash
set GRAALVM_HOME="C:\Users\<user>\Downloads\graalvm-community-openjdk-17.0.9+9.1" 
set PATH=%GRAALVM_HOME%\bin
```

or Linux

```bash
export GRAALVM_HOME=/opt/graalvm-ce-java17
export PATH=$GRAALVM_HOME/bin:$PATH
```

To install `native-image`

```bash
gu install native-image
native-image --version
```

See https://docs.gluonhq.com/#_graalvm_3120

### Requirements for macOS

To use the plugin to develop and deploy native applications on macOS platforms, you need a Mac with macOS 10.13.2 or higher and Xcode 11 or higher, available from the Mac App Store. Once Xcode is downloaded and installed, open it to accept the license terms.

Once downloaded and installed, set JAVA_HOME to the JDK, for example:

```bash
export JAVA_HOME=/Users/<user>/Downloads/jdk-17/Contents/Home
```

### Requirements for Linux

After downloading a JDK for Linux, export the JAVA_HOME environment variable for the Linux platform JDK, for example:

```bash
export JAVA_HOME=/home/<user>/Downloads/jdk-17
```

### Requirements for Windows

After downloading a JDK for Windows, set the JAVA_HOME environment variable for the Windows platform JDK, for example:

```bash
set JAVA_HOME=C:\path\to\jdk-17
```

Add JAVA_HOME to the Environment Variables list (Advanced system settings).

In addition to the Java JDK, Microsoft Visual Studio 2022is also required. The community edition is sufficient, which you can download from
https://visualstudio.microsoft.com/downloads/

During the installation process, make sure to select at least the following individual components:

* Choose the English Language Pack.
* C++/CLI support for v143 build tools (latest version).
* MSVC v143 – VS 2022 C++ x64/x86 build tools (latest version).
* Windows Universal CRT SDK.
* Windows 10 SDK (10.0.20348.0 or later).

Note that all build commands must be executed in a Visual Studio 2022 command prompt called x64 Native Tools Command Prompt for VS 2022.

### The Code

The code for this example is on GitHub:

* https://github.com/gluonhq/gluon-samples/tree/master/HelloFX/src/main/java/hellofx
* https://github.com/gluonhq/gluon-samples/tree/master/HelloFXML/src/main/java/hellofx

### JavaFX References

* https://openjfx.io/openjfx-docs/#maven

### Build the Project

The first step is to build and run the project as a regular Java project (on a regular JVM that you use for your local development, e.g., HotSpot).
With Gradle:

```bash
./gradlew clean build run (Mac OSX or Linux)
gradlew clean build run (Windows)
```

With Maven:

```bash
mvn clean javafx:run

```

We will now compile, package, and run the native desktop application.

#### Compile

Run with Gradle:

```bash
./gradlew nativeCompile (Mac OS or Linux)
gradlew nativeCompile (Windows)
```

Or with Maven:

```bash
mvn gluonfx:compile
```

You’ll need to wait until the task finishes successfully (depending on your machine, it may take 5 minutes or more). You will see the feedback provided during the process, such as in Listing 10-6.

```bash
[INFO] Scanning for projects...
[INFO]
[INFO] --------------------< com.gluonhq.samples:hellofx >---------------------
[INFO] Building HelloFX 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> gluonfx-maven-plugin:1.0.23:compile (default-cli) > process-classes @ hellofx >>>
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ hellofx ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ hellofx ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] <<< gluonfx-maven-plugin:1.0.23:compile (default-cli) < process-classes @ hellofx <<<
[INFO]
[INFO]
[INFO] --- gluonfx-maven-plugin:1.0.23:compile (default-cli) @ hellofx ---
1月 30, 2025 10:51:24 上午 com.gluonhq.substrate.util.Logger logInfo
資訊: Substrate is tested with the Gluon's GraalVM build which you can find at https://github.com/gluonhq/graal/releases.
While you can still use other GraalVM builds, there is no guarantee that these will work properly with Substrate
[週四 1月 30 10:51:24 TST 2025][資訊] ==================== COMPILE TASK ====================
             _______  ___      __   __  _______  __    _
            |       ||   |    |  | |  ||       ||  |  | |
            |    ___||   |    |  | |  ||   _   ||   |_| |
            |   | __ |   |    |  |_|  ||  | |  ||       |
            |   ||  ||   |___ |       ||  |_|  ||  _    |
            |   |_| ||       ||       ||       || | |   |
            |_______||_______||_______||_______||_|  |__|

    Access to the latest docs, tips and tricks and more info on
    how to get support? Register your usage of Gluon Substrate now at

    https://gluonhq.com/activate



[週四 1月 30 10:51:25 TST 2025][資訊] We will now compile your code for x86_64-microsoft-windows. This may take some time.
[週四 1月 30 10:51:26 TST 2025][資訊] [SUB] Warning: Ignoring server-mode native-image argument --no-server.
[週四 1月 30 10:51:28 TST 2025][資訊] [SUB] ========================================================================================================================
[週四 1月 30 10:51:28 TST 2025][資訊] [SUB] GraalVM Native Image: Generating 'org.modernclients.main' (shared library)...
[週四 1月 30 10:51:28 TST 2025][資訊] [SUB] ========================================================================================================================
[週四 1月 30 10:51:28 TST 2025][資訊] [SUB] For detailed information and explanations on the build output, visit:
[週四 1月 30 10:51:28 TST 2025][資訊] [SUB] https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md
[週四 1月 30 10:51:28 TST 2025][資訊] [SUB] ------------------------------------------------------------------------------------------------------------------------
[週四 1月 30 10:51:32 TST 2025][資訊] [SUB] [1/8] Initializing...                                                                                    (5.8s @ 0.20GB)
[週四 1月 30 10:51:32 TST 2025][資訊] [SUB]  Java version: 17.0.9+9, vendor version: GraalVM CE 17.0.9+9.1
[週四 1月 30 10:51:32 TST 2025][資訊] [SUB]  Graal compiler: optimization level: 2, target machine: x86-64-v3
[週四 1月 30 10:51:32 TST 2025][資訊] [SUB]  C compiler: cl.exe (microsoft, x64, 19.42.34436)
[週四 1月 30 10:51:32 TST 2025][資訊] [SUB]  Garbage collector: Serial GC (max heap size: 80% of RAM)
[週四 1月 30 10:51:32 TST 2025][資訊] [SUB]  1 user-specific feature(s)
[週四 1月 30 10:51:32 TST 2025][資訊] [SUB]  - org.graalvm.home.HomeFinderFeature: Finds GraalVM paths and its version number
[週四 1月 30 10:51:55 TST 2025][資訊] [SUB] [2/8] Performing analysis...  [******]                                                                  (22.7s @ 1.03GB)
[週四 1月 30 10:51:55 TST 2025][資訊] [SUB]   11,649 (89.18%) of 13,062 types reachable
[週四 1月 30 10:51:55 TST 2025][資訊] [SUB]   20,552 (66.41%) of 30,949 fields reachable
[週四 1月 30 10:51:55 TST 2025][資訊] [SUB]   56,687 (61.92%) of 91,556 methods reachable
[週四 1月 30 10:51:55 TST 2025][資訊] [SUB]    3,262 types,   179 fields, and 2,624 methods registered for reflection
[週四 1月 30 10:51:55 TST 2025][資訊] [SUB]      135 types,   152 fields, and   207 methods registered for JNI access
[週四 1月 30 10:51:55 TST 2025][資訊] [SUB]        4 native libraries: crypt32, ncrypt, version, winhttp
[週四 1月 30 10:51:58 TST 2025][資訊] [SUB] [3/8] Building universe...                                                                               (2.6s @ 1.33GB)
[週四 1月 30 10:52:00 TST 2025][資訊] [SUB] [4/8] Parsing methods...      [**]                                                                       (2.1s @ 1.80GB)
[週四 1月 30 10:52:01 TST 2025][資訊] [SUB] [5/8] Inlining methods...     [****]                                                                     (1.3s @ 1.22GB)
[週四 1月 30 10:52:18 TST 2025][資訊] [SUB] [6/8] Compiling methods...    [****]                                                                    (16.3s @ 1.96GB)
[週四 1月 30 10:52:21 TST 2025][資訊] [SUB] [7/8] Layouting methods...    [**]                                                                       (3.2s @ 2.72GB)
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB]
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB] ------------------------------------------------------------------------------------------------------------------------
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB]                         2.8s (4.8% of total time) in 105 GCs | Peak RSS: 3.92GB | CPU load: 8.10
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB] ------------------------------------------------------------------------------------------------------------------------
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB] Produced artifacts:
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB]  D:\Data\workspaces\intelliJ\apress-definitive-guide-modern-java-clients-javafx17-2022\ch10-packaging\Sample3\target\gluonfx\x86_64-windows\gvm\HelloFX\graal_isolate.h (c_header)
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB]  D:\Data\workspaces\intelliJ\apress-definitive-guide-modern-java-clients-javafx17-2022\ch10-packaging\Sample3\target\gluonfx\x86_64-windows\gvm\HelloFX\graal_isolate_dynamic.h (c_header)
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB]  D:\Data\workspaces\intelliJ\apress-definitive-guide-modern-java-clients-javafx17-2022\ch10-packaging\Sample3\target\gluonfx\x86_64-windows\gvm\HelloFX\org.modernclients.main.h (c_header)
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB]  D:\Data\workspaces\intelliJ\apress-definitive-guide-modern-java-clients-javafx17-2022\ch10-packaging\Sample3\target\gluonfx\x86_64-windows\gvm\HelloFX\org.modernclients.main_dynamic.h (c_header)
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB] ========================================================================================================================
[週四 1月 30 10:52:24 TST 2025][資訊] [SUB] Finished generating 'org.modernclients.main' in 58.0s.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:02 min
[INFO] Finished at: 2025-01-30T10:52:25+08:00
[INFO] ------------------------------------------------------------------------
```

Listing 10-6Output during the native compilation phase
As a result, you will see `org.modernclients.main.o` (65.0 MB) or `org.modernclients.main.obj` under `target/gluonfx/{target-architecture}/gvm/tmp/SVM-***/`.

If that is not the case, check for any possible failures in the log files under `target/gluonfx/{target-architecture}/gvm/log`.

#### Link

Now that the Java code for the application is compiled to native code, we can package the generated code with the required libraries and resources using the nativeLink task .

Run with Gradle:

```bash
./gradlew nativeLink (Mac OSX or Linux)
gradlew nativeLink (Windows)
```

Or with Maven:

```bash
mvn gluonfx:link

[INFO] Scanning for projects...
[INFO]
[INFO] --------------------< com.gluonhq.samples:hellofx >---------------------
[INFO] Building HelloFX 1.0.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- gluonfx-maven-plugin:1.0.23:link (default-cli) @ hellofx ---
1月 30, 2025 10:58:26 上午 com.gluonhq.substrate.util.Logger logInfo
資訊: Substrate is tested with the Gluon's GraalVM build which you can find at https://github.com/gluonhq/graal/releases.
While you can still use other GraalVM builds, there is no guarantee that these will work properly with Substrate
[週四 1月 30 10:58:26 TST 2025][資訊] ==================== LINK TASK ====================
[週四 1月 30 10:58:26 TST 2025][資訊] Default icon.ico image generated in D:\Data\workspaces\intelliJ\apress-definitive-guide-modern-java-clients-javafx17-2022\ch10-packaging\Sample3\target\gluonfx\x86_64-windows\gensrc\windows\assets.
Consider copying it to D:\Data\workspaces\intelliJ\apress-definitive-guide-modern-java-clients-javafx17-2022\ch10-packaging\Sample3\src\windows before performing any modification
[週四 1月 30 10:58:26 TST 2025][資訊] [SUB] Microsoft (R) Incremental Linker Version 14.42.34436.0
[週四 1月 30 10:58:26 TST 2025][資訊] [SUB] Copyright (C) Microsoft Corporation.  All rights reserved.
[週四 1月 30 10:58:26 TST 2025][資訊] [SUB]
[週四 1月 30 10:58:26 TST 2025][資訊] [SUB]    正在建立程式庫 D:\Data\workspaces\intelliJ\apress-definitive-guide-modern-java-clients-javafx17-2022\ch10-packaging\Sample3\target\gluonfx\x86_64-windows\HelloFX.lib 和物件 D:\Data\workspaces\intelliJ\apress-definitive-guide-modern-java-clients-javafx17-2022\ch10-packaging\Sample3\target\gluonfx\x86_64-windows\HelloFX.exp
             _______  ___      __   __  _______  __    _
            |       ||   |    |  | |  ||       ||  |  | |
            |    ___||   |    |  | |  ||   _   ||   |_| |
            |   | __ |   |    |  |_|  ||  | |  ||       |
            |   ||  ||   |___ |       ||  |_|  ||  _    |
            |   |_| ||       ||       ||       || | |   |
            |_______||_______||_______||_______||_|  |__|

    Access to the latest docs, tips and tricks and more info on
    how to get support? Register your usage of Gluon Substrate now at

    https://gluonhq.com/activate



[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.377 s
[INFO] Finished at: 2025-01-30T10:58:28+08:00
[INFO] ------------------------------------------------------------------------

```

The link step produces the executable in the target subdirectory `target/gluonfx/{target-architecture}/HelloFX` (65.4 MB) or in `target\gluonfx\x86_64-windows\hellofx.exe` for Windows. Figure 10-13 shows the executable in a macOS file system.

It can be executed directly or with `mvn gluonfx:nativerun`.

#### Run

Finally, you can run it with Gradle:

```bash
./gradlew nativeRun (Mac OS or Linux)
gradlew nativeRun (Windows)
```

Or with Maven:

```bash
mvn gluonfx:run
```

Note that you can distribute this native application to any machine with the matching architecture (macOS, Linux, or Windows) and run it directly as any other regular application.

# usage
* Steps:
```bash
mvn clean javafx:run
mvn clean compile spring-boot:process-aot -DskipTests -Pnative 
# generate qualified `src\main\resources\META-INF\native-image` data
# You have to run once scenario about operations in order to let aot to audit beahvior 
mvn clean gluonfx:compile  # clean target data about profile is native exclude `src\main\resources\META-INF\native-image` data
mvn gluonfx:link
```
Using `mvn gluonfx:nativerun ` to  check errors. 
* Produce:`target\gluonfx\x86_64-windows` 