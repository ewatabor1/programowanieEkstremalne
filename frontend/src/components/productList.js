import React from "react";
import ListButton from "./listButton";

const ProductList = ({ test, handleListClicked }) => {
  return (
    <div>
      <ul>
        {test.map((value) => {
          return (
            <li key={value.id} className="list-grocery">
             <ListButton value={value} handleListClicked={handleListClicked}/>
            </li>
          );
        })}
      </ul>
    </div>
  );
};

export default ProductList;
