import React from "react";
import './fridgeOptions.css'

const FridgeDelete = ({handleChange,handleSubmit}) => {

    return (
        <div className="fridge-input-container">
        <h2>Usuwanie</h2>
        <button onClick={handleSubmit}>Usuń produkt</button>
      </div>
    )

}

export default FridgeDelete