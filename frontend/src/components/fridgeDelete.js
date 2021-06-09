import React from "react";
import "./fridgeOptions.css";
import { DeleteData } from "./hooks/fetchData";
const FridgeDelete = ({ LOCAL_URL, updateState, testRemove }) => {
  const handleSubmit = async () => {
    DeleteData(LOCAL_URL + `/${testRemove}`);
    updateState(Math.random() * 10);
  };
  return (
    <div className="fridge-input-container">
      <h2>Usuwanie</h2>
      <button onClick={handleSubmit}>Usuń produkt</button>
    </div>
  );
};

export default FridgeDelete;
