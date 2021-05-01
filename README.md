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
* **Single Network Call:**  This is performing a single networking request
    * **[Model](app/src/main/java/com/demo/flow/models/ApiUser.kt)**
      <->
      **[View](app/src/main/java/com/demo/flow/presentation/view/fragments/SingleNetworkCallFragment.kt)**
      <->
      **[ViewModel](app/src/main/java/com/demo/flow/presentation/viewmodels/SingleNetworkCallViewModel.kt)**
      <->
      **[Repository](app/src/main/java/com/demo/flow/network/repository/UsersRepository.kt)**
      <->
      **[Service](app/src/main/java/com/demo/flow/network/services/UserListService.kt)**
      
* **Parallel Network Call:**  This involves making two networking requests in parallel, Once both are completed the results of both of them are returned at once.
    * **[Model](app/src/main/java/com/demo/flow/models/ApiUser.kt)**
      <->
      **[View](app/src/main/java/com/demo/flow/presentation/view/fragments/SingleNetworkCallFragment.kt)**
      <->
      **[ViewModel](app/src/main/java/com/demo/flow/presentation/viewmodels/ParallelNetworkCallViewModel.kt)**
      <->
      **[Repository](app/src/main/java/com/demo/flow/network/repository/UsersRepository.kt)**
      <->
      **[Service1](app/src/main/java/com/demo/flow/network/services/UserListService.kt)**,
      **[Service2](app/src/main/java/com/demo/flow/network/services/MoreUsersListService.kt)**
      
* **Filter Operator For A Network Call:**  This involves fetching a list from a server, Then filtering the users whose name starts with 'A'
    * **[Model](app/src/main/java/com/demo/flow/models/ApiUser.kt)**
      <->
      **[View](app/src/main/java/com/demo/flow/presentation/view/fragments/OperatorFilterFragment.kt)**
      <->
      **[ViewModel](app/src/main/java/com/demo/flow/presentation/viewmodels/OperatorFilterViewModel.kt)**
      <->
      **[Repository](app/src/main/java/com/demo/flow/network/repository/UsersRepository.kt)**
      <->
      **[Service](app/src/main/java/com/demo/flow/network/services/UserListService.kt)**

* **Iterator Operator For iterating the elements:**  This involves fetching a list from a server and iterating the elements in different ways 
    * **[Model](app/src/main/java/com/demo/flow/models/ApiUser.kt)**
      <->
      **[View](app/src/main/java/com/demo/flow/presentation/view/fragments/OperatorIteratorsFragment.kt)**
      <->
      **[ViewModel](app/src/main/java/com/demo/flow/presentation/viewmodels/OperatorIteratorsViewModel.kt)**
      <->
      **[Repository](app/src/main/java/com/demo/flow/network/repository/UsersRepository.kt)**
      <->
      **[Service](app/src/main/java/com/demo/flow/network/services/UserListService.kt)**
    * Iterating operators used are ---- > ```Iterator```, ```For```, ```ForEach```, ```ForEachIndexed```

* **Map Operator For transforming the elements:**  This involves fetching a list from a server and transforming it, we are displaying the last element using a transformation operator
    * **[Model](app/src/main/java/com/demo/flow/models/ApiUser.kt)**
      <->
      **[View](app/src/main/java/com/demo/flow/presentation/view/fragments/OperatorMapFragment.kt)**
      <->
      **[ViewModel](app/src/main/java/com/demo/flow/presentation/viewmodels/OperatorMapViewModel.kt)**
      <->
      **[Repository](app/src/main/java/com/demo/flow/network/repository/UsersRepository.kt)**
      <->
      **[Service](app/src/main/java/com/demo/flow/network/services/UserListService.kt)**

* **Reduce Operator For transforming the elements:** Here we shall use a collection of items. The use case of reduce operator is to reduce a list of items to a single item
    * **[Model](app/src/main/java/com/demo/flow/utils/Library.kt)**
      <->
      **[View](app/src/main/java/com/demo/flow/presentation/view/fragments/OperatorReduceFragment.kt)**
      <->
      **[ViewModel](app/src/main/java/com/demo/flow/presentation/viewmodels/OperatorReduceViewModel.kt)**
      <->
      **[Repository](app/src/main/java/com/demo/flow/network/repository/LibraryRepository.kt)**
      <->
      **[Service](app/src/main/java/com/demo/flow/network/services/LibraryService.kt)**

* **Math Operator For transforming the elements:** Here we shall use a collection of items. we shall get the list of price values and perform addition operation 
    * **[Model](app/src/main/java/com/demo/flow/utils/Library.kt)**
      <->
      **[View](app/src/main/java/com/demo/flow/presentation/view/fragments/OperatorMathematicalFragment.kt)**
      <->
      **[ViewModel](app/src/main/java/com/demo/flow/presentation/viewmodels/OperatorMathematicalViewModel.kt)**
      <->
      **[Repository](app/src/main/java/com/demo/flow/network/repository/LibraryRepository.kt)**
      <->
      **[Service](app/src/main/java/com/demo/flow/network/services/LibraryService.kt)**
    * Mathematical operators used for demo are are ---- > ```AVERAGE```, ```SUM```, ```SUM_BY```, ```MAX_BY```, ```MIN_BY```

<p align="center">
<a><img src="https://forthebadge.com/images/badges/built-for-android.svg"></a>
</p>
