# SWAutoPlay

SWAutoPlay is an automaton abled to play the mobile game **Summoners War: Sky Arena** itself

## Features

You can run several dongeons and other features such as :
- Giant B10
- Drake B10
- Necropolis B10
- Scenario
- ToA normal and hard
- Daily reward (coming soon)

## Installation and launch

It is recommended that you use Android Studio to install this project on your smartphone. As soon as you can open SWAutoPlay mobile application on your phone, the bot is ready to be launched.

## Usage

You can update the bot function with Android Studio adding/updating **testInstrumentationRunnerArgument** fields in `SWAutoPlay/app/build.gradle` : 
 > defaultConfig {   
        - applicationId "com.example.swautoplay"   
        - [...]   
        - testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"   
        - **testInstrumentationRunnerArgument** "DungeonName", "Drake"   
        - . . .   
    }   
   
| Argument name       | Possible values                   |
|---                  |---                                |
| DungeonName         | Giant / Drake / Necropolis / ToA  |
| StartTestPosition   | ToA / Home / Island               |
| Refill              | Chest / SocialPoint / Crystals    |
| Difficulty          | Hard / Normal / Hell              |
| AverageDungeonTime  | An integer                        |
| RunCount            | An integer                        |
| StartStage          | An integer                        |
| HoH                 | A boolean                         |

However that's a tedious way to use this application. A Graphical User Interface is provided to make it easier : [SWAutoPlay_GUI](https://github.com/JulienCHATEAU/SWAutoPlay_GUI)

## Support

I don't expect anything but if you like my content and want to support me in my development offering me some coffee :
ko-fi.com/s734my
