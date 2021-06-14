import React from "react";
import Card from "@material-tailwind/react/Card";
import CardHeader from "@material-tailwind/react/CardHeader";
import CardBody from "@material-tailwind/react/CardBody";
import CardFooter from "@material-tailwind/react/CardFooter";
import InputIcon from "@material-tailwind/react/InputIcon";
import Button from "@material-tailwind/react/Button";
import H5 from "@material-tailwind/react/Heading5";

const ReceiptInput = ({ handleSubmit, handleAddedRecipe, myChangeHandler }) => {
  return (
    <div className="Receipt-addingProduct">
      <Card>
        <CardHeader color="indigo" size="sm">
          <H5 color="white">Dodaj produkt do przepisu</H5>
        </CardHeader>

        <CardBody>
          <div className="mb-8 px-4">
            <InputIcon
              type="text"
              color="indigo"
              placeholder="Ilość produktu"
              name="productQuantity"
              onChange={myChangeHandler}
            />
          </div>
        </CardBody>
        <CardFooter>
          <div className="flex justify-center">
            <Button
              color="indigo"
              buttonType="outline"
              size="regular"
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

export default ReceiptInput;
