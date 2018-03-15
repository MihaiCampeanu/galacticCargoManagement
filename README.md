Build a Galactic Cargo Management console application 

----------------------------------------------------------------------------------
Functional requirements :

The application has no UI (just console interaction) and no database. Data will be loaded from the 3 JSON OR XML (whichever you prefer) files (planets, ships, characters).
Interaction with the user will be made via console input.

Each ship has a type, a maximum cargo weight in KG that it can transport, and the speed in KM/hour. 
Each planet is at different distance in KM from the current point.
Characters can only pilot the ships identified by the "shipsType" array.

At runtime, the user will be asked which driver/character he wants to use.
The user will then input a cargo weight and the destination planet.

The application, should list all the ships that can transport that cargo weight, the time it will take to transport the cargo, and how many trips the ship will have to make to transport the cargo.
Ships should be listed ordered, with the ship that will transport the cargo the fastest, first.

EG : for instance, Han Solo, can transport a cargo of 50.000KG to Jakku, in 60 hours, using the Millenium Falcon in 2 trips ( note, he will also have to return to the current location after finishing the first cargo delivery )
