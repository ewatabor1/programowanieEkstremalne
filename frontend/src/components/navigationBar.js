import React from "react";
import { Nav} from "react-bootstrap";
import "./navigationBar.css";
const NavigationBar = ({ navigation }) => {
  const data = navigation;
  console.log(data);
  return (
    <ul className="nav-links">
      {data.map((nav, key) => {
        return (
          <Nav.Link data-testid={nav.name} href={nav.path} key={key}>
            <h3>{nav.name}</h3>
          </Nav.Link>
        );
      })}
    </ul>
  );
};

export default NavigationBar;
