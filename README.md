# The shortest route calculator for the St. Petersburg metro
## Description ##
🚇📍🗺️ This app searches for the shortest route from one station to another in the St. Petersburg metro and calculates the approximate time that will be spent.

## How to use ##
First you need to type the departure station, then the destination station. The app will calculate the shortest route and approximate time that will be spent.

![image](https://github.com/SStaro/SPBMetro/assets/102288630/867e19b9-c569-4b85-87ac-2e6ae657916c)
## Getting Started ##
To download the project, you need to download the archived folder with the project or clone the repository using the following command:

`
git clone https://github.com/SStaro/searchengine.git <path-to-folder-where-to-clone-repository>
`
> [!IMPORTANT]
>File `/resources/map.json` contains a list of the metro lines and stations.
>Class RouteCalculator.java contains the constants INTER_STATION_DURATION (approximate time between two nearest stations on the same line, default value: 2.5) and INTER_CONNECTION_DURATION (approximate time between two stations on two different lines, default value: 3.5).



You need to create a jar file using the `mvn package` command, or using the IDE interface

![image](https://github.com/SStaro/SPBMetro/assets/102288630/87e3ca8b-15f2-466a-a44d-8fa454250986)


Launch the app using the command `java -jar "path-to-file"`
