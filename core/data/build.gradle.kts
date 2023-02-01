plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.detekt)
}

android {
    namespace = "cz.johnyapps.lora.core.data"
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
}

dependencies {
    implementation(libs.bundles.core)

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    implementation(project(":core:database"))
    implementation(project(":core:network"))

    detektPlugins(libs.detekt.ktlint)
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        md.required.set(true)
    }
}

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("detektFormat") {
    autoCorrect = true
}