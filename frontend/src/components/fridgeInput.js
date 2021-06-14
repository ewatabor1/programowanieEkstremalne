import React, { useState } from "react";
import "./fridgeOptions.css";
import { PostData } from "./hooks/fetchData";
import Card from "@material-tailwind/react/Card";
import CardHeader from "@material-tailwind/react/CardHeader";
import CardBody from "@material-tailwind/react/CardBody";
import CardFooter from "@material-tailwind/react/CardFooter";
import InputIcon from "@material-tailwind/react/InputIcon";
import Button from "@material-tailwind/react/Button";
import H5 from "@material-tailwind/react/Heading5";
const FridgeInput = ({ updateState }) => {
  const [productName, setProductName] = useState("");
  const [productValue, setProductValue] = useState("");
  const [productExpire, setProductExpire] = useState("");
  const [productQuantity, setProductQuantity] = useState("");
  const [minimalQuantity, setMinimalQuantity] = useState(0);
  const handleChange = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    if (nam === "productName") {
      setProductName(val);
    } else {
      setProductExpire(val);
    }
  };
  const handleChange2 = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    if (nam === "productValue") {
      const value = parseInt(val, 10);
      setProductValue(value);
    } else if (nam === "productQuantity") {
      const value = parseInt(val, 10);
      setProductQuantity(value);
    } else {
      const value = parseInt(val, 10);
      setMinimalQuantity(value);
    }
  };
  const handleSubmit = () => {
    const json = JSON.stringify({
      name: productName,
      kcal: productValue,
      expiryDate: productExpire,
      quantity: productQuantity,
      minQuantity: minimalQuantity,
    });
    PostData("/api/products", json);
    setTimeout(updateState(Math.random() * 10), 1);
  };

  return (
    <div className="fridge-input-container">
      <Card>
        <CardHeader color="indigo" size="normal">
          <H5 color="white">Dodaj produkt do lodówki</H5>
        </CardHeader>

        <CardBody>
          <div className="mt-4 mb-8 px-4">
            <InputIcon
              type="text"
              color="indigo"
              placeholder="Nazwa produktu"
              data-testid="productName-input"
              name="productName"
              onChange={handleChange}
            />
          </div>
          <div className="mb-8 px-4">
            <InputIcon
              type="text"
              color="indigo"
              placeholder="Ilość produktu"
              name="productQuantity"
              onChange={handleChange2}
            />
          </div>
          <div className="mb-8 px-4">
            <InputIcon
              type="text"
              color="indigo"
              placeholder="Minimalna ilość"
              name="minimalQuantity"
              onChange={handleChange2}
            />
          </div>
          <div className="mb-8 px-4">
            <InputIcon
              type="text"
              color="indigo"
              placeholder="Data ważności"
              name="productExpire"
              onChange={handleChange}
            />
          </div>
          <div className="mb-8 px-4">
            <InputIcon
              type="text"
              color="indigo"
              placeholder="Wartość energetyczna"
              name="productValue"
              onChange={handleChange2}
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
              Dodaj produkt
            </Button>
          </div>
        </CardFooter>
      </Card>
    </div>
  );
};

export default FridgeInput;
