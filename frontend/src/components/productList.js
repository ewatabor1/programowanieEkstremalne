import React from "react";
import ListButton from "./listButton";
import H3 from "@material-tailwind/react/Heading3";
const ProductList = ({ test, handleListClicked }) => {
  return (
    <div className="list-secondpart">
    <H3 color="indigo">Lista zakupów</H3>
      <ul className="list-grocery">
        {test.map((value) => {
          const keys = value.id
          return (
            <li key={keys} >
             <ListButton value={value} handleListClicked={handleListClicked}/>
            </li>
          );
        })}
      </ul>
    </div>
  );
};

export default ProductList;
