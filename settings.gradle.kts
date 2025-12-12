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
include(":home")
include(":core")
include(":core_data")
include(":core_ui")
include(":words_impl")
include(":words_api")
include(":categories")
include(":categories_api")
include(":categories_impl")
include(":categories_ui")
include(":words_ui")
include(":study_api")
include(":study_impl")
include(":profile_api")
include(":profile_impl")

