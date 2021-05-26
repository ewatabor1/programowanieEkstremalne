import React, { useState, useEffect } from "react";
import './Fridge.css'
import axios from "axios";
const LOCAL_URL = "http://localhost:8080/api/products";
const initialState = [
    { id: 1, value: 2, name: "Banan" },
    { id: 2, value: 3, name: "Truskawki" },
    { id: 3, value: 5, name: "Czekolada" },
    { id: 4, value: 1, name: "Mleko" },
  ];
const Fridge = () =>{
    const [data, setData] = useState(initialState);

    useEffect(() => {
      axios
        .get(LOCAL_URL)
        .then((response) => {
          setData(response.data)
          console.log(test[0].name);
        
        })
        .catch((err) => console.log(err.message));
    }, []);

    return(
        <div>
        <div className="Fridge-title">
        <h1>Lodówka</h1>
        </div>
        <div className='Fridge-list'>
        <ul>
        {data.map((value) => {
          return (
            <li
              key={value.id}
              className="list-grocery"
            >
              <p className="Fridge-item">{value.name}</p>
            </li>
          );
        })}
      </ul>
      </div>
      </div>
    )

}

export default Fridge