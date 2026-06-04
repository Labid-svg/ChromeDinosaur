# ChromeDinosaur

This is a Chrome browser's offline Dinosaur Game created by using Java Language. in this game, Dinosaur will be running to the right when the game strarted and cactus and bird will be coming from the left, which are randomly generated using the function Math.random(). Besides the score will be incrementing until the dinosaur collides with the cactus. 

Command: Player needs to press the SPACE key to jump over the cactus. Dinosaur can't able to jump double time until it go back to the ground.

This game is split into 6 files:

1. App.java: This is the main file, which creates the window to run the game.
2. GameEntity.java: It contains all the methods of the game, that must be defined for all objects.
3. Block.java: This Class implements GameEntity. This is an abstract class, which shares positions, height, weight, image. All fields are private and used setter and getter method, Because This is an Abstract Class.
4. Dinosaur.java: This file is for operating the dinosaur. Operation's like, Dinosaur movement, jumping, Collision.
5. Cactus.java: creates cactus randomly and extends the Block classes entities.
6. Bird.java: creates bird randomly and extends the Block classes entities.
7. ChromeDinosaur.java: This is the main game portion. Here, all fields and methods are initialized for all objects. 

This Game is created using OOP Concepts like: Encapsulation, Abstraction, Inheritance, Interface, and Polymorphism.

Game Interface: 

<img width="850" height="286" alt="Screenshot from 2026-06-04 08-44-11" src="https://github.com/user-attachments/assets/589837f7-4558-48bf-bcfe-edb0657f6d02" /> </br>
<img width="850" height="286" alt="Screenshot from 2026-06-04 08-42-45" src="https://github.com/user-attachments/assets/355a16b2-4ea4-4706-9f57-68e0e1e35c21" /> </br>
<img width="850" height="286" alt="Screenshot from 2026-06-04 08-48-42" src="https://github.com/user-attachments/assets/d629295b-0ab0-4166-8860-d268234295ab" />


Game Tutorial:
[Screencast from 2026-06-04 08-44-23.webm](https://github.com/user-attachments/assets/53be2d6a-1642-4ce7-b0c9-63fb3e47f313)

