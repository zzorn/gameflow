= FlowGame TODO =

1-> Tuning utility
* In-game tuning utility, expose and edit game parameters, save to (new) setting file, load into game
* Json or yaml style config file
* Tree structure for config file
* Built in color editor, direction editor, graphics chooser


2-> Keyboard input


3-> Mouse input


Allow resiable game canvas?  Or make it non-resizable.

GameMap
* Order entities by supplied depth ordering when added, and when requested.  Optionally every update

4-> Tile map
* Load from tiled format?
* Put tile, put rectangle of tiles
* Either put specific tile, or one from a group with relative probabilities
* Get tile type at point
* Get tiles overlapping rectangle
* Multiple layers, different scrollspeeds, entities in different layers

* Isometric map rendering support?




5-> Animations
* Straight loops, or markov chains
* Animation queuing
* Stop at next loop point and change to x animation -feature
* Get currently running animation name / type


6-> Collisions
* Organize entitites in groups, check collisions between groups / between an entity and a group
* Check collisions between an entity and tiles in a tile map


7-> (low pri) Sprite sheets
* Load many pictures from single large image, use naming convention specified in some datafile?


8-> Walk / jump code
* Keep track of whether an entity is on a walkable tile or in air






