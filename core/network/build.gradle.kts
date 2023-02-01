plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.detekt)
}

android {
    namespace = "cz.johnyapps.lora.core.network"
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

    kapt {
        correctErrorTypes = true
    }

    detekt {
        basePath = projectDir.path
    }

    detekt {
        val detektFolder = File(project.rootDir, "config/detekt")

        toolVersion = libs.versions.detekt.asProvider().get()
        config = files(
            File(detektFolder, "global").listFiles()?.map { it.absolutePath } ?: emptyArray<String>(),
            File(detektFolder, "core").listFiles()?.map { it.absolutePath } ?: emptyArray<String>()
        )
        buildUponDefaultConfig = true
    }
}

dependencies {
    implementation(libs.bundles.core)

    implementation(libs.coroutines)

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    detektPlugins(libs.bundles.detekt.core)
}