import React, { useState,useEffect } from "react";
import "./Receipt.css";
import axios from "axios";
const LOCAL_URL = "http://localhost:8080/api/";
const initialState = [{ name: "Mąka", value: "100" }];
const ReceiptScreen = () => {
  const [products, setProducts] = useState(initialState);
  const [productName, setProductName] = useState('')
  const [productQuantity, setProductQuantity] = useState('')
  const [data, setData] = useState(initialState);
  const [testRemove, setTestRemove] = useState('')
  useEffect(() => {
    axios
      .get(LOCAL_URL+'products')
      .then((response) => {
        setData(response.data);
        console.log(data);
      })
      .catch((err) => console.log(err.message));
  }, []);

  useEffect(() => {
    axios
      .get(LOCAL_URL+'recipes')
      .then((response) => {
        setData(response.data);
        console.log(data);
      })
      .catch((err) => console.log(err.message));
  }, []);
  
  const myChangeHandler = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    if(nam==='productQuantity'){
      setProductQuantity(val)
    }else{
      setProductName(val)
    }
  }
  const handleSubmit = ()=>{
    const product = {name:productName, value:productQuantity}
    const array = [...products,product]
    setProducts(array)
    console.log(products)
  }
  const handleListClicked = (value) => {
    setTestRemove(value)
  };
  return (
    <div className="Receipt-div">
      <h2 style={{ alignSelf: "center" }}>Przepisy</h2>
      <div className="Receipt-columns">
        <div className="Receipt-products">
          <div className ='Receipt-addingProduct'>
          <form>
          <p>Nazwa przepisu:</p>
          <input
            type='text'
            name='productName'
            onChange={myChangeHandler}
          />
          <p>Ilość produktu:</p>
          <input
            type='text'
            name='productQuantity'
            onChange={myChangeHandler}
          />
          </form>
            <button onClick={handleSubmit}>Dodaj produkt</button>
          </div>
          <div className='Receipt-productList'>
          <h1>Dostępne produkty</h1>
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
          </div>
        </div>
        <div className="Receipt-Receipts">
        
        </div>
      </div>
    </div>
  );
};

export default ReceiptScreen;
