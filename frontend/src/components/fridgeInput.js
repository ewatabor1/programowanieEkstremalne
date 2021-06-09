import React, { useState } from "react";
import "./fridgeOptions.css";
import {PostData} from './hooks/fetchData'
const FridgeInput = ({ LOCAL_URL, updateState }) => {
  const [productName, setProductName] = useState("");
  const [productValue, setProductValue] = useState("");
  const [productExpire, setProductExpire] = useState("");
  const [productQuantity, setProductQuantity] = useState("");
  const [minimalQuantity, setMinimalQuantity] = useState(0)
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
    } else if(nam === "productQuantity") {
      const value = parseInt(val, 10);
      setProductQuantity(value);
    }else {
      const value = parseInt(val, 10);
      setMinimalQuantity(value);
    }
  };
  const handleSubmit = () => {
    const json = JSON.stringify({
      name: productName,
      kcal: productValue,
      expiryDate: productExpire,
      quantity: productQuantity,
      minQuantity: minimalQuantity,
    });
    PostData(LOCAL_URL,json)
    updateState(Math.random())
  };

  return (
    <div className="fridge-input-container">
      <h2>Dodawanie</h2>
      <form className="fridge-listitem" data-testid='from'>
        <p>Nazwa produktu:</p>
        <input type="text" name="productName" data-testid='productName-input' onChange={handleChange} />
        <p>Wartość energetyczna:</p>
        <input type="text" name="productValue" onChange={handleChange2} />
        <p>Data ważności:</p>
        <input type="text" name="productExpire" onChange={handleChange} />
        <p>Ilość:</p>
        <input type="text" name="productQuantity" onChange={handleChange2} />
        <p>Minimalna ilość:</p>
        <input type="text" name="minimalQuantity" onChange={handleChange2} />
      </form>
      <button onClick={handleSubmit}>Dodaj produkt</button>
    </div>
  );
};

export default FridgeInput;
