# ✅ TaskFlow - A Modern To-Do List Application
### A comprehensive Android project built for SkillCraft Technology

TaskFlow is a modern, feature-rich To-Do list application for Android, built entirely with Kotlin and Jetpack Compose. It provides a clean, intuitive user interface for managing daily tasks, featuring due dates, categories, and a clear separation between active and completed items. The app is designed with a robust MVVM architecture using Hilt and Room, ensuring a scalable and maintainable codebase.

---
---

## 📸 Screenshots

**Note:** _Replace the placeholder links below with URLs to your actual screenshots._

| Main Screen (Empty) | Main Screen (With Tasks) | Add/Edit Dialog | Date & Time Picker |
| :---: |:---:|:---:|:---:|
| ![Empty Task List](https://raw.githubusercontent.com/therahuls916/TaskFlow/main/screenshots/s1.png) | ![Task List](https://raw.githubusercontent.com/therahuls916/TaskFlow/main/screenshots/s2.png) | ![Add-Edit Dialog](https://raw.githubusercontent.com/therahuls916/TaskFlow/main/screenshots/s3.png) | ![Date Picker](https://raw.githubusercontent.com/therahuls916/TaskFlow/main/screenshots/s4.png) |

---

## 🚀 Features

| Feature | Description |
|---|---|
| ✅ **Full CRUD Functionality** | A complete and intuitive workflow to **C**reate, **R**ead, **U**pdate, and **D**elete tasks. |
| ✨ **Modern UI/UX** | Built entirely with **Jetpack Compose & Material 3**. Features a well-structured, clean user interface that supports both Light and Dark themes. |
| 📅 **Due Dates & Times** | Assign a specific due date and time to each task using integrated, user-friendly `DatePicker` and `TimePicker` dialogs. |
| 🎨 **Task Categories** | Organize tasks with custom categories (Work, Urgent, Personal, Health). Each category is represented by a colored dot for quick visual identification. |
| 🗂️ **Tabbed Layout** | Tasks are automatically separated into **Active** and **Completed** tabs. Checking off a task seamlessly moves it to the completed list. |
| 💾 **Persistent Storage** | All tasks are saved to a local **Room database**, ensuring your data is never lost, even if the app is closed or the device is restarted. |
| 🏗️ **Robust Architecture** | Built on a modern **MVVM** foundation with **Hilt** for dependency injection, a `Repository` pattern, and reactive data flow using Kotlin `StateFlow`. |

---

## 🔧 Installation

1.  Clone the repository:
    ```bash
    git clone https://github.com/therahuls916/TaskFlow.git
    ```
2.  Navigate to the project directory:
    ```bash
    cd TaskFlow
    ```
3.  Open the project in the latest stable version of Android Studio, let Gradle sync, and click ▶️ **Run**.

## 🛠 Tech Stack

-   **Tech:** Kotlin, Coroutines, Flow
-   **Architecture:** MVVM (Model-View-ViewModel)
-   **UI:** Jetpack Compose, Material 3
-   **Dependency Injection:** Hilt
-   **Data Persistence:**
    -   Room: For creating and managing the local SQLite database for tasks.
-   **Utilities:**
    -   Material 3 Date & Time Pickers

---

## 📂 Folder Structure

app/src/main/java/com/rahul/auric/todo/
├── data/
│   ├── model/
│   │   └── Category.kt      # Sealed class for task categories and colors
│   ├── AppDatabase.kt     # Room database definition
│   ├── Task.kt            # Room @Entity for the tasks table
│   ├── TaskDao.kt         # Data Access Object for database queries
│   └── TaskRepository.kt  # Repository to abstract the data source
├── di/
│   └── DatabaseModule.kt    # Hilt module to provide database dependencies
├── ui/
│   ├── tasks/
│   │   ├── components/
│   │   │   ├── AddEditTaskDialog.kt # Composable for the Add/Edit dialog
│   │   │   └── TaskItem.kt          # Composable for a single task card
│   │   ├── TaskScreen.kt      # Main UI screen with tabs and task lists
│   │   └── TaskViewModel.kt   # ViewModel to hold state and business logic
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
├── ToDoApp.kt               # Application class for Hilt initialization
└── MainActivity.kt          # Main entry point for the application

## 🔐 Permissions Used

This application currently requires **no special permissions**, providing a secure and privacy-focused user experience.

---

## 🧠 How It Works

-   **UI Layer:** The entire UI is built with Jetpack Compose. `TaskScreen` is the main Composable, which contains the tabbed layout. It observes data from the `TaskViewModel` and displays two lists: one for active tasks and one for completed tasks.
-   **State Management:** The `TaskViewModel` holds all application state (task lists, dialog visibility) using `StateFlow`. It transforms the single `Flow` of all tasks from the repository into two separate `StateFlows` (incomplete and completed), which the UI reactively collects.
-   **Data Layer:** The `TaskRepository` acts as a single source of truth for task data. It abstracts the Room database operations away from the ViewModel, making the code cleaner and easier to test.
-   **Database:** Room is used to persist all tasks. The `Task` data class is the table entity, and the `TaskDao` interface defines all the database operations (insert, update, delete, query).
-   **Dependency Injection:** Hilt is used to manage dependencies. The `DatabaseModule` tells Hilt how to create and provide singleton instances of the `AppDatabase`, `TaskDao`, and `TaskRepository`, which are then automatically injected into the `TaskViewModel`.

---

## ✅ Planned Features

-   [ ] ⚙️ **Implement Settings Screen:** Add a second screen for application settings, accessible via a `BottomNavigationBar`.
-   [ ] 🎨 **Theme Switching:** Add an option in Settings to manually switch between Light and Dark themes.
-   [ ] 🔔 **Notifications:** Implement notifications to remind users of upcoming task deadlines.
-   [ ] 📊 **Task Sorting:** Add an option in Settings to sort tasks by due date or category.
-   [ ] ✨ **UI Animations:** Add animations for adding/deleting tasks and switching between tabs.

---

## 🤝 Contributing

Contributions are welcome! If you'd like to help, please fork the repository, create a new branch for your feature or fix, and submit a pull request.

---

## 📄 License

This project is licensed under the MIT License - see the `LICENSE` file for details.

---

## 👨‍💻 Developer

**Rahul Salunke**  
[GitHub](https://github.com/therahuls916)  
[LinkedIn](https://www.linkedin.com/in/rahulasalunke/)