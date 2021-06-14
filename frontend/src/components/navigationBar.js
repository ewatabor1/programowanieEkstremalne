import React, { useState } from "react";
import Navbar from "@material-tailwind/react/Navbar";
import NavbarContainer from "@material-tailwind/react/NavbarContainer";
import NavbarWrapper from "@material-tailwind/react/NavbarWrapper";
import NavbarBrand from "@material-tailwind/react/NavbarBrand";
import NavbarToggler from "@material-tailwind/react/NavbarToggler";
import NavbarCollapse from "@material-tailwind/react/NavbarCollapse";
import Nav from "@material-tailwind/react/Nav";
import NavLink from "@material-tailwind/react/NavLink";
import "./navigationBar.css";
import H5 from "@material-tailwind/react/Heading5";
const NavigationBar = ({ navigation }) => {
  const [openNavbar, setOpenNavbar] = useState(false);
  const data = navigation;
  return (
    <Navbar color="indigo" navbar>
      <NavbarContainer>
        <NavbarWrapper>
          <NavbarBrand><H5 color='white'>Fridge Manager</H5></NavbarBrand>
          <NavbarToggler
            color="white"
            onClick={() => setOpenNavbar(!openNavbar)}
            ripple="light"
          />
        </NavbarWrapper>

        <NavbarCollapse open={openNavbar}>
          <Nav>
            {data.map((nav, key) => {
              return (
                <NavLink data-testid={nav.name} href={nav.path} key={key} ripple='light'>
                  <H5 color='white'>{nav.name}</H5>
                </NavLink>
              );
            })}
          </Nav>
        </NavbarCollapse>
      </NavbarContainer>
    </Navbar>
  );
};

export default NavigationBar;
