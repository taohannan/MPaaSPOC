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
        maven {
            url = uri("https://mvn.cloud.alipay.com/nexus/content/repositories/open/")
            name = "alipay"
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://mvn.cloud.alipay.com/nexus/content/repositories/open/")
            name = "alipay"
        }
        maven {
            url = uri("https://packages.aliyun.com/63dc774a9dee9309492b993a/maven/repo-jpkdy/")
            credentials {
                username = "626668353c6b5273a0ce984d"
                password = "t[4AU)JOBzXN"
            }
        }
    }
}

rootProject.name = "MPaaSPOC"
include(":app")
