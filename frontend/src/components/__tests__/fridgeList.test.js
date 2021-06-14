import { render, cleanup } from "@testing-library/react";
import FridgeList from "../fridgeList";

afterEach(cleanup);
const test = [
  { id: 1, name: "test2", value: 1, quantity: 12, minQuantity: 2 },
  { id: 2, name: "test4", value: 2, quantity: 12, minQuantity: 2 },
];
it("should render FridgeList component", () => {
  const ListComponent = render(<FridgeList data={test} />);
  expect(ListComponent).toBeTruthy();
});

it("renders data correctly",() => {
    const { getAllByTestId } = render(<FridgeList data={test} />);
    const itemNames = getAllByTestId("single-item").map((li) => li.textContent);
    const fakeItems = test.map((val) => val.item);
    expect(itemNames).toEqual(fakeItems);
  })


it("Total length of list should be 3",
  () => {
    const { getAllByTestId } = render(<FridgeList data={test} />);
    const listUI = getAllByTestId("item-list-wrap");
    expect(listUI.children.length).toBe(3);
  })

