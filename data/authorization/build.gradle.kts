plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)

    alias(libs.plugins.android.library)

    alias(libs.plugins.navigation.safe.args)
}

android {
    namespace = "com.imysko.data.authorization"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    dataBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}