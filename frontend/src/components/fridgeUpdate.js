import React, {useState} from "react";
import "./fridgeOptions.css";
import axios from "axios";
const FridgeUpdate = ({ LOCAL_URL, valueToRemove }) => {
    const testValue = valueToRemove;
    const [productValue, setProductValue] = useState('');
    const handleChange = (event) => {
      let nam = event.target.name;
      let val = event.target.value;
        setProductValue(val);
    };
    const handleSubmit = async () => {
        console.log(testValue)
      await axios
        .put(LOCAL_URL+`/${testValue}`+`/${productValue}`,'' ,{
            headers: {
              "Content-Type": "application/json",
            },})

    };
  return (
    <div className="fridge-input-container">
      <h2>Aktualizowanie</h2>
      <form className="fridge-listitem">
        <p>Ilość:</p>
        <input type="text" name="productValue" onChange={handleChange} />
      </form>
      <button onClick={handleSubmit}>Dodaj produkt</button>
    </div>
  );
};

export default FridgeUpdate;
