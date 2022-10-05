# Coding Assignment

An android app built using Kotlin that consumes the given api and display the information. 
It has been built following Clean Architecture Principle, Repository Pattern, MVVM Architecture in the presentation layer as well as Jetpack components(including Jetpack Compose).

## Table Of Content.

- [Prerequisite](#prerequisite)
    - [Disclaimer](##disclaimer)
- [Layers](##layers)
        - [Domain](###domain)
        - [Data](###data)
        - [Presentation](###presentation)
- [Tech Stack](#techstack)
    - [Libraries](##libraries)
- [References](#References)


## Prerequisite.

In order to be able to build the application you'll need Android Studio Minimum version Android Studio Chipmunk


## Disclaimer.
- Clean architecture is an overkill for such a small app, just wanted to demonstrate and revise the current architecture patterns

## Layers.

### 1. Domain.
This is the core layer of the application. The ```domain``` layer is independent of any other layers thus ] domain models and business logic can be independent from other layers.This means that changes in other layers will have no effect on domain layer eg.

Components of domain layer include:
- __Models__: Defines the core structure of the data that will be used within the application.

- __Repositories__: Interfaces used by the use cases. Implemented in the data layer.

- __Use cases: They enclose a single action, like getting data from a database or posting to a service. They use the repositories to resolve the action they are supposed to do. They usually override the operator “invoke”, so they can be called as a function.

### 2. Data.
The ```data``` layer is responsibile for selecting the proper data source for the domain layer. It contains the implementations of the repositories declared in the domain layer. 

Components of data layer include:
- __models__

    -__dto models__: Defines POJO of network responses.

    -__entity models__: Defines the schema of SQLite database.

- __repository__: Responsible for exposing data to the domain layer.

- __mappers__: They perform data transformation between ```domain```, ```dto``` and ```entity``` models.

- __remote__: This is responsible for performing network operations eg. defining API endpoints using [Retrofit](https://square.github.io/retrofit/).

- __local__: This is responsible for performing caching operations using [Room](https://developer.android.com/training/data-storage/room).

### 3. Presentation.
The ```presentation``` layer contains components involved in showing information to the user. The main part of this layer are the views(activity) and viewmodels.

# Tech Stack.
This project uses some of the popular libraries, plugins and tools of the android ecosystem.

## DEPENDENCIES

Used kotlin dsl to easily manage and update the dependencies

## Libraries.

- [Hilt](https://dagger.dev/hilt/) - Dependency Injection library.
- [Jetpack](https://developer.android.com/jetpack)
  
  -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
  -   [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android.
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
    
    - [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) -Component that allows easier implementation of navigation from composables.


- [Retrofit](https://square.github.io/retrofit/) - Type-safe http client and supports coroutines out of the box.
- [OkHttp-Logging-Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines.
- [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.
- [Square/Logcat](https://github.com/square/logcat) -Library for easier logging.
- [Material Design](https://material.io/develop/android/docs/getting-started/) - Build awesome beautiful UIs.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines,provides runBlocking coroutine builder used in tests.

- [Compose-Accompanist](https://google.github.io/accompanist/) -Accompanist is a group of libraries that aim to supplement Jetpack Compose with features that are commonly required by developers but not yet available.

## Testing

The tests are mainly unit tests covering the Network layer and Domain layer and also viewmodel tests for the characters module,main Libraries used for
testing include:
  - [Junit](https://junit.org/junit4/) - JUnit is a simple framework
to write repeatable tests
  - [Hamcrest](http://hamcrest.org/) - Matchers that can be combined to
create flexible expressions of intent
  - [Coroutines Testing](https://developer.android.com/kotlin/coroutines/test) - Test utilities for
kotlinx.coroutines.
  - [CashApp Turbine](https://github.com/cashapp/turbine) - A small testing library for kotlinx.coroutines Flow
  

# References
In this section i've included some resources ie. articles and GitHub repositories that are helpful when learning about clean architecture:

1. [The clean code blog](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) by Robert C. Martin.
2. [A detailed guide on developing android apps using clean architecture pattern](https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029) Medium article.
3. [Clean Architecture Component Boilerplater](https://github.com/bufferapp/clean-architecture-components-boilerplate) GitHub repo .
4. [Clean architecture sample](https://www.youtube.com/watch?v=Mr8YKDh3li4) Video by Philip Lackner
5. [Foodies](https://github.com/LinusMuema/foodies/) Github Repo by [Linus Moose](https://github.com/LinusMuema) to show how to build UIs with Compose
6. [Pokedex](https://github.com/ronnieotieno/PokeApi-Pokedex) by [Ronnie Otieno](https://github.com/ronnieotieno) demonstrating how to properly use Jetpack Components







