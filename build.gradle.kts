buildscript {
    val mpaas_baseline by extra("10.2.3-59")
    val mpaas_artifact by extra("mpaas-baseline")
    dependencies {
        classpath("com.android.boost.easyconfig:easyconfig:2.8.4")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}