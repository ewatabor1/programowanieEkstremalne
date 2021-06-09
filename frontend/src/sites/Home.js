import "../App.css";
import React, { useState, useEffect } from "react";
import ProductList from "../components/productList";
import { GetData, DeleteData } from "../components/hooks/fetchData";
import { LOCAL_URL } from "../variables";
import ListInput from "../components/listInput";

function Home() {
  const [test, setTest] = useState([]);
  const [state, setState] = useState(0);
  let valueToRemove = "";

  useEffect(() => {
    GetData(LOCAL_URL + "grocery-lists").then((data) => {
      setTest(data);
      console.log(data);
    });
  }, [state]);

  const handleRemove = () => {
    DeleteData(LOCAL_URL + `grocery-lists/${valueToRemove}`);
    setState(state + 1);
  };

  const handleListClicked = (value) => {
    valueToRemove = value;
  };
  const handleStateUpdated = (value) => {
    console.log(value);
    setState(value);
  };
  return (
    <div className="App-main">
      <ListInput
        LOCAL_URL={LOCAL_URL}
        handleStateUpdated={handleStateUpdated}
      />
      <ProductList test={test} handleListClicked={handleListClicked} />
      <div>
        <button onClick={handleRemove}>Usuń produkt</button>
      </div>
    </div>
  );
}

export default Home;
