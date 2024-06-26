import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

    // kotlinSerializable
    kotlin("plugin.serialization") version "1.9.22"

    // parcelize
    id("kotlin-parcelize")
}
val properties = Properties()
properties.load(FileInputStream(rootProject.file("local.properties")))

android {
    namespace = "com.seoulfitu.seoulfitu"
    compileSdk = libs.versions.compileSdk.get().toInt()

    // BuildConfig 클래스 생성
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.seoulfitu.seoulfitu"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // BASE_URL
        buildConfigField("String", "SEOUL_OPEN_API_BASE_URL", "\"http://openAPI.seoul.go.kr:8088/${properties["ACT_KEY"]}/\"")
        buildConfigField("String", "GEOCODING_API_BASE_URL", "\"https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode/\"")
        buildConfigField("String", "REVERSE_GEOCODING_API_BASE_URL", "\"https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc/\"")
        buildConfigField("String", "OPEN_DATA_API_BASE_URL", "\"http://apis.data.go.kr/\"")
        buildConfigField("String", "GEOCODER_API_BASE_URL", "\"https://naveropenapi.apigw.ntruss.com/\"")

        // KEY
        buildConfigField("String", "NAVER_MAP_CLIENT_ID", "\"${properties["NAVER_MAP_CLIENT_ID"]}\"")
        buildConfigField("String", "NAVER_MAP_CLIENT_SECRET", "\"${properties["NAVER_MAP_CLIENT_SECRET"]}\"")
        buildConfigField("String", "NAVER_GEOCODE_CLIENT_ID", "\"${properties["NAVER_GEOCODE_CLIENT_ID"]}\"")
        buildConfigField("String", "NAVER_GEOCODE_CLIENT_SECRET", "\"${properties["NAVER_GEOCODE_CLIENT_SECRET"]}\"")
        buildConfigField("String", "OPEN_DATA_SERVICE_KEY", "\"${properties["OPEN_DATA_SERVICE_KEY"]}\"")

        manifestPlaceholders["NAVER_MAP_CLIENT_ID"] =
            properties.getProperty("NAVER_MAP_CLIENT_ID")
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

    // Kotlinx-serialization
    implementation(libs.kotlinx.serialization.json)

    // lifecycle
    implementation(libs.bundles.lifeCycle)

    // retrofit
    implementation(libs.bundles.retrofit)

    // okhttp3
    implementation(libs.okhttp3)

    // Coroutine
    implementation(libs.bundles.coroutine)
    testImplementation(libs.kotlinx.coroutines.test)

    // naverMap
    implementation(libs.bundles.naverMap)

    // OkHttp3 logging interceptor
    implementation(libs.logging.interceptor)

    // Room
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    // lottie
    implementation(libs.lottie)
}
