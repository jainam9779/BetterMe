# BetterMe - Habit Tracker App

## Project Description

**BetterMe** is a habit tracker app designed to assist users in building and maintaining positive habits by tracking their progress over time. Users can set personal goals, monitor daily activities, and visualize their progress through charts and calendars. The app is focused on providing a user-friendly interface that makes habit tracking simple and effective, motivating users to achieve their desired habits and improve their lifestyle.

## Problem Addressing

Consistency is often the biggest challenge when trying to establish new habits. Traditional methods of habit tracking, like pen-and-paper or simple checklists, lack the engagement and feedback necessary to sustain motivation. **BetterMe** addresses these challenges by offering a digital solution that integrates reminders, visual progress tracking, and data synchronization, helping users stay accountable and focused on their goals.

## Platform

The app is developed specifically for the **Android platform**. This choice ensures compatibility with a wide range of devices and leverages the extensive capabilities of modern Android devices to provide a seamless user experience. The decision to use Android was also driven by its broad accessibility and the availability of robust development tools.

## Project Structure

### 1. `manifests`
- **AndroidManifest.xml**: The core file that defines essential information about the app, such as components, permissions, and services.

### 2. `java/com.example.betterme`
- **database**:
  - `Converters`: Custom type converters for Room database.
  - `Habit`: Data model representing a habit.
  - `HabitDao`: Data Access Object (DAO) for interacting with the Habit database.
  - `HabitDatabase`: Abstract class for Room database setup.

- **repository**:
  - `HabitRepository`: Handles data operations, providing a clean API to the ViewModel.

- **ui**:
  - `CheckInActivity`: Manages user check-in interactions.
  - `DailyChecklistAdapter`: Adapter for displaying the daily habit checklist.
  - `DailyChecklistFragment`: Fragment displaying the daily habit checklist.
  - `HomeFragment`: Main home screen fragment displaying habit overview.
  - `MainActivity`: The main entry point for the app.
  - `ProgressFragment`: Fragment displaying progress charts.
  - `WeekViewAdapter`: Adapter for displaying weekly habit data.

- **viewmodel**:
  - `HabitViewModel`: Provides data to the UI and survives configuration changes.

### 3. `res`
- **drawable**: Contains XML files for custom shapes, gradients, and icons.
  - `background_gradient.xml`, `button_gradient.xml`: Defines visual styles.
  - Various icon files (e.g., `ic_add.xml`, `ic_habits.xml`) used throughout the app.

- **layout**: XML files defining the structure of each screen and UI element.
  - Activity and Fragment layouts (e.g., `activity_main.xml`, `fragment_home.xml`).
  - Custom item views (e.g., `item_habit.xml`, `item_week_day.xml`).

- **menu**: Contains XML for navigation menu items.
  - `drawer_menu.xml`: Defines the items in the navigation drawer.

- **values**: Centralized resources for strings, colors, and themes.
  - `strings.xml`: Holds all the app's text strings.
  - `colors.xml`: Defines the color palette.
  - `themes.xml`: Manages the app's theme settings.

### 4. Gradle Scripts
- **build.gradle.kts (Project: BetterMe)**: Defines project-level configurations.
- **build.gradle.kts (Module: app)**: Module-specific build configurations, dependencies, and plugins.
- **gradle.properties**, **settings.gradle.kts**: Project settings and environment configurations.

## Front/Back End Support

### Front End

- **Language**: Kotlin/Java
- **Framework**: Android SDK
- **UI Design**: Custom views for displaying habit progress
- **Libraries**:
  - MPAndroidChart for visualizing progress
  - Material Design Components for a modern user interface

### Back End

- **Local Storage**: Room database for offline data persistence
- **Cloud Storage** (Optional): Firebase for syncing data across devices
- **Notifications**: AlarmManager or WorkManager for setting daily reminders

## Functionality

The **BetterMe** app offers a variety of features designed to enhance the habit-building process:

- **Add, Edit, and Delete Habits**: Users can manage their habits with ease, customizing goals to fit their lifestyle.
- **Set Reminders**: Schedule daily notifications to remind users to complete their habits.
- **Track Daily Completion**: Users can log daily activities, marking habits as complete or incomplete.
- **Visual Progress**: Charts and calendars display progress, providing insights into habit-building trends.
- **Data Synchronization**: (Optional) Sync data with cloud services for backup and multi-device support.

## Design (Wireframes)

The app's design focuses on simplicity and effectiveness, utilizing intuitive layouts and clear visual cues. The following wireframes illustrate the app's primary screens:

1. **Home Screen**: Overview of habits and quick access to tracking features.
2. **Habit Detail Screen**: Detailed view of individual habits, including options to edit and delete.
3. **Progress Screen**: Charts and calendars displaying habit completion over time.
4. **Settings Screen**: Options for configuring notifications, data sync, and app preferences.

## Version Changelog

### Version 1.0 - Initial Outline Submission (Week 2)
- **Project Description:** Initial project idea and outline.
- **Problem Addressing:** Defined the problem that the app aims to solve.
- **Platform:** Chose Android as the platform for development.
- **Front/Back End Support:** Listed technologies to be used.
- **Functionality:** Outlined basic features.
- **Design:** Created initial wireframes.

### Version 1.1 - Update Submission 1 (Week 4)
- **Functionality:** Added cloud storage (Firebase) as an optional feature for data synchronization.
- **Back End:** Included WorkManager for more flexible background task management.
- **Design:** Refined wireframes based on user feedback, improving the layout and navigation flow.

### Version 1.2 - Update Submission 2 (Week 6)
- **Front End:** Began integrating MPAndroidChart for enhanced visual progress tracking.
- **Functionality:** Expanded the reminder feature to allow for custom notifications based on user preferences.
- **UI/UX Design:** Introduced a new theme and color scheme to align with the Material Design guidelines.

### Version 1.3 - Update Submission 3 (Week 8)
- **Back End:** Improved local data storage efficiency by optimizing Room database queries.
- **Functionality:** Implemented a streaks feature to motivate users by tracking consecutive days of habit completion.
- **UI Enhancements:** Added animations to improve user engagement when interacting with the habit progress charts.

### Future Updates
- **Version 1.4:** Plan to add social sharing features, allowing users to share their progress on social media platforms.
- **Version 1.5:** Introduce a habit template library where users can select from predefined habits.
- **Version 1.6:** Enhance accessibility features, including voice commands and screen reader support.


