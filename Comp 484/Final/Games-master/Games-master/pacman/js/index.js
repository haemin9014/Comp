
var assetsDir = '../pacman/assets';
import Ghost from "./sprites/Ghost.js";
import Pacman from "./sprites/Pacman.js";

var game = new Phaser.Game(448, 496, Phaser.AUTO);

var Game = function (game) {

    this.map = null;
    this.layer = null;
    this.tileset = null;

    this.pacman = null;

    this.ghosts = null;

    this.safetile = 14;
    this.gridsize = 16;


    this.superEaten = false;
    this.superCount = 0;
    this.superMax = 60 * 15;
    this.score = 0;
    this.time = 0;

};

Game.prototype = {

    init: function () {

        this.scale.scaleMode = Phaser.ScaleManager.SHOW_ALL;
        this.scale.pageAlignHorizontally = true;
        this.scale.pageAlignVertically = true;

        // Phaser.Canvas.setImageRenderingCrisp(this.game.canvas);

        this.physics.startSystem(Phaser.Physics.ARCADE);
    },

    preload: function () {

        // Dot assets
        this.load.image('dot', assetsDir + '/objects/dot.png');
        this.load.image('super-dot', assetsDir + '/objects/superdot.png');

        // Tile/map assets
        this.load.image('tiles', assetsDir + '/pacman-tiles.png');
        this.load.tilemap('map', assetsDir + '/pacman-map.json', null, Phaser.Tilemap.TILED_JSON);

        // Sound assets
        this.load.audio('waka', assetsDir + '/sounds/waka.mp3');

        // Preload pacman assets
        this.pacman = new Pacman();
        this.pacman.preload(this);

        // Preload ghost assets
        this.inky = new Ghost('inky', {x: (13 * 16) + 8, y: (11 * 16) + 8});
        this.inky.preload(this);

        this.blinky = new Ghost('blinky', {x: 40, y: 40});
        this.blinky.preload(this);

        this.clyde = new Ghost('clyde', {x: 294, y: 224});
        this.clyde.preload(this);

        this.pinky = new Ghost('pinky', {x: 340, y: 280});
        this.pinky.preload(this);

    },

    create: function () {

        this.map = this.add.tilemap('map');
        this.tileset = this.map.addTilesetImage('pacman-tiles', 'tiles');

        this.layer = this.map.createLayer('Pacman');

        this.setupDots();
        this.setupPacman();
        this.setupGhosts();

        // Sprites should collide with everything except the safe tiles/dots
        this.map.setCollisionByExclusion([this.safetile, 7, 35], true, this.layer);

        this.cursors = this.input.keyboard.createCursorKeys();
        this.scoreText = this.add.text(5, 0, 'Score: ' + this.score, {font: '10pt monospace', fill: 'white'});
        // this.timeText = this.add.text(300, 0, 'Time: ' + this.time, {font: '10pt monospace',  fill: 'white'});
    },

    setupPacman: function () {
       this.pacman.create(this);
    },

    setupDots: function () {
        this.dots = this.add.physicsGroup();
        this.superDots = this.add.physicsGroup();

        this.map.createFromTiles(7, this.safetile, 'dot', this.layer, this.dots);
        this.map.createFromTiles(35, this.safetile, 'super-dot', this.layer, this.superDots);

        this.dots.setAll('x', 6, false, false, 1);
        this.dots.setAll('y', 6, false, false, 1);
    },

    setupGhosts: function () {
        this.inky.create(this);
        this.blinky.create(this);
        this.clyde.create(this);
        this.pinky.create(this);

        var ghostTurns = this;

        this.ghostTurnsWait = setInterval(function() {
              ghostTurns.inky.turnDirection = "NONE";
              ghostTurns.blinky.turnDirection = "NONE";
              ghostTurns.clyde.turnDirection = "NONE";
              ghostTurns.pinky.turnDirection = "NONE";
        }, 5000);

        this.ghosts = this.add.physicsGroup();
        this.ghosts.add(this.inky.sprite);
        this.ghosts.add(this.blinky.sprite);
        this.ghosts.add(this.clyde.sprite);
        this.ghosts.add(this.pinky.sprite);
    },

    eatDot: function (pacman, dot) {
        dot.kill();
        this.score++;
        if (this.dots.total === 0) {
            this.dots.callAll('revive');
        }
    },
    
    eatSuperDot:  function (pacman, superDot) {
        superDot.kill();
        if (this.superDots.total === 0) {
            this.superDots.callAll('revive');
        }

        this.superEaten = true;
        this.score += 5;

        this.inky.setAfraid();
        this.blinky.setAfraid();
        this.clyde.setAfraid();
        this.pinky.setAfraid();

        this.pacman.move(this.pacman.current); // TODO: Pacman-ghost pushback
    },

    killPacman: function(pacman, ghost) {
        pacman.kill();
        this.add.text(100, 496 / 2, "You died!\nBetter luck next time.", {fill: 'red', font: '15pt monospace', backgroundColor: 'black'});
    },

    killGhost: function(pacman, ghost) {
        ghost.kill();
        this.score += 10;
    },

  stopGhost: function(ghost, wall) {
        if (this.inky.sprite == ghost) {
              this.inky.turnDirection = "NONE";
        } else if (this.blinky.sprite == ghost) {
              this.blinky.turnDirection = "NONE";
        } else if (this.clyde.sprite == ghost) {
              this.clyde.turnDirection = "NONE";
        } else if (this.pinky.sprite == ghost) {
              this.pinky.turnDirection = "NONE";
        }
  },

    update: function () {

        this.scoreText.setText("Score: " + this.score);

        // Dot behavior
        if (this.superEaten && ++this.superCount == this.superMax) {
            this.superEaten = false;
            this.superCount = 0;

            this.inky.setNormal();
            this.blinky.setNormal();
            this.clyde.setNormal();
            this.pinky.setNormal();
        } 
        
        // Pacman collision behavior
        
         // Run into and eat dots
         this.physics.arcade.overlap(this.pacman.sprite, this.dots, this.eatDot, null, this);
         this.physics.arcade.overlap(this.pacman.sprite, this.superDots, this.eatSuperDot, null, this);

        if (!this.superEaten)
            this.physics.arcade.collide(this.pacman.sprite, this.ghosts, this.killPacman, null, this);
        else
            this.physics.arcade.overlap(this.pacman.sprite, this.ghosts, this.killGhost, null, this);

        this.physics.arcade.overlap(this.pacman.sprite, this.dots, this.eatDot, null, this);
        this.physics.arcade.collide(this.pacman.sprite, this.layer);
        
        // Ghost collision behavior
        this.physics.arcade.collide(this.ghosts, this.layer, this.stopGhost, null, this);
        this.physics.arcade.collide(this.ghosts, this.ghosts, this.stopGhost, null, this);
        
        // Update sprite movements
        this.inky.update(this);
        this.blinky.update(this);
        this.clyde.update(this);
        this.pinky.update(this);

        this.pacman.update(this);
    }

};

game.state.add('Game', Game, true);