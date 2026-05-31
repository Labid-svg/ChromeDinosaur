# ChromeDinosaur

This is a Chrome browser's offline Dinosaur Game created by using Java Language. in this game, Dinosaur will be running to the right when the game strarted and cactus will be coming from the left, which are randomly generated using the function Math.random(). Besides the score will be incrementing until the dinosaur collides with the cactus. 

Command: Player needs to press the SPACE key to jump over the cactus. Dinosaur can't able to jump double time until it go back to the ground.

This game is split into 6 files:

1. App.java: This is the main file, which creates the window to run the game.
2. GameEntity.java: It contains all the methods of the game, that must be defined for all objects.
3. Block.java: This Class implements GameEntity. This is an abstract class, which shares positions, height, weight, image. All fields are private and used setter and getter method, Because This is an Abstract Class.
4. Dinosaur.java: This file is for operating the dinosaur. Operation's like, Dinosaur movement, jumping, Collision.
5. Cactus.java: creates cactus randomly and extends the Block classes entity.
6. ChromeDinosaur.java: This is the main game portion. Here, all fields and methods are initialized for all objects. 

This Game is created using OOP Concepts like: Encapsulation, Abstraction, Inheritance, Interface, and Polymorphism.
