import React from "react";
import './fridgeOptions.css'
import axios from "axios";

const FridgeDelete = ({LOCAL_URL, updateState, testRemove}) => {
  const handleSubmit = async () => {
   

    await axios
      .delete(LOCAL_URL+`/${testRemove}`)
      .then((response) => console.log(response));

    updateState(Math.random()*10)
  };
    return (
        <div className="fridge-input-container">
        <h2>Usuwanie</h2>
        <button onClick={handleSubmit}>Usuń produkt</button>
      </div>
    )

}

export default FridgeDelete