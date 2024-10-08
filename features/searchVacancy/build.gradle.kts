plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)

    alias(libs.plugins.android.library)

    alias(libs.plugins.navigation.safe.args)
}

android {
    namespace = "com.imysko.features.searchVacancy"
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

    implementation(project(":data:offers"))
    implementation(project(":data:vacancies"))

    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.androidx.recyclerview)

    implementation(libs.androidx.lifecycle.livedata)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}