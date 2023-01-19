pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Lóra"
include(":app")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // Core ================================================================================
            version("androidx-core-version", "1.9.0")
            library("androidx-core", "androidx.core", "core-ktx").versionRef("androidx-core-version")

            version("appcompat-version", "1.6.0")
            library("appcompat", "androidx.appcompat", "appcompat").versionRef("appcompat-version")

            version("material-version", "1.7.0")
            library("material", "com.google.android.material", "material").versionRef("material-version")

            bundle("core", listOf("androidx-core", "appcompat", "material"))

            // Tests ===============================================================================
            version("junit-version", "4.13.2")
            library("junit", "junit", "junit").versionRef("junit-version")

            bundle("testImplementations", listOf("junit", "junit-ext"))

            version("junit-ext-version", "1.1.5")
            library("junit-ext", "androidx.test.ext", "junit").versionRef("junit-ext-version")

            version("espresso-version", "3.5.1")
            library("espresso", "androidx.test.espresso", "espresso-core").versionRef("espresso-version")

            bundle("androidTestImplementations", listOf("junit-ext", "espresso"))
        }

        create("appVersions") {
            version("compileSdk", "33")
            version("minSdk", "26")

            version("versionCode", "1")
            version("versionName", "1.0")

            version("jvmTarget", "1.8")
        }
    }
}
