import React, { useState, useEffect } from "react";
import "./Fridge.css";
import FridgeInput from "../components/fridgeInput";
import FridgeDelete from "../components/fridgeDelete";
import FridgeUpdate from "../components/fridgeUpdate";
import { GetData } from "../components/hooks/fetchData";
import FridgeList from "../components/fridgeList";
import Modal from "@material-tailwind/react/Modal";
import ModalHeader from "@material-tailwind/react/ModalHeader";
import ModalBody from "@material-tailwind/react/ModalBody";

const Fridge = () => {
  const [data, setData] = useState([]);
  const [testRemove, setTestRemove] = useState("");
  const [state, setState] = useState(0);
  const [showModal, setShowModal] = React.useState(false);
  useEffect(() => {
    GetData("/api/products")
      .then((data) => {
        setData(data);
      })
      .then((response) => console.log(response));
  }, [state]);

  const handleListClicked = (value) => {
    console.log(value.id);
    setTestRemove(value.id);
    setShowModal(true);
  };

  const updateState = (InputState) => {
    console.log(InputState);
    setTimeout(() => {
      setState(InputState);
    }, 500);
    setShowModal(false)
  };
  return (
    <div className="fridge-container">
      <Modal
        size="regular"
        active={showModal}
        toggler={() => setShowModal(false)}
      >
        <ModalHeader toggler={() => setShowModal(false)}></ModalHeader>
        <ModalBody>
          <FridgeDelete updateState={updateState} testRemove={testRemove} />
          <FridgeUpdate valueToRemove={testRemove} updateState={updateState} />
        </ModalBody>
      </Modal>
        <FridgeInput updateState={updateState} />
      <FridgeList
        data={data}
        handleListClicked={handleListClicked}
        type="Fridge"
      />
    </div>
  );
};

export default Fridge;
