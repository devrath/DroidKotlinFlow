![TestDrivenDevelopmentInAndroid](documentation/images/Logo-new.png)

# DroidKotlinFlow
This project explains how to use kotlin flow in android 

## Introduction to kotlin flow 
As an android developer, you would have come across RxJava for asynchronous programming, RxJava has operators to almost everything. With kotlin flow we handle a stream of data that emits values sequentially 


![Hilt](https://img.shields.io/badge/Hilt-Dependency%20Injection-orange)
![Koin](https://img.shields.io/badge/Koin-Dependency%20Injection-orange)
![Unit Testing](https://img.shields.io/badge/Unit%20Testing-Testing-blue)
![Architecture](https://img.shields.io/badge/MVVM-Architecture-red)
![KotlinFlow](https://img.shields.io/badge/KotlinFlow-API-yellowgreen)
![Coroutines](https://img.shields.io/badge/Coroutines-API-green)
![LiveData](https://img.shields.io/badge/LiveData-API-brightgreen)


#### Setting up the Mockoon backend
1. Find out the localhost from your pc ( Ex: IPv4 Address. . . . . . . . . . . : 192.178.1.1 )
2. [Install Mockoon from their official site](https://mockoon.com/ "Install Mockoon from their official site")
3. Set up the params like port number(2999) and end point(playlists) and type of request(GET)..... ( Ex: http://192.178.1.1:2999/playlists )
4. Add the sample json in mockoon console body from this readme file
5. Click start server

#### <--------------------- Json Samples to add in Mockoon --------------------->

#### Json sample for playlist
```json
[
  {
    "id": "1",
    "name": "Dil se re",
    "category": "hindi"
  },
    {
    "id": "2",
    "name": "Jazzed music beats",
    "category": "Jazz Type"
  },
    {
    "id": "3",
    "name": "Top kannada Hits",
    "category": "Remix"
  },
    {
    "id": "4",
    "name": "80's Hindi Songs",
    "category": "hindi"
  },
    {
    "id": "5",
    "name": "Everybody Rock on",
    "category": "english"
  },
  {
    "id": "6",
    "name": "90's flashback",
    "category": "pop"
  },
  {
    "id": "7",
    "name": "Machine singing",
    "category": "electro"
  },
  {
    "id": "8",
    "name": "Let's Party",
    "category": "mixed"
  },
  {
    "id": "9",
    "name": "Feel The Music",
    "category": "electro english"
  },
  {
    "id": "10",
    "name": "Best Songs 2020",
    "category": "kannada"
  }
 ]
```
#### <--------------------- Json Samples to add in Mockoon --------------------->

