import React from 'react'

const SelectedList = ({productName,products, handleListClicked})=>{

    return(
        <div className="Receipt-actualproducts">
        {console.log(products)}
          <h2>{"Nazwa przepisu " + productName}</h2>
          <h2>Wybrane produkty:</h2>
          {products.map((value) => {
              console.log(value)
            return (
              <li key={value.id} className="fridge-list">
                <button
                  className="fridge-button"
                  onClick={() => handleListClicked(value.id)}
                  data-testid={value.value}
                >
                  <p>{'Produkt: '+value.name}</p>
                  <p>{"ilość: " + value.amount}</p>
                  <p>{'Kcal: '+value.kcal*value.amount}</p>
                </button>
              </li>
            );
          })}
        </div>
    )
}

export default SelectedList