plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.seoulgonggong"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.seoulgonggong"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.bundles.androidDefault)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.androidUnitTest)

    // Glide
    implementation(libs.glide)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hiltKapt)
}
