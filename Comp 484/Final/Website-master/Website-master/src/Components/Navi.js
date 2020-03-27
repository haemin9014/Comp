import React, { Component } from 'react'
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";

import Game from './Game';
import Profile from './Profile';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';



export class Navi extends Component {
    //navigation
  render() {
        const {classes} = this.props;
      
  return (
    <div className ="sidebar">
      <AppBar position="static">
        <Toolbar id>
          {/* menu for navigation */}
        <Typography variant="h3">
             Gameboard
        </Typography>
          <IconButton component={Link} to= "/" edge="start"  color="inherit" aria-label="menu" id = "button">
              Home
          </IconButton>
          <IconButton component={Link} to= "/game" edge="start"  color="inherit" aria-label="menu" >
              Game
          </IconButton>
          <IconButton component={Link} to= "/profile" edge="start"  color="inherit" aria-label="menu">
              Profile
          </IconButton>
        </Toolbar>
      </AppBar>
    <Switch>
      {/* switch connection */}
        <Route path="/home" component={Navi}/>
        <Route path="/" />
        <Route path="/game" component={Game}/>
        <Route path="/profile" component={Profile}/>
    </Switch>
    </div>
  );
    }
}

export default (Navi);