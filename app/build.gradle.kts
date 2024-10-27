// build.gradle.kts (Module: app)
plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.yogaposeclassifier"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.yogaposeclassifier"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Enable multidex if needed
        multiDexEnabled = true

        // TensorFlow Lite requires Java 8
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")

    // TensorFlow Lite
    implementation("org.tensorflow:tensorflow-lite:2.16.1")

    implementation("org.tensorflow:tensorflow-lite-support:0.4.4"){
        exclude(group = "com.google.ai.edge.litert")
    }
    // CameraX
    implementation("androidx.camera:c   amera-core:1.3.4")
    implementation("androidx.camera:camera-camera2:1.3.4")
    implementation("androidx.camera:camera-lifecycle:1.3.4")
    implementation("androidx.camera:camera-view:1.3.4")
    implementation("androidx.camera:camera-extensions:1.3.4")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // UI
    implementation("com.google.android.material:material:1.12.0")
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
}
