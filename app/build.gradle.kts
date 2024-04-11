import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

    // kotlinx-serializable
    kotlin("plugin.serialization") version "1.9.22"
}

val properties = Properties()
properties.load(FileInputStream(rootProject.file("local.properties")))

android {
    namespace = "com.example.seoulgonggong"
    compileSdk = libs.versions.compileSdk.get().toInt()

    // BuildConfig 클래스 생성
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.seoulgonggong"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "SEOUL_OPEN_API_BASE_URL", "\"http://openAPI.seoul.go.kr:8088/${properties["ACT_KEY"]}/\"")
        buildConfigField("String", "OPEN_DATA_API_BASE_URL", "\"http://apis.data.go.kr/\"")
        buildConfigField(
            "String",
            "OPEN_DATA_SERVICE_KEY",
            "\"N4E7Om1Q+ZfsXn5KbolmQMkKGMtW6NKltMv/tBhClqRm/U6UMiSeIamXBI9aD6GNs/GQRR6Maxah3UfAcw16+Q==\"",
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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

    dataBinding {
        enable = true
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

    // lifecycle
    implementation(libs.viewmodel)
    implementation(libs.livedata)

    // activity
    implementation(libs.activity)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter)

    // kotlinx-serialization
    implementation(libs.serialization)
    implementation(libs.serialconverter)

    // okhttp3
    implementation(libs.okhttp3)

    // Coroutine
    implementation(libs.coroutinecore)
    implementation(libs.coroutinejdk)
    testImplementation(libs.coroutinetest)

    // location
    implementation(libs.location)
}
