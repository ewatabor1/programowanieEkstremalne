import React, { useState, useEffect } from "react";
import "./Fridge.css";
import axios from "axios";
import FridgeInput from "../components/fridgeInput";
import FridgeDelete from "../components/fridgeDelete";
import FridgeUpdate from "../components/fridgeUpdate";
const LOCAL_URL = "http://localhost:8080/api/products";
const initialState = [
  { id: 1, value: 2, name: "Banan" },
  { id: 2, value: 3, name: "Truskawki" },
  { id: 3, value: 5, name: "Czekolada" },
  { id: 4, value: 1, name: "Mleko" },
];

const Fridge = () => {
  const [data, setData] = useState(initialState);
  const [productValue, setProductValue] = useState("");
  const [productName, setProductName] = useState("");
  const [test, setTest] = useState([]);
  const [testRemove, setTestRemove] = useState('')
  let valueToRemove = "";
  useEffect(() => {
    axios
      .get(LOCAL_URL)
      .then((response) => {
        setData(response.data);
        console.log(data);
      })
      .catch((err) => console.log(err.message));
  }, []);

  const handleChange = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    if (nam === "productValue") {
      setProductValue(val);
    } else if (nam === "productName") {
      setProductName(val);
    }
  };

  const handleSubmit = async () => {
    const json = JSON.stringify({
      products: [{ product: productName, quantity: productValue }],
    });
    console.log(json);
    await axios
      .post(LOCAL_URL, json, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then(
        axios
          .get(LOCAL_URL)
          .then((response) => {
            setTest(response.data);
            console.log(test);
          })
          .catch((err) => console.log(err.message))
      );
  };

  const handleListClicked = (value) => {
    valueToRemove = value;
    console.log(valueToRemove);
    setTestRemove(value)
  };
  return (
    <div className="fridge-container">
      <div>
        <div className="fridge-title">
          <h1>Lodówka</h1>
        </div>
        <FridgeInput LOCAL_URL={LOCAL_URL} />
        <FridgeDelete />
        <FridgeUpdate valueToRemove={testRemove} LOCAL_URL={LOCAL_URL} />
      </div>
      <div >
        <ul className="fridge-display">
          {data.map((value) => {
            return (
              <li key={value.id} className="list-fridge">
                <button
                  className="list-button"
                  onClick={() => handleListClicked(value.id)}
                  data-testid={value.value}
                >
                  <p>{value.name}</p>
                  <p>{"ilość: " + value.value}</p>
                </button>
              </li>
            );
          })}
        </ul>
      </div>
    </div>
  );
};

export default Fridge;
