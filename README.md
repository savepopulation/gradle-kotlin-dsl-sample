![alt text](/art/ss1.jpeg)

# Gradle Kotlin-DSL Sample
How to use Kotlin in Gradle?

## Step by Step Migration Guide to Kotlin-DSL

### Step 0:
Create ```buildSrc``` directory in **root of your project.**

### Step 1:
Create ```src/main/java``` directory in ```buildSrc```

### Step 2: 
Create your ```build.gradle.kts``` file which contains **Kotlin-DSL plugin.**
```Kotlin
import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
}
```

### Step 3:
Create your dependency and config files in ```buildSrc/src/main/java```

ClassPaths:
```Kotlin
object ClassPaths {
    val gradleClasspath = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    val kotlinGradlePluginClasspath = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
}
```

Config:
```Kotlin
object Config {
    val applicatiınId = "com.raqun.phonebox"
    val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
}
```

Dependencies.kt:
```Kotlin
object CoreLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
}

object SupportLibraries {
    val appCompat = "com.android.support:appcompat-v7:${Versions.appCompatVersion}"
}

object TestLibraries {
    val jUnit = "junit:junit:${Versions.jUnitVersion}"
    val runnner = "com.android.support.test:runner:${Versions.testRunnerVersion}"
    val espressoCore = "com.android.support.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
}
```

Modules:
```Kotlin
object Modules {
    val app = ":app"
}
```

Versions.kt:
```Kotlin
object Release {
    val versionCode = 1
    val versionName = "1.0.0"
}

object Versions {

    /**
     * Sdk Versions
     */
    val compileSdkVersion = 28
    val minSdkVersion = 17
    val targetSdkVersion = 28

    /**
     * Dependency Versions
     */
    const val gradleVersion = "3.2.1"
    const val kotlinVersion = "1.3.11"
    const val appCompatVersion = "28.0.0"
    const val jUnitVersion = "4.12"
    const val testRunnerVersion = "1.0.2"
    const val espressoCoreVersion = "3.0.2"
}
```

### Step 4: 
Rename your ```settings.gradle``` to ```settings.gradle.kts```
<br>Change ```include ':app'``` to ```include(Modules.app)```

### Step 5:
Rename your project level ```build.gradle``` to ```build.gradle.kts```
Refactor your code like the following:
```Kotlin
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath (ClassPaths.gradleClasspath)
        classpath (ClassPaths.kotlinGradlePluginClasspath)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
```

### Step 6: 
Rename your module level ```build.gradle``` to ```build.gradle.kts```
Refactor your code like the following:
```Kotlin
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        applicationId = Config.applicatiınId
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Release.versionCode
        versionName = Release.versionName
        testInstrumentationRunner = Config.testInstrumentationRunner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // Core Libraries
    implementation(CoreLibraries.kotlin)

    // Support Libraries
    implementation(SupportLibraries.appCompat)

    // Testing
    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.runnner)
    androidTestImplementation(TestLibraries.espressoCore)
}
```












