//material ui and icon
import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';

//stye for material
const useStyles = makeStyles({
  card: {
    minWidth: 275,
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
});

export default function NoteTwo() {
  const classes = useStyles();
  //for bullet point
  const bull = <span className={classes.bullet}>•</span>;

  return (
    //material io/icon designed for this part.
    <Card className={classes.card}>
      <CardContent>
        <Typography className={classes.title} color="textSecondary" gutterBottom>
          Game - Developing
        </Typography>
        <Typography variant="h5" component="h2">
          phaser.io
        </Typography>
        <Typography className={classes.pos} color="textSecondary">
          Angelica G Flores {bull} Aleks Trifonova {bull} Alexis Siguenza
        </Typography>
        <Typography variant="body2" component="p">
          Pac-Man - infinite version, written in Phaser 2 and javascript<br />
          Snake - classic version, written in html and javascript <br />
          Space - modern twist version, written in Phaser 3 and javascript<br />

          <br />
        </Typography>
      </CardContent>
    </Card>
  );
}