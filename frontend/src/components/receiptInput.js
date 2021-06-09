import React from 'react';

const ReceiptInput = ({handleSubmit,handleAddedRecipe,myChangeHandler})=>{
  
    return(
        <div className="Receipt-addingProduct">
            <button onClick={handleAddedRecipe}>Zatwierdź przepis</button>
            <p> </p>
            <form>
              <p>Nazwa przepisu:</p>
              <input
                type="text"
                name="productName"
                data-testid='productName-input'
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
    )
}

export default ReceiptInput