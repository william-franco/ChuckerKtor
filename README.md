# Chucker Ktor

Sample Android app that loads posts from the [JSONPlaceholder API](https://jsonplaceholder.typicode.com) with Jetpack Compose and feature-based MVVM. HTTP calls use Ktor Client on the OkHttp engine; **Chucker** inspects requests and responses in debug builds. A settings feature persists dark theme via DataStore. The project demonstrates wiring Chucker as an OkHttp interceptor without changing repository or ViewModel code.

## Structure

```mermaid
flowchart TB
  subgraph postsFeature [posts]
    PostRoute --> PostViewModel
    PostViewModel --> PostRepository
    PostRepository --> HttpService
  end
  HttpService --> KtorClient[Ktor OkHttp engine]
  KtorClient --> ChuckerInterceptor
  ChuckerInterceptor --> JSONPlaceholder[JSONPlaceholder API]
  subgraph settingsFeature [settings]
    SettingRoute --> SettingViewModel
    SettingViewModel --> SettingRepository
    SettingRepository --> DataStore
  end
  RoutesApp --> PostRoute
  RoutesApp --> SettingRoute
```

## Stack

| Technology | Version |
|------------|---------|
| Android Gradle Plugin | 9.4.0 |
| Kotlin | 2.2.10 |
| NDK | 30.0.16248370 |
| Compose BOM | 2026.02.01 |
| Koin | 4.2.2 |
| Navigation Compose | 2.9.3 |
| Ktor Client | 3.1.3 |
| Chucker | 4.3.1 |
| DataStore | 1.1.7 |
| compileSdk / targetSdk | 37 |
| minSdk | 29 |
| JVM | 21 |

## Architecture

Feature-based MVVM with Koin for dependency injection.

```
src/
├── common/
│   ├── constants/
│   ├── patterns/
│   └── services/
├── di/
├── design/theme/
├── routes/
└── features/
    ├── posts/
    └── settings/
```

## ScreenShots

| Image 1 | Image 2 | Image 3 |
|----------|----------|----------|
| ![App Screenshot](assets/screenshots/screen-1.png) | ![App Screenshot](assets/screenshots/screen-2.png) | ![App Screenshot](assets/screenshots/screen-3.png) |

| Image 4 | Image 5 | Image 6 |
|----------|----------|----------|
| ![App Screenshot](assets/screenshots/screen-4.png) | ![App Screenshot](assets/screenshots/screen-5.png) | ![App Screenshot](assets/screenshots/screen-6.png) |

## Commits

```
git add . && git commit -m ":rocket: Initial commit." && git push
git add . && git commit -m ":building_construction: Added initial project architecture." && git push
git add . && git commit -m ":building_construction: Update project architecture." && git push
git add . && git commit -m ":memo: Updated project documentation." && git push
git add . && git commit -m ":memo: Updated code documentation." && git push
git add . && git commit -m ":white_check_mark: Added feature xyz." && git push
git add . && git commit -m ":wrench: Fixed xyz usage." && git push
git add . && git commit -m ":heavy_minus_sign: Removed xyz." && git push
git add . && git commit -m ":memo: Adjusted project imports." && git push
git add . && git commit -m ":arrow_up: Updated dependencies." && git push
git add . && git commit -m ":arrow_down: Removed dependencies." && git push
git add . && git commit -m ":wastebasket: Removed unused code." && git push
git add . && git commit -m ":test_tube: Added test functionality xyz." && git push
git add . && git commit -m ":construction_worker: Building in progress." && git push
git add . && git commit -m ":construction_worker: Added CI build system." && git push
```

## License

[MIT License](https://opensource.org/licenses/MIT)

Copyright (c) 2026 William Franco.
