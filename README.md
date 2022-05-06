# 2048 using JavaFX

This is an implementation of the [2048](https://en.wikipedia.org/wiki/2048_(video_game)#Gameplay) game using
JavaFX. [Scene Builder](https://gluonhq.com/products/scene-builder/) was used to create the layout of the game and CSS
was use for styling view components.

## How to play

Using WASD keys move the values in the grid to aggregate same values till get 2048!

## How to use

Use [maven](https://maven.apache.org/) to build the project. Run all commands from the root project folder. Additional
dependencies are AnimateFX and FontAwesomeFX

```bash
mvn clean install
```

## How to run

This project was created using IntelliJ IDE, you can use PLAY button on Application class

## Tests

Run Unit tests

```bash
mvn test
```

Run Integration tests

```bash
mvn verify
```

## Coding style

This project is develop under
the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)