# Tennis

## Description
This project is the begining of my portfolio and can be used as an example of an Android app. Since Google is investing a lot in Kotlin for Android, the need for more examples using idiomatic Kotlin code, instead of just "Java code" written with Kotlin keywords. Right now, this is a simple and stylish tennis scoreboard for Android. Match history and multi player sync will be implemented. More on features to be developed in Roadmap section.

## Who am I
My name is Vitor, I\`m 21 and I\`ve been working and studying mobile apps, specially in Android, for a few years. I\` always looking for new things to learn and trying to evolve my technical skills. [LinkedIn](https://www.linkedin.com/in/vitor-sramos).

## Roadmap
### Improvements
Every step of the way, I\`ll be fixing bugs and refactoring code. It can be a bug that I miss, a new and better way of doing something already implemented or I learned something new.

### Tests
The next step is introducing tests in the project. Some end to end test can assure that basic user interactions won\`t brake. Integration tests will be helpful as the project grows larger and uses different technologies, as explained below. I\`ll try to implement as many unit test as I can.

### Room
The app will save a match history locally with players names, score, date, and location (not implemented yet). I\`ll use Room, a Jetpack abstraction of SQLite for Android.

### Firebase
The user will be able to invite another player into the match, syncing the match score in real time. I\`ll use Firebase Auth to login users e Cloud Firestore to record info, sync devices and backup history.

### Location
The app will use device\`s location to tag matches in history.

### Bluetooth
As an alternative to using cloud to sync score, the user will be able to pair two devices using the app and sync match information without internet access.

### Wear OS
A version of the app will run on Wear OS so players can keep score count as they play.
