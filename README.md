# Hospital Details App

 Android Hospital Details App using ViewModel & LiveData with Retrofit, Glide and Kotlin

## Dependencies
 This app demonstrates the MVVM architecture with below dependencies:

 * [Retrofit](https://square.github.io/retrofit/) to make api calls to an HTTP web service
 * Room for data layer
 * Dagger2 for dependency injections

 It leverages the following components from the Jetpack library:

 * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
 * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
 * [Data Binding](https://developer.android.com/topic/libraries/data-binding/) with binding adapters
 * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) with the SafeArgs plugin for parameter passing between fragments


### Getting Started


This project uses the Gradle build system. To build this project, use the
`./gradlew build` command or Run the app via Android Studio.

There are two Gradle tasks for testing the project:
* `connectedAndroidTest` - for running Espresso on a connected device
* `test` - for running unit tests
