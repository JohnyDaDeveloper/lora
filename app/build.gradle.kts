plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "cz.johnyapps.lora"
    compileSdk = appVersions.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "cz.johnyapps.lora"
        minSdk = appVersions.versions.minSdk.get().toInt()
        targetSdk = appVersions.versions.compileSdk.get().toInt()
        versionCode = appVersions.versions.versionCode.get().toInt()
        versionName = appVersions.versions.versionName.get()!!

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = appVersions.versions.jvmTarget.get()!!
    }
}

dependencies {

    implementation(libs.bundles.core)
    testImplementation(libs.bundles.testImplementations)
    androidTestImplementation(libs.bundles.androidTestImplementations)
}