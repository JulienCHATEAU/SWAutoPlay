# SWAutoPlay

SWAutoPlay is an automaton abled to play the mobile game **Summoners War: Sky Arena** itself

## Features

You can run several dongeons and other features such as :
- Giant B10
- Drake B10
- Necropolis B10
- Scenario
- ToA normal and hard
- Karzhan
- Ellunia
- Lumel
- Elemental Rift Beasts
- Rivals (NEW)

## Installation and launch

You will have to use Android Studio to install the project. First, import the project then run the `../SWAutoPlay/app/src/androidTest/java/com/example/swautoplay/SwAutoPlayTest.java` class.
If everything went correctly, the SWAutoPlay mobile application is supposed to be installed on your phone, which means the bot is ready to be used.

## Usage

You can update the bot function with Android Studio adding/updating **testInstrumentationRunnerArgument** fields in `SWAutoPlay/app/build.gradle` : 
 > defaultConfig {   
        - applicationId "com.example.swautoplay"   
        - [...]   
        - testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"   
        - **testInstrumentationRunnerArgument** "DungeonName", "Drake"   
        - . . .   
    }   
   
| Argument name       | Possible values                                                                                 |
|---                  |---                                                                                              |
| DungeonName         | Giant / Drake / Necropolis / ToA / Karzhan / Ellunia / Lumel / Beasts / Rivals                  |
| StartTestPosition   | ToA / Home / Island                                                                             |
| Refill              | Chest / SocialPoint / Crystals / None                                                           |
| Difficulty          | Hard / Normal / Hell / Vestige / Rune                                                           |
| AverageDungeonTime  | An integer                                                                                      |
| RunCount            | An integer                                                                                      |
| StartStage          | An integer                                                                                      |
| Level               | An integer                                                                                      |
| HoH                 | A boolean                                                                                       |
| RivalsStates        | A binary string with 9 bits ('10010000' means only Gready and Morgana are ready to battle)      |

However that's a tedious way to use this application. A Graphical User Interface is provided to make it easier : [SWAutoPlay_GUI](https://github.com/JulienCHATEAU/SWAutoPlay_GUI)

## Support

I don't expect anything but if you like my content and want to support me in my development offering me some coffee :
[ko-fi.com/s734my](https://ko-fi.com/s734my) 
