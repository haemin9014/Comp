var assetsDir = '../pacman/assets';
export default class Ghost {
	// color = [blue, orange, pink, red]
	constructor(name, position) {

		this.spritesheets = {
			afraid: {name: name + '_' + 'afraid', path: assetsDir + '/sprites/ghost/ghost_afraid.png'},
			normal: {name: name + '_' + 'normal', path: assetsDir + '/sprites/ghost/' + name + '.png'},
		};

		this.width = 16;
		this.height = 16;
		this.sprite = null;
		
		this.directions = {up: 'UP', down: 'DOWN', right: 'RIGHT', left: 'LEFT', none: 'NONE'};
        this.turnDirection = this.directions.none;

        this.marker = {x: position.x, y: position.y};
        this.adjacentTiles = {};
        this.turnPoint = {};
    
		this.velocity = 100;
    }
    
    resetVelocity = () => {
        this.sprite.body.velocity.x = 0;
        this.sprite.body.velocity.y = 0;
    }

    setVelocity = (velocity) => {
        this.velocity = velocity;
    }

    getVelocity = () => {
        return this.velocity;
    }

    getSprite = () => {
    return this.sprite;
    }

    preload = (game) => {
        game.load.spritesheet(this.spritesheets.afraid.name, this.spritesheets.afraid.path, 32, 32);
        game.load.image(this.spritesheets.normal.name, this.spritesheets.normal.path, this.width, this.height);
    }

    // Superdot not eaten
    setNormal = () => {
        this.setVelocity(100);
        this.sprite.loadTexture(this.spritesheets.normal.name, 0);
        if (!this.sprite.alive) {
            this.sprite.x = (13 * 16) + 8;
            this.sprite.y = (14 * 16) + 8;
        }
        this.sprite.revive();
    }

    // Superdot eaten
    setAfraid = () => {
        this.setVelocity(200);
        this.sprite.loadTexture(this.spritesheets.afraid.name, 0);
        this.sprite.animations.add(this.spritesheets.afraid.name, null, 2, true);
        this.sprite.play(this.spritesheets.afraid.name);
    }

    create = (game) => {
        this.sprite = game.add.sprite(this.marker.x, this.marker.y, this.spritesheets.normal.name, 0);
        this.sprite.anchor.set(0.5);
        this.sprite.angle = 0;
        game.physics.arcade.enable(this.sprite);
        this.sprite.body.setSize(10, 10, 0, 0);
    }

    move = (direction) => {
        switch (this.turnDirection) {
            case this.directions.up:
                this.resetVelocity();
                this.sprite.body.velocity.y = -this.velocity;
                break;
            case this.directions.right:
                this.resetVelocity();
                // Flip ghost
                if (this.sprite.scale.x < 0) {
                    this.sprite.scale.x *= -1;
                }
                this.sprite.body.velocity.x = this.velocity;
                break;
            case this.directions.down:
                this.resetVelocity();
                this.sprite.body.velocity.y = this.velocity;
                break;
            case this.directions.left:
                this.resetVelocity();
                // Flip ghost
                if (this.sprite.scale.x > 0) {
                    this.sprite.scale.x *= -1;
                }
                this.sprite.body.velocity.x = -this.velocity;
                break;
        }
    }

    checkDirection = (direction, game) => {
        if (this.turnDirection == direction) {
            return;
        } else if (this.adjacentTiles[direction] == null) {
            return;
        } else if (this.adjacentTiles[direction].collides) {
            return;
        }

        // Is ghost inside entrance box? Then only go up.
        if (this.adjacentTiles[this.directions.up].x == 13 && this.adjacentTiles[this.directions.up].y == 14) {
            this.turningNow = this.directions.up;
        }

        this.turningNow = direction;

        this.turnPoint.x = (this.marker.x * this.width) + (this.width / 2);
        this.turnPoint.y = (this.marker.y * this.height) + (this.height / 2);

        if (game.math.fuzzyEqual(this.sprite.x, this.turnPoint.x, 6) ||
            game.math.fuzzyEqual(this.sprite.y, this.turnPoint.y, 6)) {

            this.turnDirection = direction;

            this.sprite.x = this.turnPoint.x;
            this.sprite.y = this.turnPoint.y;

            this.sprite.body.reset(this.turnPoint.x, this.turnPoint.y);

            this.move(this.turning);

            this.turningNow = this.directions.none;
        }    
    }


    // Get tiles nearby ghost in all directions
    findAdjacentTiles = (game) => {
        // Reposition current coords to whole number (tile grid)
        this.marker.x = game.math.snapToFloor(Math.floor(this.sprite.x), this.width) / this.height;
        this.marker.y = game.math.snapToFloor(Math.floor(this.sprite.y), this.width) / this.height;

        var i = game.layer.index;
        var x = this.marker.x;
        var y = this.marker.y;

        this.adjacentTiles[this.directions.left] = game.map.getTileLeft(i, x, y);
        this.adjacentTiles[this.directions.right] = game.map.getTileRight(i, x, y);
        this.adjacentTiles[this.directions.up] = game.map.getTileAbove(i, x, y);
        this.adjacentTiles[this.directions.down] = game.map.getTileBelow(i, x, y);
    }  

    // Use adjacent tiles to determine possible directions ghost can go (not a wall)
    findIntersection = () => {
        let intersection = [];
    
        if (!this.adjacentTiles[this.directions.left].collides) {
            intersection.push(this.directions.left);
        }
        if (!this.adjacentTiles[this.directions.right].collides) {
            intersection.push(this.directions.right);
        }
        if (!this.adjacentTiles[this.directions.up].collides) {
            intersection.push(this.directions.up);
        }
        if (!this.adjacentTiles[this.directions.down].collides) {
            intersection.push(this.directions.down);
        }
    
        return intersection;
    }

    update = (game) => {
        this.findAdjacentTiles(game);
    
        // If ghost is about to turn, choose random pathway to move into
        if (this.turnDirection == this.directions.none) {
            let intersection = this.findIntersection();
    
            if (intersection.length > 0) {
                let randomDirection = Math.floor(Math.random() * intersection.length);
                let turnDirection = intersection[randomDirection];
    
                this.checkDirection(turnDirection, game);
            }
        }
    }
}
