# SWAutoPlay

SWAutoPlay is an automaton abled to play the mobile game **Summoners War: Sky Arena** itself

## Features

You can run several dungeons and other features such as :
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
   
| Argument name       | Possible values                                                                                       |
|---                  |---                                                                                                    |
| DungeonName         | Giant / Drake / Necropolis / ToA / Karzhan / Ellunia / Lumel / Beasts / Rivals / Raid                 |
| StartTestPosition   | ToA / Home / Island / Rivals                                                                          |
| Refill              | Chest / SocialPoint / Crystals / None                                                                 |
| Difficulty          | Hard / Normal / Hell / Vestige / Rune                                                                 |
| AverageDungeonTime  | An integer                                                                                            |
| RunCount            | An integer                                                                                            |
| StartStage          | An integer                                                                                            |
| Level               | An integer                                                                                            |
| HoH                 | A boolean                                                                                             |
| RivalsStates        | A binary string with 9 bits (e.g, '10010000' means only Gready and Morgana are ready to battle)       |

However that's a tedious way to use this application. A Graphical User Interface is provided to make it easier : [SWAutoPlay_GUI](https://github.com/JulienCHATEAU/SWAutoPlay_GUI)

## Raid auto farming notes

- If you want to auto farm Raid content you have to launch the runs from the lobby room without being ready. 
- Refills aren't available for Raid but you can do it manually during the runs, the bot will keep working afterwards.
- BJ4/5 runs would work perfectly as long as the average time is constantly 28sec so take advantage of it !

## Support

I don't expect anything but if you like my content and want to support me in my development you can offer me some coffee :
[ko-fi.com/s734my](https://ko-fi.com/s734my) 
