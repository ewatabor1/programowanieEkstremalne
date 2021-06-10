import "../App.css";
import React, { useState, useEffect } from "react";
import ProductList from "../components/productList";
import { GetData, DeleteData } from "../components/hooks/fetchData";
import ListInput from "../components/listInput";

function Home() {
  const [test, setTest] = useState([]);
  const [state, setState] = useState(0);
  let valueToRemove = "";

  useEffect(() => {
    GetData("/api/grocery-lists").then((data) => {
      setTest(data);
      console.log(data);
    });
  }, [state]);

  const handleRemove = () => {
    DeleteData(`/api/grocery-lists/${valueToRemove}`);
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
