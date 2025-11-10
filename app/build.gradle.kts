plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    // Use the modern plugin id (works with root plugin version 2.48)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.chatflow"
    compileSdk = 34
    
    defaultConfig {
        applicationId = "com.example.chatflow"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    
    buildFeatures {
        compose = true
    }
    
    // Kotlin 1.9.25 ↔ Compose Compiler 1.5.15
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlin {
        jvmToolchain(17)
    }
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
    // Keep args minimal to reduce noise; add back only if you really need them.
    // arguments {
    //     arg("dagger.fastInit", "enabled")
    //     arg("dagger.hilt.internal.useAggregatingRootProcessor", "false")
    // }
}

dependencies {
    // --- Compose ---
    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.8.0")
    
    // --- Hilt (aligned to plugin 2.48) ---
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    
    // --- Retrofit + OkHttp ---
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    
    // --- Room (upgrade to 2.6.1) ---
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // --- Paging ---
    implementation("androidx.paging:paging-runtime-ktx:3.3.0")
    implementation("androidx.paging:paging-compose:3.3.0")
    
    // --- Coil ---
    implementation("io.coil-kt:coil-compose:2.2.2")
    
    // --- Coroutines ---
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // --- Accompanist swipe refresh ---
    implementation("com.google.accompanist:accompanist-swiperefresh:0.36.0")
    
    // --- Timber ---
    implementation("com.jakewharton.timber:timber:5.0.1")
    
    // --- LifecycleService (for services extending LifecycleService) ---
    implementation("androidx.lifecycle:lifecycle-service:2.8.3")
    
    // --- Tests ---
    testImplementation("junit:junit:4.13.2")
    
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    
    
    // Coroutines test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    
   // Turbine (Flow testing)
    testImplementation("app.cash.turbine:turbine:1.0.0")
    
    // MockK (mocks, coEvery, coVerify, every…)
    testImplementation("io.mockk:mockk:1.13.10")
}
