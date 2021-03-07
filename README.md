![TestDrivenDevelopmentInAndroid](documentation/images/Logo-new.png)

# DroidKotlinFlow
This project explains how to use kotlin flow in android 

## Introduction to kotlin flow 
As an android developer, you would have come across RxJava for asynchronous programming, RxJava has operators to almost everything. With kotlin flow we handle a stream of data that emits values sequentially 



![Koin](https://img.shields.io/badge/Koin-Dependency%20Injection-orange)
![Architecture](https://img.shields.io/badge/MVVM-Architecture-red)
![KotlinFlow](https://img.shields.io/badge/KotlinFlow-API-yellowgreen)
![Coroutines](https://img.shields.io/badge/Coroutines-API-green)
![StateFlow](https://img.shields.io/badge/StateFlow-API-green)


## Guide to state flow 
* State flow is basically live data based on co-routines
* State flow needs to have a initial value but livedata does not, Advantage is that if there is a initial value meaning there is always a value and the state can't be null at any moment
* State flow will be active when your app is in background but, live data won't
* State flow can use powerful flow operators by transforming the data into something new before being observed. 


### Samples:
* **Single Network Call:**  Single network request
    * [Model ------ Code](app/src/main/java/com/demo/flow/viewmodels/SingleNetworkCallViewModel.kt)
    * [View ------- Code](app/src/main/java/com/demo/flow/view/fragments/SingleNetworkCallFragment.kt)
    * [ViewModel -- Code](app/src/main/java/com/demo/flow/viewmodels/SingleNetworkCallViewModel.kt)
    * [Repository - Code](app/src/main/java/com/demo/flow/network/repository/PlaylistRepository.kt)
    * [Service ---- Code](app/src/main/java/com/demo/flow/network/services/PlayListService.kt)