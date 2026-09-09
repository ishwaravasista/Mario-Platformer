# Mario Platformer

Mario Platformer is a Java platform game built with the Mayflower library. Guide Mario through three worlds by jumping across platforms, collecting cherries, avoiding enemies, and climbing vines.

## Features

- Three playable worlds with platforms, enemies, vines, and collectibles.
- Mario movement with running, jumping, climbing, and gravity.
- Animated character and enemy sprites.
- Collision detection for platforms, enemies, cherries, and vines.
- Score and life system, with cherries worth 100 points each.
- Game-over, victory, and replay screens.
- Background music and sound effects.

## Build and run

For BlueJ, open `package.bluej`, ensure the Mayflower library is available in your BlueJ environment, compile the project, and run `Runner.main(String[] args)` with an empty argument array (`{}`).

The project includes `mayflower3.3.7.jar` and `mayflower1.7.jar`; use the version configured for your course environment. Keep the `img` and `audio` folders in the project directory so the game can load its images and music.

## How to play

1. Press **Space** on the intro screen to start.
2. Use the **Left** and **Right Arrow** keys to move.
3. Press **Up** to jump while standing on a platform.
4. While touching a vine, use **Up** to climb and **Down** to descend.
5. Collect cherries to earn 100 points each.
6. Jump on enemies to knock them over. Touching an active enemy costs one life.
7. Climb above the top of the screen to progress from `MyWorld` to `World2`, then `World3`.
8. Exit through the top of `World3` to reach the victory screen.

Mario starts each world with three lives. Falling below the level or losing all three lives ends the run.

Press **Enter** on the game-over or victory screen to reach the replay screen, then press **Enter** again to restart.

## Gameplay notes and limitations

Knocked-over enemies can be pushed by touching them without damaging Mario.

`World2` and `World3` initially display `Score: ? Lives: ?` until a score or life update occurs.

Score carryover is currently incomplete because the Mario constructor does not assign the incoming score to its instance field, and the shared score tracker is not reset when replaying the game.
