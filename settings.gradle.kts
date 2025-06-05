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
include(":study")
include(":profile")
include(":core")
include(":core_data")
include(":words_impl")
include(":words_api")
include(":dictionary")
include(":categories_api")
include(":categories_impl")
include(":categories_ui")
