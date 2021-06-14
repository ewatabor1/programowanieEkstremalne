import React from "react";
import "./fridgeOptions.css";
import { DeleteData } from "./hooks/fetchData";
import Card from "@material-tailwind/react/Card";
import CardHeader from "@material-tailwind/react/CardHeader";
import CardBody from "@material-tailwind/react/CardBody";
import CardFooter from "@material-tailwind/react/CardFooter";
import InputIcon from "@material-tailwind/react/InputIcon";
import Button from "@material-tailwind/react/Button";
import H5 from "@material-tailwind/react/Heading5";

const FridgeDelete = ({ updateState, testRemove }) => {
  const handleSubmit = () => {
    DeleteData(`http://localhost:8080/api/products/${testRemove}`);
    setTimeout(updateState(Math.random() * 10), 1);
  };
  return (
      <Card>
        <CardHeader color="indigo" size="regular">
          <H5 color="white">Usuń produkt</H5>
        </CardHeader>
        <CardFooter>
          <div className="flex justify-center">
            <Button
              color="indigo"
              buttonType="outline"
              size="lg"
              ripple="dark"
              rounded={false}
              block={false}
              iconOnly={false}
              onClick={handleSubmit}
            >
              Usuń produkt
            </Button>
          </div>
        </CardFooter>
      </Card>
  );
};

export default FridgeDelete;
