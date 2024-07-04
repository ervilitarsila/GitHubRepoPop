plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.safeargsKotlin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.ervilitasila.githubrepopop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ervilitasila.githubrepopop"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.testing)
    implementation(libs.androidx.espresso.contrib)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.retrofit.scalars)
    implementation(libs.retrofit.adapter)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //Rxjava
    implementation(libs.rxjava.rxandroid)
    implementation(libs.rxjava.rxjava)

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)

    //Moshi
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.retrofit)

    // kps
    implementation(libs.ksp.gradlePlugin)

    //Dagger
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    kspTest(libs.dagger.compiler)

    // Test
    testImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.mockk.testing.android)
    testImplementation(libs.mockk.testing)
    debugImplementation(libs.fragment.testing)

}