import React, { useState } from "react";
import "./listInput.js";
import { PostData } from "../components/hooks/fetchData";
import Card from "@material-tailwind/react/Card";
import CardHeader from "@material-tailwind/react/CardHeader";
import CardBody from "@material-tailwind/react/CardBody";
import CardFooter from "@material-tailwind/react/CardFooter";
import InputIcon from "@material-tailwind/react/InputIcon";
import Button from "@material-tailwind/react/Button";
import H5 from "@material-tailwind/react/Heading5";
const ListInput = ({ handleStateUpdated, handleRemove }) => {
  const [productValue, setProductValue] = useState("");
  const [productName, setProductName] = useState("");
  const [listName, setListName] = useState("");

  const handleChange = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    if (nam === "productValue") {
      setProductValue(val);
    } else if (nam === "productName") {
      setProductName(val);
    } else {
      setListName(val);
    }
  };

  const handleSubmit = () => {
    const json = JSON.stringify({
      name: listName,
      products: [{ product: productName, quantity: productValue }],
    });
    PostData("/api/grocery-lists", json);
    handleStateUpdated(Math.random());
  };
  return (
    <div className="list-firstpart">
      <div className="list-card">
        <Card>
          <CardHeader color="indigo" size="lg">
            <H5 color="white">Dodaj produkt do listy</H5>
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
                Dodaj produkt
              </Button>
            </div>
          </CardFooter>
        </Card>
        <div className="product-button">
          <Button
            color="indigo"
            buttonType="filled"
            size="lg"
            rounded={false}
            block={false}
            iconOnly={false}
            ripple="light"
            onClick={handleRemove}
          >
            Usuń produkt
          </Button>
        </div>
      </div>
    </div>
    //   <div className="list-firstpart">
    //   <form className="form-listitem" data-testid='form'>
    //     <p>Nazwa produktu:</p>
    //     <input type="text" name="productName" data-testid="productName-input" onChange={handleChange} />
    //     <p>Ilość produktu:</p>
    //     <input type="text" name="productValue" onChange={handleChange} />
    //   </form>
    //   <button onClick={handleSubmit}>Dodaj produkt</button>
    // </div>
  );
};

export default ListInput;
