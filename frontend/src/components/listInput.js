import React, {useState} from "react";
import './listInput.js';
import {PostData} from '../components/hooks/fetchData'
const ListInput = ({ handleStateUpdated})=>{
  const [productValue, setProductValue] = useState("");
  const [productName, setProductName] = useState("");
  const [listName, setListName] = useState("");

  const handleChange = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    if (nam === "productValue") {
      setProductValue(val);
    } else if (nam === "productName") {
      setProductName(val);
    } else {
      setListName(val);
    }
  };

  const handleSubmit = () => {
    const json = JSON.stringify({
      name: listName,
      products: [{ product: productName, quantity: productValue }],
    });
    PostData("/api/grocery-lists", json);
    handleStateUpdated(Math.random())
  };
    return(
        <div className="list-firstpart">
        <form className="form-listitem" data-testid='form'>
          <p>Nazwa produktu:</p>
          <input type="text" name="productName" data-testid="productName-input" onChange={handleChange} />
          <p>Ilość produktu:</p>
          <input type="text" name="productValue" onChange={handleChange} />
        </form>
        <button onClick={handleSubmit}>Dodaj produkt</button>
      </div>
    )
}

export default ListInput