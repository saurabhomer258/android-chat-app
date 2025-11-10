// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // Android + Kotlin
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.25" apply false
    
    // Hilt Dependency Injection
    id("com.google.dagger.hilt.android") version "2.48" apply false
}
