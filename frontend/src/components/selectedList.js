import React from "react";
import Button from "@material-tailwind/react/Button";
import H6 from "@material-tailwind/react/Heading6";
import Input from "@material-tailwind/react/Input";
const SelectedList = ({
  productName,
  products,
  handleListClicked,
  handleAddedRecipe,
  myChangeHandler
}) => {
  return (
    <div className="Receipt-actualproducts">
      {console.log(products)}
      <H6>{"Nazwa przepisu " + productName}</H6>
      <div className="receipt-title">
        <Input
          type="text"
          color="indigo"
          size="regular"
          outline={true}
          placeholder="Input"
          name='ProductName'
          onChange={myChangeHandler}
        />
        <Button
          color="indigo"
          buttonType="filled"
          size="regular"
          rounded={false}
          block={false}
          iconOnly={false}
          ripple="light"
          onClick={handleAddedRecipe}
        >
          Dodaj przepis
        </Button>
      </div>
      <H6>Wybrane produkty:</H6>
      {products.map((value,key) => {
        console.log(value);
        return (
          <li key={key} className="fridge-list">
            <Button
              color="indigo"
              buttonType="filled"
              size="lg"
              rounded={false}
              block={true}
              iconOnly={false}
              ripple="light"
              className="list-inside-button"
              data-testid={value.value}
              onClick={() => handleListClicked(value.id)}
            >
              <div>
                <p>{"Produkt: " + value.name}</p>
                <p>{"ilość: " + value.amount}</p>
                <p>{"Kcal: " + value.kcal * value.amount}</p>
              </div>
            </Button>
          </li>
        );
      })}
    </div>
  );
};

export default SelectedList;
