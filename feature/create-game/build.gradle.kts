plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.detekt)
}

android {
    namespace = "cz.johnyapps.lora.feature.creategame"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()

        consumerProguardFiles("consumer-rules.pro")
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
        toolVersion = libs.versions.detekt.asProvider().get()
        config = files(
            "../detekt/twitter-compose.yml",
            "../detekt/compose.yml"
        )
        buildUponDefaultConfig = true
    }
}

dependencies {
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    implementation(project(":feature:core"))

    implementation(project(":core:data"))

    implementation(libs.coroutines)

    detektPlugins(libs.bundles.detekt.feature)
}