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
include(":common:core")
include(":common:database")

include(":data:offers")
include(":data:vacancies")

include(":features:searchVacancy")
include(":features:favorite")
include(":features:feedback")
include(":features:messages")
include(":features:profile")
