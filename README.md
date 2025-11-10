# ChatFlow - Android (Skeleton Project)

This is a lightweight skeleton of the ChatFlow app (Compose + Hilt + Room + Retrofit).
It contains key components to get started and to be imported into Android Studio.

## What is included
- Compose UI screens (Chat list, Chat detail)
- Hilt DI setup
- Retrofit + OkHttp interceptor (TimingInterceptor)
- Room entities + DAO
- Repository + ViewModel (basic offline-first)
- ForegroundService for 30s sync
- Basic Paging 3 hooks in DAO/Repository (needs paging dependency activation)
- README and Gradle scripts

## How to use
1. Download the zip and unzip into a folder.
2. Open the folder in Android Studio as a project.
3. Sync Gradle and run on an emulator/device (internet required).
4. For best results, add SQLCipher or additional configs as needed.
