var assetsDir = '../pacman/assets';
export default class Pacman {
    constructor() {
        this.speed = 150;
        this.threshold = 3;

        this.marker = new Phaser.Point();
        this.turnPoint = new Phaser.Point();

        this.directions = [ null, null, null, null, null ];
        this.opposites = [ Phaser.NONE, Phaser.RIGHT, Phaser.LEFT, Phaser.DOWN, Phaser.UP ];

        this.currentDirection = Phaser.NONE;
        this.turnDirection = Phaser.NONE;
    }

    preload = (game) => {
        game.load.spritesheet('pacman', assetsDir + '/sprites/pacman/pacman.png', 32, 32);
    }

    doArrowKeys = (game) => {
        if (game.cursors.left.isDown && this.currentDirection !== Phaser.LEFT) {
            this.checkDirection(Phaser.LEFT, game);
        }
        else if (game.cursors.right.isDown && this.currentDirection !== Phaser.RIGHT) {
            this.checkDirection(Phaser.RIGHT, game);
        }
        else if (game.cursors.up.isDown && this.currentDirection !== Phaser.UP) {
            this.checkDirection(Phaser.UP, game);
        }
        else if (game.cursors.down.isDown && this.currentDirection !== Phaser.DOWN) {
            this.checkDirection(Phaser.DOWN, game);
        }
        else {
            this.turnDirection = Phaser.NONE;
        }
    }

    checkDirection = (toDirection, game) => {

        if (this.turnDirection === toDirection // already going in direction
            || this.directions[toDirection] === null
            || this.directions[toDirection].index !== game.safetile) { // hit wall
            return;
        }
        if (this.currentDirection === this.opposites[toDirection]) { // turn back
            this.move(toDirection);
        }
        else {
            this.turnDirection = toDirection;
            this.turnPoint.x = (this.marker.x * game.gridsize) + (game.gridsize / 2);
            this.turnPoint.y = (this.marker.y * game.gridsize) + (game.gridsize / 2);
        }
    }

    turn = (game) => {
        var changeX = Math.floor(this.sprite.x);
        var changeY = Math.floor(this.sprite.y);

        // Pacman constantly moving -> hard to hit exact coordinates, give some leeway
        if (!game.math.fuzzyEqual(changeX, this.turnPoint.x, this.threshold) 
            || !game.math.fuzzyEqual(changeY, this.turnPoint.y, this.threshold)) {
            return false;
        }

        this.sprite.x = this.turnPoint.x;
        this.sprite.y = this.turnPoint.y;

        this.sprite.body.reset(this.turnPoint.x, this.turnPoint.y);
        this.move(this.turnDirection);
        this.turnDirection = Phaser.NONE;

        return true;

    }

    move = (direction) => {
        var speed = this.speed;

        if (direction === Phaser.LEFT || direction === Phaser.UP) { // go backwards (right, down = defaults)
            speed = -speed;
        }
        if (direction === Phaser.LEFT || direction === Phaser.RIGHT) {
            this.sprite.body.velocity.x = speed;
        }
        else {
            this.sprite.body.velocity.y = speed;
        }

        this.sprite.scale.x = 1;
        this.sprite.angle = 0;

        if (direction === Phaser.LEFT) {
            this.sprite.scale.x = -1;   // flip X
        }
        else if (direction === Phaser.UP) {
            this.sprite.angle = 270;    // rotate up
        }
        else if (direction === Phaser.DOWN) {
            this.sprite.angle = 90;     // rotate down
        }

        this.currentDirection = direction;
    }

    create = (game) => {
    this.sprite = game.add.sprite((14 * 16) + 8, (17 * 16) + 8, 'pacman', 0);
    this.sprite.anchor.set(0.5);
    this.sprite.animations.add('munch', [0, 1, 2, 1], 20, true);

    game.physics.arcade.enable(this.sprite);
    this.sprite.body.setSize(16, 16, 0, 0);

    this.sprite.play('munch');
    this.move(Phaser.LEFT);

    }

    updateTileDirections = (game) => {
        this.marker.x = game.math.snapToFloor(Math.floor(this.sprite.x), game.gridsize) / game.gridsize;
        this.marker.y = game.math.snapToFloor(Math.floor(this.sprite.y), game.gridsize) / game.gridsize;

        this.directions[1] = game.map.getTileLeft(game.layer.index, this.marker.x, this.marker.y);
        this.directions[2] = game.map.getTileRight(game.layer.index, this.marker.x, this.marker.y);
        this.directions[3] = game.map.getTileAbove(game.layer.index, this.marker.x, this.marker.y);
        this.directions[4] = game.map.getTileBelow(game.layer.index, this.marker.x, this.marker.y);
    }

    updateMovement = (game) => {
        this.doArrowKeys(game);

        if (this.turnDirection !== Phaser.NONE) {
            this.turn(game);
        }
    }

    update = (game) => {
        // Update movement
        this.updateTileDirections(game);
        this.updateMovement(game);
    }
}