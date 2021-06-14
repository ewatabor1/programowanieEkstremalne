import React, { useState, useEffect } from "react";
import "./Receipt.css";
import { GetData, PostData } from "../components/hooks/fetchData";
import FridgeList from "../components/fridgeList";
import ReceiptInput from "../components/receiptInput";
import ReceiptList from "../components/receiptList";
import SelectedList from "../components/selectedList";

const ReceiptScreen = () => {
  const [products, setProducts] = useState([]);
  const [productName, setProductName] = useState("");
  const [productQuantity, setProductQuantity] = useState("");
  const [data, setData] = useState([]);
  const [recipes, setRecipes] = useState([]);
  const [testRemove, setTestRemove] = useState({});
  const [state, setState] = useState(0);
  useEffect(() => {
    GetData("/api/products").then((data) => {
      setData(data);
    });
  }, []);

  useEffect(() => {
    GetData("/api/recipes").then((data) => {
      setRecipes(data);
    });
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
    const product = { ...testRemove, amount: productQuantity };
    const array = [...products, product];
    setProducts(array);
  };
  const handleListClicked = (value) => {
    console.log(value);
    const Remove = { name: value.name, productId: value.id, kcal: value.kcal};
    setTestRemove(Remove);
  };

  const handleAddedRecipe = () => {
    console.log(products)
    const json = JSON.stringify({
      name: productName,
      ingredients: products
    });
    PostData("/api/recipes", json);
    setTimeout(()=>{setState(Math.random())},500)
    setProducts([]);
    setProductName('')
  };
  return (
    <div className="Receipt-div">
      <div className="Receipt-columns">
        <div className="Receipt-products">
          <ReceiptInput
            myChangeHandler={myChangeHandler}
            handleSubmit={handleSubmit}
          />
          <div className="Receipt-productList">
            <FridgeList
              data={data}
              handleListClicked={handleListClicked}
              type="Receipt"
            />
          </div>
        </div>
        <SelectedList
          products={products}
          productName={productName}
          handleListClicked={handleListClicked}
          handleAddedRecipe={handleAddedRecipe}
          myChangeHandler={myChangeHandler}
        />
        <ReceiptList recipes={recipes} />
      </div>
    </div>
  );
};

export default ReceiptScreen;
