import React from 'react';

const ReceiptList = ({recipes}) =>{

    return(
        <div className="Receipt-Receipts">
        {console.log(recipes)}
        <h2>Dostępne przepisy</h2>
        {recipes.map((value,key) => {
          return (
            <li key={key} className="recipe-list">
              <p>{value.name}</p>
              {value.ingredients.map((ingrediens) => {
                return (
                  <div className="list-ingrediens">
                    <p>{'Składnik: '+ingrediens.product.name}</p>
                    <p>{'Ilość: '+ingrediens.amount}</p>
                    <p>{'Kalorie: '+ingrediens.amount*ingrediens.product.kcal}</p>
                  </div>
                );
              })}
            </li>
          );
        })}
      </div>
    )
}

export default ReceiptList