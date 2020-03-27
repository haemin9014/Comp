/* setting up our game asthetics and important functionalities*/
var config = {
    type: Phaser.AUTO,
    width: 800,
    height: 500,
    parent: 'game',
    physics: {
        default: 'arcade',
        arcade: {
            gravity: { y: 0 }
        }
    },// end of physics
    scene: {
        preload: preload,
        create: create,
        update: update,

      
    }// end of scene

};//end of config

//creating variables for the game : player, leftkey and rightkey
var game = new Phaser.Game(config);
var player;
var leftKey;
var rightKey;
let leftBlock;
let middleBlock;
let rightBlock;
let alienArr;
let alienOne;
let alienTwo;
let alienThree;
let alienFour;
let movement = true;
var spacebar;
var bullets;
var bulletDelay = 0;
let num;
let count = 0 ;
let scoreText;
let gameEnd = false;

/*  where we load in external files (or “assets”)*/
function preload ()
{
    /* creating the sprite image of the ship in the preaload */
    this.load.image('player', 'assets/Ship.png');
   
    //blocks
    this.load.image('leftBlock', 'assets/FullBlock.png');
    this.load.image('middleBlock', 'assets/FullBlock.png');
    this.load.image('rightBlock', 'assets/FullBlock.png');

    //aliens
    this.load.image('alienOne', 'assets/InvaderA1.png');
    this.load.image('alienTwo', 'assets/InvaderB1.png');
    this.load.image('alienThree', 'assets/InvaderC1.png');
    this.load.image('alienFour', 'assets/InvaderA2.png');

    //bullets
    this.load.image('bullet', 'assets/Bullet.png');
    this.load.image('enemyBull', 'assets/Bullet.png');
    
}

/* where we define the GameObjects that are necessary at the start of our game*/
function create()
{   
    let x = this.sys.canvas.width / 2;
    let y = this.sys.canvas.height;
    this.player = this.add.image(x,y, 'player').setOrigin(0.5, 1);
    this.player.depth = 9999;

    this.leftBlock = this.add.image(200, 400, 'leftBlock');
    this.middleBlock = this.add.image(400, 400, 'middleBlock');
    this.rightBlock = this.add.image(600, 400, 'rightBlock');

    this.alienOne = this.physics.add.image(100, 100, 'alienOne');
    this.alienTwo = this.physics.add.image(300, 100, 'alienTwo');
    this.alienThree = this.physics.add.image(500, 100, 'alienThree');
    this.alienFour = this.physics.add.image(700, 100, 'alienFour');
    alienArr = [this.alienOne, this.alienTwo, this.alienThree, this.alienFour];

    //setting the left key and right key ans pssce
    this.leftKey = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.LEFT);  
    this.rightKey = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.RIGHT);
    this.spacebar = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.SPACE);

    //bullets for spaceship
    bullets = this.physics.add.group();
    bullets.physicsBodyType = Phaser.Physics.ARCADE;
    bullets.enableBody = true;

    //group the bullets to load all at once
    for (var i = 0; i < 30; i++) {
		bullets.create(0.5, 1, 'bullet');
    }
    
    //makes the bullet invisble and puts it behinf the player ship
    bullets.children.each(function(bullet) {
        bullet.setActive(false);
        bullet.setVisible(false);
        //index for css and puts the bullet behind
        bullet.depth = 0; // always in back
    }, this);

     //bullets for ALIENS
     alienbull = this.physics.add.group();
     alienbull.physicsBodyType = Phaser.Physics.ARCADE;
     alienbull.enableBody = true;
 
 
     //group the bullets to load all at once ALIENS
     for (var i = 0; i < 30; i++) {
        alienbull.create(0.5, 1, 'enemyBull');
        
     }
     
     //makes the bullet invisble and puts it behinf the player ship ALIENS
     alienbull.children.each(function(bullet) {
        bullet.setActive(false);
        bullet.setVisible(false);
        bullet.setTint(0xff00ff);
         //index for css and puts the bullet behind ALIENS
        alienbull.depth = 0; // always in back
     }, this);
     //set timer for aliens to randomnly shoot
    

}

//alien die
function die(alien, bullets){
   console.log("alein die!!");

    alien.destroy();
    count++;
    console.log(count);
    if(count >= 4){
    
        scoreText = this.add.text(200,200, 'YOU WIN!', {fontSize: '64px', fill: 'red'});
        gameEnd = true;
    }

}

// player die
function playerDie(player, bullets){
    console.log("die!!");
     player.setVisible(false);
     player.destroy();
     scoreText = this.add.text(200,200, 'YOU LOST. :(', {fontSize: '64px', fill: 'red'});
     gameEnd = true;
 }

/* where we define animation and interaction in our game updates every frame*/
function update() {
    // making the fucntionality of the keybords to work
   

    if (this.spacebar.isDown){
        shootBullet( this, this.player);
    }
     // Destroy off screen bullets so it wont bounce back
    bullets.children.each(function(bullet) {
        killIfOffscreen(bullet, this);
    }, this);

    if (this.leftKey.isDown && this.player.x > this.player.displayWidth * this.player.originX){
        this.player.x -= 10;
        console.log("clicked");
    }
    else if (this.rightKey.isDown && this.player.x < this.sys.canvas.width - this.player.displayWidth * this.player.originX){
        this.player.x += 10;

    }
    // OVERLAP kill alien when the bullet collides with it
    this.physics.add.overlap(this.alienOne, bullets, die, null, this);
    this.physics.add.overlap(this.alienTwo, bullets, die, null, this);
    this.physics.add.overlap(this.alienThree, bullets, die, null, this);
    this.physics.add.overlap(this.alienFour, bullets, die, null, this);
    this.physics.add.overlap(this.player, alienbull, playerDie, null, this);
    setInterval(randomAlien(this), 5000);
} // end of update 

//seperate functions outside for spaceship
function shootBullet(game, player) {
    if (game.time.now > bulletDelay) {
        bullet = bullets.getLast();
        if (bullet) { // a bullet exists
            bullet.setActive(true);
            bullet.setVisible(true);
            bullet.body.reset(player.x, player.y + 8);
            bullet.body.velocity.y = -900;
            bulletDelay = game.time.now + 200;
        }
    }
}

//seperate functions outside for ALIENS to randomnly shoot
function alienShoot(game, alien) {
    if (gameEnd) return;
        bullet = alienbull.getLast();
        if (bullet && alien.visible) { // a bullet exists
            bullet.setActive(true);
            bullet.setVisible(true);
            
            bullet.body.reset(alien.x, alien.y - 8);
            bullet.body.velocity.y = 900;
            
        }
}

//aliens attack back!!
function randomAlien(game) {
    num = Math.floor(Math.random() * 100)+ 1;
    let aliens = alienArr[Math.floor(alienArr.length * Math.random())];
    if (num % 50 == 0){
        alienShoot(game, aliens);
    }
   
}

function killIfOffscreen(sprite, game) {
    if (!Phaser.Geom.Rectangle.Overlaps(game.physics.world.bounds, sprite.getBounds())) {
        sprite.setActive(false);
        sprite.setVisible(false);
    }
}

    // if(this.movement){
    //     //Move Right
    //     this.alienOne.x += 50;
    //     this.alienTwo.x += 50;
    //     this.alienThree.x += 50;
    //     this.alienFour.x += 50;
    
    //     this.movement = false;
    // }
    // else{
    //     //Move Left
    //     this.alienOne.x -= 50;
    //     this.alienTwo.x -= 50;
    //     this.alienThree.x -= 50;
    //     this.alienFour.x -= 50;
    //     this.movement = true;
    // }