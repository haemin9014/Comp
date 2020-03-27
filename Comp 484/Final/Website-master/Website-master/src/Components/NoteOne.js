import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';

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

export default function NoteOne() {
  const classes = useStyles();
  const bull = <span className={classes.bullet}>•</span>;

  return (
    <Card className={classes.card}>
      <CardContent className = "move">
        <Typography className={classes.title} color="textSecondary" gutterBottom>
          Web - Developing
        </Typography>
        <Typography variant="h5" component="h2">
          react
          {bull}
          material-ui
        </Typography>
        <Typography className={classes.pos} color="textSecondary">
          Haemin Lee {bull} Ian Iskra
        </Typography>
        <Typography variant="body2" component="p">
         navi, home, and game component -use react.js bootstrap, material ui and io <br />
         profile component - use react.js
          <br />
        </Typography>
      </CardContent>
    </Card>
  );
}