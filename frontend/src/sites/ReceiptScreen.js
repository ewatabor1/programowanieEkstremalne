import React, { useState, useEffect } from "react";
import "./Receipt.css";
import axios from "axios";
const LOCAL_URL = "http://localhost:8080/api/";
const ReceiptScreen = () => {
  const [products, setProducts] = useState([]);
  const [productName, setProductName] = useState("");
  const [productQuantity, setProductQuantity] = useState("");
  const [data, setData] = useState([]);
  const [recipes, setRecipes] = useState([]);
  const [testRemove, setTestRemove] = useState({});
  const [state, setState] = useState(0)
  useEffect(() => {
    axios
      .get(LOCAL_URL + "products")
      .then((response) => {
        setData(response.data);
        console.log(data);
      })
      .catch((err) => console.log(err.message));
  }, []);

  useEffect(() => {
    axios
      .get(LOCAL_URL + "recipes")
      .then((response) => {
        setRecipes(response.data);
        console.log(data);
      })
      .catch((err) => console.log(err.message));

  }, [state]);

  const myChangeHandler = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    if (nam === "productQuantity") {
      setProductQuantity(val);
    } else {
      setProductName(val);
    }
  };
  const handleSubmit = () => {
    const product = { ...testRemove, value: productQuantity };
    const array = [...products, product];
    setProducts(array);
    console.log(products);
  };
  const handleListClicked = (value) => {
    const Remove = { name: value.name, id: value.id };
    setTestRemove(Remove);
  };

  const handleAddedRecipe = async () => {
    console.log(products);
    const json = JSON.stringify({
      name: productName,
      ingredients: [
        { productId: products[0].id, amount: products[0].value },
        { productId: products[1].id, amount: products[1].value },
      ],
    });
    await axios
      .post(LOCAL_URL + "recipes", json, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((json) => console.log(json));

    setState(state+1)
  };
  return (
    <div className="Receipt-div">
      <h1 style={{ alignSelf: "center" }}>Przepisy</h1>
      <div className="Receipt-columns">
        <div className="Receipt-products">
          <div className="Receipt-addingProduct">
            <button onClick={handleAddedRecipe}>Zatwierdź przepis</button>
            <p> </p>
            <form>
              <p>Nazwa przepisu:</p>
              <input
                type="text"
                name="productName"
                onChange={myChangeHandler}
              />
            </form>
            <p> </p>
            <form>
              <p>Ilość produktu:</p>
              <input
                type="text"
                name="productQuantity"
                onChange={myChangeHandler}
              />
            </form>
            <button onClick={handleSubmit}>Dodaj produkt</button>
          </div>
          <div className="Receipt-productList">
            <h1>Dostępne produkty</h1>
            {data.map((value) => {
              return (
                <li key={value.id} className="list-fridge">
                  <button
                    className="list-button"
                    onClick={() => handleListClicked(value)}
                    data-testid={value.value}
                  >
                    <p>{value.name}</p>
                    <p>{"ilość: " + value.quantity}</p>
                  </button>
                </li>
              );
            })}
          </div>
        </div>
        <div className="Receipt-actualproducts">
          <h2>{"Nazwa przepisu " + productName}</h2>
          <h2>Wybrane produkty:</h2>
          {products.map((value) => {
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
        <div className="Receipt-Receipts">
          <h2>Dostępne przepisy</h2>
          {recipes.map((value) => {
            console.log(value)
            return (
              <li key={value.id} className="list-fridge">
                <p>{value.name}</p>
                {value.ingredients.map((ingrediens) =>{
                  return(<div className="list-ingrediens">
                    <p>{ingrediens.product.name}</p>
                    <p>{ingrediens.product.quantity}</p>
                    </div>
                  )
                })}
              </li>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default ReceiptScreen;
