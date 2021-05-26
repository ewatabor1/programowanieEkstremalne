import React, {useState} from "react";
import "./fridgeOptions.css";
import axios from "axios";
const FridgeUpdate = ({ LOCAL_URL, valueToRemove, updateState }) => {
    const testValue = valueToRemove;
    const [productValue, setProductValue] = useState('');
    const handleChange = (event) => {
      let nam = event.target.name;
      let val = event.target.value;
      let value = parseInt(val, 10);
        setProductValue(value);
    };
    const handleSubmit = async () => {
        console.log(testValue)
        if(productValue>0) {
          await axios
          .put(LOCAL_URL+`/supply/${testValue}`+`/${productValue}`,'' ,{
              headers: {
                "Content-Type": "application/json",
              },})
              updateState(Math.random())
        }else{
          setProductValue(Math.abs(productValue))
          console.log('odejmowanie')
          await axios
          .put(LOCAL_URL+`/consume/${testValue}`+`/${productValue}`,'' ,{
              headers: {
                "Content-Type": "application/json",
              },})
              updateState(Math.random())
        }


    };
  return (
    <div className="fridge-input-container">
      <h2>Aktualizowanie</h2>
      <form className="fridge-listitem">
        <p>Ilość:</p>
        <input type="text" name="productValue" onChange={handleChange} />
      </form>
      <button onClick={handleSubmit}>Uaktualnij produkt</button>
    </div>
  );
};

export default FridgeUpdate;
