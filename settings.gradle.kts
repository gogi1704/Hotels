pluginManagement {
    repositories {
        maven ( url ="https://jitpack.io" )
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

rootProject.name = "Hotels"
include(":app")
include(":data")
include(":domain")
