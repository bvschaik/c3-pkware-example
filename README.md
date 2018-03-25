# Caesar 3 PKWare decompression example

This program is an example program for demonstrating how to decompress parts of the Caesar 3 .sav game file format.

## Compiling

Requirements: Java 8 compiler and Maven.

Run the following command from the command-line or your IDE:

    mvn package

The executable JAR file is now in the `target` director.

## Running the program

From the command-line, run:

    java -jar PATH_TO_JAR_FILE example.sav

Where `PATH_TO_JAR_FILE` is the path to the jar file you built in the Compiling step, and `example.sav` is a C3 saved game.

The program will print out an ascii-representation of the terrain.

## Legend

### Natural elements
- `Y` Tree
- `v` Scrub
- `O` Rock
- `/` Elevation
- `\` Access ramp for elevated area
- `~` Water
- `_` Meadow
- `.` Empty land

### Human elements
- `B` Building
- `z` Garden
- `=` Road
- `|` Aqueduct
- `w` Wall
- `W` Gatehouse
- `;` Rubble

## Example output

Map of the first mission:

    YYYYYYYYYYYYYYYYYY..O=..~~~~.YYYYYYYYOOO
    YYYYYYYYYYYYYYYYYY...=...~~~~.YYYYYY.OOO
    YYYYYYYYYYYYYYYYYYY..=..Y.~~~~.YYYY.OOOO
    YYYYYYYYYYYYYYYYYYYY.=.YYY.~~~~~YYY.OOO.
    YYYYYYYYYYYYYYY.YYYYY=YYYYY~~~~~~...OOOO
    YYYYYYYYYYYYYYYY.YYY.=.YYYY..~~~~~~OOOOO
    YYYYYYYYYYYYY.YYYYY..=YYYYYYY~~~~~~~OOOO
    .YYYYYYYYYY....YYYY..=YYYYYYYYY~~~~~~.O.
    YYYYYYYYYY......YYYY.=YYYYYYYY.Y~~~~~...
    YYYYYYYYYY.......YYYY=.YYY...YY..~~~~~~~
    ~YY.YY...YYYY.....YYY=.YY.OOOOO..O.~~~~~
    ~~Y.YYY...YYYY.....Y.=.YYYOOOOOOOOO.~~~~
    ~~~.YYYY...YYYY......=..YYOOOOOOOOO..~~~
    ~~~..YYY....Y.YY.....=.YY.OOO.YY..YY.~~~
    ~~~..YYY....YYYYY....=.Y..OOO.YYYYYYYYY.
    ~~~...YYY...YY.YYY...=....OOOYYYYYYYYYYY
    ~~~~..YYY..YYYYYYY...=...YOOYYYYYYYYYYYY
    .~~~...YYY..Y.YYYYY..=..Y.YOOYYYYYYYYYY.
    .~~~~..YYYYYYYYYYY...=.YYY.YO.YYYYYYYYYY
    .~~~~~..YYYYYYYYYYY..=.YYY.OOO.YYYYYYYYY
    .~~~~~~..YYYYYYYYYYY.=YYYYY.OO.YYYYYYYYY
    ...~~~~~.YYYYY..YYYYY=YYYYY.YOO.YYYYYYY.
    ..Y.~~~~~YYYY...YYYY.=.YYYY..OOOOYYYYYY.
    .YYY.~~~~~YYYY..YYY..=..YYY..YOOOOOYYYYY
    YY.YY~~~~~~YYYYYYYY..=..YYYY...OOOOOYYYY
    YYYYY.~~~~~.YYYYYYYY.=...YYYY...O.OO.YYY
    YYYYYY~~~~~.YYYYYY.YY=..YYYYYY...OOOOYYY
    YYY.YY~~~~~..YYY.YYYY.BBBBBYYYY...OOOOY.
    YYYYYY~~~~~..YYYYYYY.=BBBBBYYYY....OOOOO
    YYY.YY~~~~~~.YYYYYYBB=BBBBBY.YY..OOOOOOO
    YYYY.Y~~~~~~.YYY.Y.BB=BBBBBY.YYY..OOOOOO
    YY.Y...~~~~~~.YYY.BBB=BBBBBY.YYY..OOOO.O
    YYYYYY..~~~~~.YYY..BB=BBBBBYY.YYY..OOOOO
    YYYYYYY...~~~~.Y...BB=BBBBBYYYYYY..O.OO.
    YY.YY.YY.~~~~~.YY..BB======YYYYYY..OOOOO
    YYYYYYYYYY~~~~YYYYBBB=BBBB.YYYYYY..OOOOO
    .YYYYYY.YYY~~~~YYY...=BBBB.YYYYY...OOOOO
    ..YYYYYYYYYY~~~YYYY..=BBY.YYYYYYY..OOOOO
    ...YYYYYYYY~~~~~YY..O=..YYYYYYYYYY.OOOOO
    .......YYYYY~~~~.....=.YYYYYYYYYYYY.OOOO
