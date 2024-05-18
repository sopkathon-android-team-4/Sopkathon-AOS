plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.serialization)
    kotlin("kapt")
}

android {
    namespace = "com.sopt.sopkathon_aos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sopt.sopkathon_aos"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit2
    implementation(libs.retrofit)

    // Kotlinx Serialization
    implementation(libs.kotlin.serialization.json)
    implementation(libs.kotlin.serialization.converter)

    // Okhttp3
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.interceptor)

    // coroutines
    implementation(libs.kotlinx.coroutines)

    // fragment-ktx
    implementation(libs.fragment.ktx)

    // viewModel
    implementation(libs.viewModel)

    // coil
    implementation(libs.coil)
}