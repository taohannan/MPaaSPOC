plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.alipay.apollo.baseline.config")
}

android {
    namespace = "com.tutorial.mpaaspoc"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tutorial.mpaaspoc"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs{
        create("release"){
            storeFile = file("keystore.jks")
            storePassword = "abcd1234"
            keyAlias = "keydemo"
            keyPassword = "abcd1234"
            enableV1Signing = true
        }
        getByName("debug"){
            storeFile = file("keystore.jks")
            storePassword = "abcd1234"
            keyAlias = "keydemo"
            keyPassword = "abcd1234"
            enableV1Signing = true
        }
    }


    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(platform("com.mpaas.android:${rootProject.ext["mpaas_artifact"]}:${rootProject.ext["mpaas_baseline"]}"))
    configurations.all {
        exclude(group = "com.alipay.android.phone.thirdparty", module = "securityguard-build")
    }

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.mpaas.android.ktx)
    implementation(libs.mriver)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}