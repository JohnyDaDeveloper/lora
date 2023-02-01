plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.detekt)
}

android {
    namespace = "cz.johnyapps.lora"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "cz.johnyapps.lora"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

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
        jvmTarget = libs.versions.jvmTarget.get()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.bundles.core)
    implementation(libs.bundles.ui.core)
    testImplementation(libs.bundles.tests)
    androidTestImplementation(libs.bundles.androidTests)

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    implementation(project(":feature:core"))
    implementation(project(":feature:join-game"))
    implementation(project(":feature:create-game"))

    detektPlugins(libs.detekt.ktlint)
}