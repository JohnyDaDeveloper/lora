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

    detekt {
        val detektFolder = File(project.rootDir, "config/detekt")

        toolVersion = libs.versions.detekt.asProvider().get()
        config = files(
            File(detektFolder, "global").listFiles()?.map { it.absolutePath } ?: emptyArray<String>(),
            File(detektFolder, "core").listFiles()?.map { it.absolutePath } ?: emptyArray<String>(),
            File(detektFolder, "feature").listFiles()?.map { it.absolutePath } ?: emptyArray<String>()
        )
        buildUponDefaultConfig = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(libs.bundles.core)
    implementation(libs.bundles.ui.core)

    testImplementation(libs.bundles.tests)

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    detektPlugins(libs.bundles.detekt.feature)

    implementation(project(":core:constants"))

    implementation(project(":feature:core"))
    implementation(project(":feature:join-game"))
    implementation(project(":feature:create-game"))
    implementation(project(":feature:firebase-sign-in"))
}