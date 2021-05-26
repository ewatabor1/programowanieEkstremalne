import "../App.css";
import React, { useState, useEffect } from "react";
import axios from "axios";
import ProductList from "../components/productList";
const LOCAL_URL = "http://localhost:8080/api/grocery-lists";
const initialState = [
  { id: 1, value: 2, name: "Banan" },
  { id: 2, value: 3, name: "Truskawki" },
  { id: 3, value: 5, name: "Czekolada" },
  { id: 4, value: 1, name: "Mleko" },
];

function Home() {
  const [Data, setData] = useState(initialState);
  const [productValue, setProductValue] = useState('');
  const [productName, setProductName] = useState("");
  const [test, setTest] = useState([]);
  const [listName, setListName] = useState("");
  const [state, setState] = useState(initialState)
  let valueToRemove = "";

  useEffect(() => {
    axios
      .get(LOCAL_URL)
      .then((response) => {
        setTest(response.data);
        console.log(test);
      })
      .catch((err) => console.log(err.message));
  }, [state]);

  const handleChange = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    if (nam === "productValue") {
      setProductValue(val);
    }else
    if (nam === "productName") {
      setProductName(val);
    } else {
      setListName(val);
    }
  };

  const handleSubmit = async () => {
    const json = JSON.stringify({
      name:listName,
      products: [{ product: productName , quantity:productValue }],
    });
    console.log(json);
    await axios
      .post(LOCAL_URL, json, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      setState(state+1)
  };

  const handleRemove = async () => {
    await axios
      .delete(LOCAL_URL + `/${valueToRemove}`)
      .then((response) => console.log(response));
      setState(state+1)
  };

  const handleListClicked = (value) => {
    valueToRemove = value;
    console.log(valueToRemove);
  };
  return (
    <div className="App-main">
    <div className="App-firstpart">
        <form className="form-namelist">
          <p>Nazwa listy:</p>
          <input type="text" name="listName" onChange={handleChange} />
        </form>
        <form className="form-listitem">
          <p>Nazwa produktu:</p>
          <input type="text" name="productName" onChange={handleChange} />
          <p>Ilość produktu:</p>
          <input type="text" name="productValue" onChange={handleChange} />
        </form>
        <button onClick={handleSubmit}>Dodaj produkt</button>
      </div>
     <ProductList test={test} handleListClicked={handleListClicked}/>
      <div>
         <button onClick={handleRemove}>Usuń produkt</button>
       </div>
    </div>
  );
}

export default Home;
