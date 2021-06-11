import React from "react";
import "./fridgeOptions.css";
import { DeleteData } from "./hooks/fetchData";
const FridgeDelete = ({ updateState, testRemove }) => {
  const handleSubmit = () => {
    DeleteData(`/api/${testRemove}`);
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
