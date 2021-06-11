import React, { useState, useEffect } from "react";
import "./Fridge.css";
import FridgeInput from "../components/fridgeInput";
import FridgeDelete from "../components/fridgeDelete";
import FridgeUpdate from "../components/fridgeUpdate";
import { GetData } from "../components/hooks/fetchData";
import FridgeList from "../components/fridgeList";


const Fridge = () => {
  const [data, setData] = useState([]);
  const [testRemove, setTestRemove] = useState("");
  const [state, setState] = useState(0);
  useEffect(() => {
    GetData("/api/products").then((data) => {
      setData(data);
    }).then((response)=>console.log(response));
  }, [state]);

  const handleListClicked = (value) => {
    console.log(value.id)
    setTestRemove(value.id);
  };

  const updateState = (InputState) => {
    console.log(InputState)
    setState(InputState);
  };
  return (
    <div className="fridge-container">
      <div>
        <div className="fridge-title">
          <h1>Lodówka</h1>
        </div>
        <FridgeInput
          updateState={updateState}
        />
        <FridgeDelete
          updateState={updateState}
          testRemove={testRemove}
        />
        <FridgeUpdate
          valueToRemove={testRemove}
          updateState={updateState}
        />
      </div>
      <FridgeList data={data} handleListClicked={handleListClicked} type='Fridge' />
    </div>
  );
};

export default Fridge;
