import React, { useState } from "react";
import "./Receipt.css";
const initialState = [{ name: "Mąka", value: "100" }];
const ReceiptScreen = () => {
  const [products, setProducts] = useState(initialState);
  const [productName, setProductName] = useState('')
  const [productQuantity, setProductQuantity] = useState('')
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
  return (
    <div className="Receipt-div">
      <h2 style={{ alignSelf: "center" }}>Przepisy</h2>
      <div className="Receipt-columns">
        <div className="Receipt-products">
          <div className ='Receipt-addingProduct'>
          <form>
          <p>Nazwa produktu:</p>
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
          <h1>Przypisane produkty</h1>
        {products.map((value) => {
          return (
            <li className='List-product' key={value.id}>
            <p className='Receipt-product-title'>{value.name}</p>
            <p>{value.value}</p>
            </li>
            
          );
        })}
          </div>
        </div>
        <div className="Receipt-Receipts"></div>
      </div>
    </div>
  );
};

export default ReceiptScreen;
