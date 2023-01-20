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

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("version-catalog.toml"))
        }
    }
}

rootProject.name = "Lóra"
include(":app")
include(":feature:card")
include(":feature:core")
include(":feature:join-game")
