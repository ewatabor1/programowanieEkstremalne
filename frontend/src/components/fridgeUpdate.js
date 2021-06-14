import React, { useState } from "react";
import "./fridgeOptions.css";
import { PutData } from "./hooks/fetchData";
import Card from "@material-tailwind/react/Card";
import CardHeader from "@material-tailwind/react/CardHeader";
import CardBody from "@material-tailwind/react/CardBody";
import CardFooter from "@material-tailwind/react/CardFooter";
import InputIcon from "@material-tailwind/react/InputIcon";
import Button from "@material-tailwind/react/Button";
import H5 from "@material-tailwind/react/Heading5";

const FridgeUpdate = ({ valueToRemove, updateState }) => {
  const testValue = valueToRemove;
  const [productValue, setProductValue] = useState("");
  const handleChange = (event) => {
    let val = event.target.value;
    let value = parseInt(val, 10);
    setProductValue(value);
  };
  const handleSubmit = () => {
    const actualValue = productValue;
    if (actualValue > 0) {
      PutData(`/api/products/supply/${testValue}/${productValue}`);
      updateState(Math.random() * 10);
    } else {
      const value = Math.abs(productValue);
      PutData(`/api/products/consume/${testValue}/${value}`);
      updateState(Math.random() * 10);
    }
  };
  return (
      <Card>
        <CardHeader color="indigo" size="sm">
          <H5 color="white">Aktualizowanie produktu</H5>
        </CardHeader>

        <CardBody>
          <div className="mt-4 mb-8 px-4">
            <InputIcon
              type="text"
              color="indigo"
              placeholder="Ilość produktu"
              data-testid="productValue-input"
              name="productValue"
              onChange={handleChange}
            />
          </div>
        </CardBody>
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
              Uaktualnij produkt
            </Button>
          </div>
        </CardFooter>
      </Card>
  );
};

export default FridgeUpdate;
