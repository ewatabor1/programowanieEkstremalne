import React from "react";
import './listInput.css'
const ListButton = ({value,handleListClicked})=>{
    return(
        <button
        className="list-button"
        onClick={() => handleListClicked(value.id)}
        data-testid={value.products[0].product}
      >
        <p>{"nazwa:"+ value.products[0].product}</p>
        <p>{"ilość: " + value.products[0].quantity}</p>
      </button>
    )

}

export default ListButton