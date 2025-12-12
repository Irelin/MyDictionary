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

rootProject.name = "MyDictionary"
include(":app")
include(":core")
include(":core_data")
include(":core_ui")
include(":home")
include(":words_api")
include(":words_impl")
include(":words_ui")
include(":categories_api")
include(":categories_impl")
include(":categories_ui")
include(":study_api")
include(":study_impl")
include(":profile_api")
include(":profile_impl")

