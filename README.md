# ChatFlow 💬⚡
A modern and clean chat application built using **Kotlin**, **Jetpack Compose**, **Clean Architecture**, and **Hilt**, designed to be scalable, smooth, and production-ready.

---

## ✨ Features

| Feature | Details |
|--------|---------|
| 💬 Recent Chat List | Shows name, avatar, unread count, timestamps |
| 🔍 Search Chats | Real-time search with debounce |
| 🔄 Infinite Scroll | Paginated list using Flow-powered paging |
| 📦 Offline Cache | Room DB stores chats locally |
| 🌐 Sync with Server | One-tap refresh via ViewModel + UseCase |
| 🔁 Background Sync Service | Foreground service keeps chats updated silently |
| 🧹 Clean Architecture | UI → UseCases → Repository → Data Source separation |

---

## 🏗 Architecture Overview

## 🧱 Clean Architecture Layers

### 1) Data Layer
- **local/** → Room database for offline caching
- **remote/** → Retrofit API calls
- **repo/** → Bridges data with domain layer

### 2) Domain Layer
- **model/** → App’s core business models
- **repository/** → Interfaces describing required operations
- **usecase/** → Action-based business workflows (Single Responsibility)

### 3) Presentation Layer
- **chatlist/** → Chat list UI + ViewModel
- **chatdetail/** → (Future extension for messaging UI)

### 4) Background Services
- **sync/ChatSyncService.kt** → Foreground service to sync chats periodically



---

## 🧰 Tech Stack

| Layer | Libraries |
|------|-----------|
| UI | Jetpack Compose + Material 3 |
| Architecture | MVVM + Clean Architecture |
| DI | Hilt / Dagger |
| Network | Retrofit + OkHttp |
| Local Storage | Room Database |
| Async / Reactive | Kotlin Coroutines + StateFlow |
| Logging | Timber |
| Testing | JUnit4, MockK, Turbine, Coroutines Test |

---

## 🚀 Getting Started

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/android-chat-app.git
cd android-chat-app
2️⃣ Open in Android Studio
Use Android Studio Flamingo or newer

3️⃣ Sync Dependencies
File → Sync Project with Gradle Files

4️⃣ Run App ✅
Connect a device → Press Run ▶

🛠 Optional: Change API Base URL
Edit:

bash
Copy code
data/remote/ApiService.kt

