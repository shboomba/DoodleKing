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

+ Continuous level to add the effect of going up
+ Platforms to jump on
+ Gravity so one jump won't bring you to the top
+ Ammo packs to replenish ammo to shoot with
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

Work accomplished:  I have created classes for the overall game, player world, platforms, player, bullets, and monsters, in addition to the Actor and World class. I plan to have the platforms spawn randomly between the maximum jump distance of jumping right or left from a platform, so no jump is impossible. It has also occurred to me that I have to make sure that a platform doesn't spawn directly above a consecutive platform, so the game will still be playable. I will also make it so that bullets only travel upward because monsters will only be above the player, and will stop appearing when the ammo count drops to 0. Lastly, I need to find sprites of the player to ensure that it will look like your character is jumping instead of levitating with gravity.

**5/28/20 (3 hours 30 minutes)**

Goal: Today, I will make the image sprites for the actors in my game and work on the jumping mechanic.

Work accomplished:  Making the actor images went well, as I was able to find images for all of them. However, jumping took a long time to figure out because I want to make it so that after a certain amount of time after holding the space bar, you will jump automatically to keep from jumping way too high. Doing this made jumping much more complicated than I thought, I also added an integer variable with a maximum value. Also, gravity and boundaries had to be added, so jumping can properly work. I added these through dx and dy values, and used getOneIntersectingObject(Platform.class) for boundaries. However, when all of this was implemented, I received errors about JNI and installation errors, so I will check what is wrong tomorrow.

**5/29/20 (5 hours)**

Goal: Today, I will look into the error and figure out how to fix it, I will complete jumping mechanics with images changes, and I will implement how the bullets will be made.

Work accomplished:  First, making the player show up on the screen in the right spot was tricky. While my positioning coordinates were correct, the player spawned with about 30% of its body underneath the world. This took almost 30 minutes to figure out because the only keyword that could be used for the screen width and height was scene, not world, stage, or getFitWidth/Height. After the positioning was working, I realized that jumping didn't work either. To fix this, I changed the method of jumping. Instead of changing dy depending on whether it reached a maxHeight of the jump or not and making it positive or negative based on the result, I create a variable for velocity, which increases every frame, so it essentially seems more realistic. I also polished up the player image swapping so that the images did not start in different places. After changing these factors, jumping and shooting worked perfectly.

**5/30/20 (3 hours)**

Goal: Today, I will create platforms and complete shooting with ammo.

Work accomplished:  Creating platforms was a callous and slow process due to disappearing and going off-screen. At first, I had to do some math to figure out how to add a random platform above the previous platform without making an impossible jump. This took about 30 minutes but didn't get me very far. Despite going out of my way to make jumps possible, there were still jumps that could not be made. So, I spent another 30 minutes on this single problem and fixed the x positioning of the platform based on the y positioning, so there wouldn't be a platform that is too far bot horizontally and vertically. It felt great to move on from this because it was challenging to do multiple trials and attempts and change one variable by a tiny bit over and over again. Unfortunately, but also expectedly, I had more issues with the platforms. Sometimes when a platform was close to the edge, the next platform would spawn off the screen, making yet another jump impossible. Thankfully this was a lot easier to fix since I just had to figure out where to set the x position of the platform so that there would be just enough space to hold the player width. After fixing these two problems, it seemed as though the platforms worked as intended. Shooting took me a very short amount of time since I had not yet incorporated the ammo packs or ammo in general, so I just needed to check if a key was pressed and animate the bullet going upward.

**5/31/20 (10 hours)**

Goal: Today, I will basically finish my game.

Work accomplished:  

The things I needed to do: ammo, ammo packs, win conditions, lose conditions, creating separate screens to switch to, music, and eye candy. Creating the ammo was extremely easy because it was very similar to the score class from Breakout, only this kept track of a different game element. I think AmmoPack was the easiest class to create because of how little work had to be put into it. There is nothing to get from the class, and you don't check anything constantly in the act() method, which only leaves creating an image for the ammo packs. 

Win conditions were a little harder, because I messed up the correct places to put methods and variables to keep track of the state. After using a boolean property, it worked just fine. Separate screens at switching was extremely straightfoward thanks to Breakout, so that took about 20 minutes. All I needed to change was making an additional channel and adding new controls and game actors. However, after running through my game a few times, I noticed a problem with the way my platforms worked. If my head was inside a platform at hax height during a jump, I would be teleported to the top of the platform. This was not supposed to happen, so I had to debug a little to figure out where I was messing up. I located the error in the code and set up another condition before landing on a platform. 

After doing all of this, there were still 6 hours before the deadline, so I still had time to add extra features like sound and backgrounds. Implementing sound took a lot longer than it should have due to both extra noise and improper syntax. When recording a song to go along with my game, there were many other sounds that forced me to have to retake the song over and over again. "Other sounds" includes shutting doors, talking, dogs barking, and kitchen noises. All for a short 3 minute soundtrack. The syntax problem that occurred was with naming the file. Originally, I named it like an image, but then I eventually figured out that I need the actual path from src to make it work. After this, there was only background left. I took some time looking for a good image for the game background, but there were no images that I felt were perfect for my game. The background I stuck with seems out of place, but it was probably the best one I found. I really like the title background because it looks very calming, and in order to survive the actual game Jump King, you can't get angry or else you're going to have a very bad time. The ending background for when you win I also like because it completely captures the final outcome that I did not capture (Once the Player touches the girl, the screen switches. There is no animation for making it all the way to the girl). Overall, I feel like I did well in capturing the best parts of each of the games, and I had fun creating them too. 


##Total Time Spent: 22 hours##
