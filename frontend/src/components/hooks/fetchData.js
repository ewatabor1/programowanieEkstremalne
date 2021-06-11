import axios from "axios";

export const DeleteData = async ( url ) => {
  await axios
    .delete(url)
    .catch((err) => console.error(err));
};

export const GetData= async ( url ) => {
  return await axios
    .get(url)
    .then((response) => response.data)
    .catch((error) =>console.log(error));
}

export const PostData = async ( url, json ) => {
  await axios
    .post(url, json, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .catch((err) => console.error(err));
};

export const PutData = async ( url ) => {
  await axios
    .put(url, "", {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .catch((err) => console.error(err));
};