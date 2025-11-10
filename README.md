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

app/
├─ data/
│ ├─ local/ # Room database (ChatDao, AppDatabase)
│ ├─ remote/ # Retrofit ApiService
│ ├─ repo/ # Repository implementation (ChatRepositoryImpl)
│
├─ domain/
│ ├─ model/ # Core data models (RecentChat)
│ ├─ repository/ # Interfaces (ChatRepository)
│ ├─ usecase/ # Executable business actions
│
├─ presentation/
│ ├─ chatlist/ # UI Screens + ViewModel
│ ├─ chatdetail/ # (Extend later for messaging screen)
│
├─ sync/
│ └─ ChatSyncService.kt # Background sync worker/foreground service


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

