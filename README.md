# Y2020_Campbell_Loden_Period_1_Individual_Project

# README #

**Name:**	Loden Campbell

**Period:**	1

**Game Title:** Doodle King

## Game Proposal ##

I want to make a mashup of the games Jump King and Doodle Jump. In this new version, there will be a mission to jump through many platforms and shoot monsters to ultimately reach one of your character's loved one. You can get ammo packs along the way so there will be strategy and timing involved with shooting.

Game Controls:

+ Up Arrow Key to shoot your gun
+ Left Arrow Key to move left
+ Right Arrow Key to move right
+ Space button to jump

Game Elements:

+ Continuous level to add the effect of scaling a building
+ Platforms to jump on
+ Gravity so one jump won't bring you to the top
+ Ammo packs will respawn after a certain amount of time
+ A gun to shoot the monsters
+ Monters to block your path upward
+ Monsters take more shots to kill as you move upward
+ A large platform at the top where your loved one will be waiting
+ No fall damage

How to Win:

+ Jump to the very top without being killed by a monster

## Link Examples ##
Provide links to examples of your game idea.  This can be a playable online game, screenshots, YouTube videos of gameplay, etc.

+ [Jump King speedrun](https://www.youtube.com/watch?v=9_W-3lAljes)
+ [Doodle Jump](https://www.youtube.com/watch?v=wjofzwaC_Oo)

## Teacher Response ##

**Approved**

Your game description includes enough elements to be interesting and worthy.  Doodle Jump clones I've seen from students have ranged from very basic and not credit worthy to excellent with bonus points.  The most basic ones just spawn random platforms and get faster over time with no real sense of gameplay or purpose.  Not very good.  The best ones have either levels that are purposely designed and have good flow or have an intelligent random generation system that feels right.  The addition of the game elements you mentioned would likely make your game excellent.  Finally, one thing I'm unsure about from your description is the direction the player can fire.  Only upward?  Space jumps (and powers up the jump height) and up arrow fires but it's not clear which direction the shots go.

## Class Design and Brainstorm ##

Put all your brainstorm ideas, strategy approaches, and class outlines here

## Development Journal ##

Every day you work, keep track of it here.

**5/27/2020 (30 minutes)**

Goal:  Today, I will brainstorm the neccessary classes and interfaces that I need to create my game.

Work accomplished:  I have created classes for the overall game, player world, platforms, player, bullets, and monsters, in addition to the Actor and World class. I plan to have the platforms spawn randomly between the maximum jump distance of jumping right or left from a platform, so no jump is impossible. It has also occured to me that I have to make sure that a platform doesn't spawn directly above a consecutive platform, so the game will still be playable. I will also make it so that bullets only travel upward because monsters will only be above the player, and will stop appearing when the ammo count drops to 0. Lastly, I need to find sprites of the player to ensure that it will look like your character is jumping instead of levitating with gravity.

**5/28/20 (3 hours 30 minutes)**

Goal: Today, I will make the image sprites for the actors in my game and work on the jumping mechanic.

Work accomplished:  Making the actor images went well, as I was able to find images for all of them. However, jumping took a long time to figure out because I want to make it so that after a certain amount of time after holding the space bar, you will jump automatically to keep from jumping way too high. Doing this made jumping much more complicated than I thought, I also added an integer variable with a maximum value. Also, gravity and boundaries had to be added so jumping can properly work. I added these through dx and dy values, and used getOneIntersectingObject(Platform.class) for boundaries. However, when all of this was implemented, I recieved errors about JNI and installation errors, so I will check what is wrong tomorrow.

**5/29/20 (5 hours)**

Goal: Today, I will look into the error and figure out how to fix it, I will complete jumping mechanics with images changes, and I will implement how the bullets will be made.

Work accomplished:  First, making the player show up on the screen in the right spot was difficult. While my positioning coordinates were correct, the player spawned with about 30% of its body underneath the world. This took almost 30 minutes to figure out, because the only keyword that could be used for the screen width and height was scene, not world, stage, or getFitWidth/Height. After the positioning was working, I realized that jumping didn't work either. To fix this, I changed the method of jumping. Instead of changing dy depending on whether it reached a maxHeight of the jump or not and making it positive or negative based on the result, I create a variable for velocity which increases every frame, so it essentially seems more realistic. I also polished up the player image swapping so that the images did not start in different places. After changes these factors, jumping and shooting worked perfectly.
