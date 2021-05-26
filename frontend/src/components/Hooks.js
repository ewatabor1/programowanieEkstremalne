import axios from "axios";
const LOCAL_URL = "http://localhost:8080/api/products";
 const Hooks= async () => {
    const response = await axios.get(LOCAL_URL)
    return response.data;
}

export default Hooks