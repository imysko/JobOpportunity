plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)

    alias(libs.plugins.android.library)
}

android {
    namespace = "com.imysko.features.favorite"
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

    implementation(project(":common:ui"))

    implementation(project(":data:vacancies"))

    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.recyclerview)

    implementation(libs.androidx.lifecycle.livedata)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}