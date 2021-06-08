import React, { useState } from "react";
import "./fridgeOptions.css";
import axios from "axios";
const FridgeInput = ({ LOCAL_URL, updateState }) => {
  const [productName, setProductName] = useState("");
  const [productValue, setProductValue] = useState("");
  const [productExpire, setProductExpire] = useState("");
  const [productQuantity, setProductQuantity] = useState("");
  const handleChange = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    if (nam === "productName") {
      setProductName(val);
    } else {
      setProductExpire(val);
    }
  };
  const handleChange2 = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    if (nam === "productValue") {
      const value = parseInt(val, 10);
      setProductValue(value);
    } else {
      const value = parseInt(val, 10);
      setProductQuantity(value);
    }
  };
  const handleSubmit = async () => {
    console.log(LOCAL_URL)
    const json = JSON.stringify({
      name: productName,
      kcal: productValue,
      expiryDate: productExpire,
      quantity: productQuantity,
      minQuantity: 2,
    });
    console.log(json);
    await axios
      .post(LOCAL_URL, json, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => console.log(response, json));

    updateState(productQuantity)
  };

  return (
    <div className="fridge-input-container">
      <h2>Dodawanie</h2>
      <form className="fridge-listitem">
        <p>Nazwa produktu:</p>
        <input type="text" name="productName" onChange={handleChange} />
        <p>Wartość energetyczna:</p>
        <input type="text" name="productValue" onChange={handleChange2} />
        <p>Data ważności:</p>
        <input type="text" name="productExpire" onChange={handleChange} />
        <p>Ilość:</p>
        <input type="text" name="productQuantity" onChange={handleChange2} />
      </form>
      <button onClick={handleSubmit}>Dodaj produkt</button>
    </div>
  );
};

export default FridgeInput;
