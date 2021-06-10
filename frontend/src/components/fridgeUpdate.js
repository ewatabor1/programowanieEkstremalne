import React, { useState } from "react";
import "./fridgeOptions.css";
import {PutData} from './hooks/fetchData'
const FridgeUpdate = ({ valueToRemove, updateState }) => {
  const testValue = valueToRemove;
  const [productValue, setProductValue] = useState("");
  const handleChange = (event) => {
    let val = event.target.value;
    let value = parseInt(val, 10);
    setProductValue(value);
  };
  const handleSubmit = () => {
    const actualValue = productValue;
    if (actualValue > 0) {
      PutData(`/api/products/supply/${testValue}/${productValue}`)
      updateState(Math.random());
    } else {
      const value = Math.abs(productValue)
      PutData(`/api/products/consume/${testValue}/${value}`)
      updateState(Math.random());
    }
  };
  return (
    <div className="fridge-input-container">
      <h2>Aktualizowanie</h2>
      <form className="fridge-listitem" data-formid='form'>
        <p>Ilość:</p>
        <input type="text" name="productValue" data-testid='productValue-input' onChange={handleChange} />
      </form>
      <button onClick={handleSubmit}>Uaktualnij produkt</button>
    </div>
  );
};

export default FridgeUpdate;
