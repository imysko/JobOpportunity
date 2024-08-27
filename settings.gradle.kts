pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "JobOpportunity"
include(":app")

include(":common:ui")

include(":data:authorization")

include(":features:authorization")
include(":features:search")
include(":features:favorite")
include(":features:feedback")
include(":features:messages")
include(":features:profile")
