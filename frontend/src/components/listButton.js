import React from "react";
import './listInput.css'
import Button from "@material-tailwind/react/Button";
const ListButton = ({value,handleListClicked})=>{
    return(
      <div className="list-button2">
      <Button
            color="indigo"
            buttonType="filled"
            size="lg"
            rounded={false}
            block={true}
            iconOnly={false}
            ripple="light"
            className="list-inside-button"
            onClick={()=>handleListClicked(value.id)}
        >
        <div >
        <p>{"nazwa:"+ value.products[0].product}</p>
        <p>{"ilość: " + value.products[0].quantity}</p>
        </div>
        </Button>
        </div>
    )

}

export default ListButton