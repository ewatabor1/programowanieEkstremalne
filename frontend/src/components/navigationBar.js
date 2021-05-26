import React from "react";
import { Nav, Navbar, Container } from "react-bootstrap";
import { NavLink } from "react-router-dom";
import "./navigationBar.css";
const NavigationBar = ({ navigation }) => {
  const data = navigation;
  console.log(data);
  return (
    // <Navbar bg="primary" variant="dark">
    //     <Navbar.Toggle aria-controls="responsive-navbar-nav" />
    //     <Navbar.Collapse id="responsive-navbar-nav">
    //       <Nav>
    //       {data.map((nav, key) =>{
    //         return(
    //         <Nav.Link data-testid = {nav.name} href={nav.path}>{nav.name}</Nav.Link>
    //       )})}
    //       </Nav>
    //     </Navbar.Collapse>
    // </Navbar>
    <ul className="nav-links">
      {data.map((nav, key) => {
        return (
          <Nav.Link data-testid={nav.name} href={nav.path}>
            <h3>{nav.name}</h3>
          </Nav.Link>
        );
      })}
    </ul>
  );
};

export default NavigationBar;
