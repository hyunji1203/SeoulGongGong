import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}
val properties = Properties()
properties.load(FileInputStream(rootProject.file("local.properties")))

android {
    namespace = "com.example.seoulgonggong"
    compileSdk = libs.versions.compileSdk.get().toInt()

    //BuildConfig 클래스 생성
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

    // Kotlinx-serialization
    implementation(libs.kotlinx.serialization.json)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlinx.serialization.converter) //Retrofit과 kotlin serialization 을 연동

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
}
