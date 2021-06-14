import React from "react";
import Button from "@material-tailwind/react/Button";
import '../sites/Fridge.css'
import H3 from "@material-tailwind/react/Heading3";
const FridgeList = ({ data, handleListClicked, type }) => {
  return (
    <ul className="fridge-display">
      {console.log(data)}
      <H3 color="indigo" >Produkty w lodówce</H3>
      {data.map((value) => {
        return (
          <li key={value.id} className="fridge-list">
            <Button
              color={value.minQuantity > value.quantity ? "red" : "indigo"}
              buttonType="filled"
              size="lg"
              rounded={false}
              block={true}
              iconOnly={false}
              ripple="light"
             
              data-testid={"single-item"}
              onClick={() => handleListClicked(value)}
            >
            <div className="fridge-button">
              <p>{"Nazwa: " + value.name}</p>
              <p>{"ilość: " + value.quantity}</p>
              {type === "Receipt" ? (
                <p>{"Kcal: " + value.kcal}</p>
              ) : (
                <p>{"Data ważności: " + value.expiryDate}</p>
              )}
              {type === "Fridge" ? (
                <p>{"Minimalna ilość: " + value.minQuantity}</p>
              ) : (
                ""
              )}
              </div>
            </Button>

          </li>
        );
      })}
    </ul>
  );
};

export default FridgeList;
