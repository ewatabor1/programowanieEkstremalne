import './App.css';
import React, {useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Checkbox from '@material-ui/core/Checkbox';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
  }
}));


const initialState = ['Banan','Truskawka','Ciastko','Czekolada']

function App() {
  const classes = useStyles();
  const [checked, setChecked] = React.useState([0]);
  const [Data, setData] = useState(initialState)
  const [Value, setValue] = useState("")
  const handleToggle = (value) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    setChecked(newChecked);
  };

  const handleChange = e =>{
    setValue(e.target.value)
  }

  const handleSubmit = () =>{
    setData(Data =>Data.concat(Value))
    setValue("")
  }

  const handleRemove = (value) => {
    const newArray = Data.filter((item) => item !== value)
    setData(newArray)
  }
  return (
    <div className='App-header'>
    <div className='App-firstpart'>
    <TextField id="custom-css-outlined-input" label="Produkt" variant="outlined" onChange={handleChange} value={Value}/>
    <Button
        variant="contained"
        color="primary" 
        onClick={handleSubmit}
      >
        Add product
      </Button>
    </div>
    <List className={classes.root}>
      {Data.map((value) => {
        const labelId = `checkbox-list-label-${value}`;

        return (
          <ListItem key={value} role={undefined} dense button onClick={handleToggle(value)}>
            <ListItemIcon>
              <Checkbox
                edge="start"
                checked={checked.indexOf(value) !== -1}
                tabIndex={-1}
                disableRipple
                inputProps={{ 'aria-labelledby': labelId }}
              />
            </ListItemIcon>
            <ListItemText id={labelId} primary={value} />
            <ListItemText id={labelId} primary={value.value} />
            <ListItemSecondaryAction>
              <IconButton edge="end" aria-label="comments" onClick={()=>handleRemove(value)}>
                <DeleteIcon />
              </IconButton>
            </ListItemSecondaryAction>
          </ListItem>
        );
      })}
    </List>
    </div>
  );
}

export default App;
