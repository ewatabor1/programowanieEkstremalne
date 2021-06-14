import "./App.css";
import React from "react";
import NavigationBar from "./components/navigationBar";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Home from "./sites/Home";
import ReceiptScreen from "./sites/ReceiptScreen";
import Fridge from "./sites/FridgeScreen";
import "@material-tailwind/react/tailwind.css";
const navigation = [
  { path: "/", name: "Home" },
  { path: "/receipt", name: "Recipes" },
  { path: "/fridge", name: "Fridge" },
];
const App = () => {
  return (
    <React.Fragment>
      <Router>
        <NavigationBar navigation={navigation} />
        <Switch>
          <Route exact path="/" component={Home} />
          <Route path="/receipt" component={ReceiptScreen} />
          <Route path="/fridge" component={Fridge} />
          <Route component={Home} />
        </Switch>
      </Router>
    </React.Fragment>
  );
};

export default App;
