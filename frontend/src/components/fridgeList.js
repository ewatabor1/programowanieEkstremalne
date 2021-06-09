import React from 'react'


const FridgeList = ({data, handleListClicked, type}) =>{

    return(
        <ul className="fridge-display">
        {console.log(data)}
          {data.map((value) => {
            return (
              <li key={value.id} className="fridge-list">
                <button
                  className={
                    value.minQuantity > value.quantity
                      ? "fridge-button2"
                      : "fridge-button"
                  }
                  onClick={() => handleListClicked(value)}
                  data-testid={value.quantity}
                >
                  <p>{"Nazwa: " + value.name}</p>
                  <p>{"ilość: " + value.quantity}</p>
                  {type==='Receipt' ? <p>{'Kcal: ' + value.kcal}</p> :<p>{"Data ważności: " + value.expiryDate}</p>}
                  {type==='Fridge' ? <p>{"Minimalna ilość: "+ value.minQuantity}</p>: ''}
                </button>
              </li>
            );
          })}
        </ul>
    )
}

export default FridgeList