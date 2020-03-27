import React, { Component } from 'react'
import Button from 'react-bootstrap/Button'
import 'bootstrap/dist/css/bootstrap.min.css';

export class Game extends Component {
    render() {
        return (
            <div>
                <h1 class="game_title">pacman</h1>
                <div id ="pacman">
                    {/* image from glitch */}
                    <img src="https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Fpac.png?v=1576716625453"
                    alt="new" class ="game_image"/>
                    
                    <aside class="text_place">
                    <p>Released in 1980 by Namco, Pac-Man is a classic arcade game where the iconic character
                            must devour as many dots as he can while avoiding four colorful ghosts as he navigates
                            the maze. Written in Phaser 2, this JavaScript implementation allows the player to eat
                            as many dots as you can - see how high a score you can get before the ghosts catch you!</p>
                    </aside>
                </div>

                <div class="game_btn">
                {/* target="_blank" href={''} */}
                {/* button designed by bootstrap */}
                <Button variant="primary" size="lg" block target="_blank" href={'/game/pacman'}>
                    Game start
                </Button>
                </div>
                <h1 class="game_title">snake</h1>
                <div id ="snake">
                    <img  src="https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Fsnake.png?v=1576725395513"
                    alt="new" class ="game_image" />

                    <aside class="text_place">
                    <p>Snake was first introduced in 1997 by Nokia is a classic phone game and has since been
                            developed and enjoyed by young and old people alike and is a fun, simple game. 
                            Developed with html and css this implementation allows you to move around the map to
                            eat as many pieces of food as you can!</p>
                    </aside>
                
                </div>

                <div class="game_btn">
                <Button variant="primary" size="lg" block target="_blank" href={'/game/snake'}>
                    Game start
                </Button>
                </div>
                <h1 class="game_title">space invaders</h1>
                <div id ="space">
                    <img src="https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2FSpace-invaders-game-preview..png?v=1576713911799"
                    alt="new" class ="game_image"/>

                    <aside class="text_place">
                        <p>The Original Space invaders is a Japanese game created in 1978 by (C) ATARI.
                         It's now available online made with Phaser 3!</p>
                    </aside>

                </div>

                <div class="game_btn">
                {/* target="_blank" href={''} */}
                <Button variant="primary" size="lg" block target="_blank" href={'/game/space-invaders'}>
                    Game start
                </Button>
                </div>
                
            </div>
        )
    }
}

export default Game
