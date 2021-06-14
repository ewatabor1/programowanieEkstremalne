import React from "react";
import H4 from "@material-tailwind/react/Heading4";
import CardHeader from "@material-tailwind/react/CardHeader";
import H6 from "@material-tailwind/react/Heading6";
import LeadText from "@material-tailwind/react/LeadText";
import Button from "@material-tailwind/react/Button";
const ReceiptList = ({ recipes }) => {
  return (
    <div className="Receipt-Receipts">
      {console.log(recipes)}
      <H4 color="indigo">Przepisy</H4>
      {recipes.map((value, key) => {
        return (
          <li key={key} className="recipe-list">
          <button className="recipe-title-button" onClick={()=>console.log('Click')}>
          <CardHeader color="indigo" size="sm">
            <H6 color='white'>{value.name}</H6>
            </CardHeader>
            </button>
            {value.ingredients.map((ingrediens) => {
              return (
                <div className="list-ingrediens" data-testid="single-item">

                  <LeadText>{"Składnik: " + ingrediens.product.name}</LeadText>
                  <LeadText>{"Ilość: " + ingrediens.amount}</LeadText>
                  <LeadText>
                    {"Kalorie: " + ingrediens.amount * ingrediens.product.kcal}
                  </LeadText>
                </div>
              );
            })}
          </li>
        );
      })}
    </div>
  );
};

export default ReceiptList;
