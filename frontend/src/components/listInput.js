import React from "react";
import './listInput.js';
const ListInput = ({handleChange, handleSubmit})=>{
    return(
        <div className="list-firstpart">
        <form className="form-namelist">
          <p>Nazwa listy:</p>
          <input type="text" name="listName" onChange={handleChange} />
        </form>
        <form className="form-listitem">
          <p>Nazwa produktu:</p>
          <input type="text" name="productName" onChange={handleChange} />
          <p>Ilość produktu:</p>
          <input type="text" name="productValue" onChange={handleChange} />
        </form>
        <button onClick={handleSubmit}>Dodaj produkt</button>
      </div>
    )
}

export default ListInput