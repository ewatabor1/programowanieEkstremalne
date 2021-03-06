import { render, screen, cleanup } from "@testing-library/react";
import ProductList from "../productList";

afterEach(cleanup);
const test = [
  {
    products: [
      { id: 1, product: "test2", quantity: 1 },
      { id: 2, product: "test4", quantity: 2 },
    ],
  },
];
it("should render ProductList component", () => {
  const ListComponent = render(<ProductList test={test} />);
  expect(ListComponent).toBeTruthy();
});

if (
  ("renders data correctly",
  () => {
    const { getAllByTestId } = render(<ProductList test={test} />);
    const itemNames = getAllByTestId("single-item").map((li) => li.textContent);
    const fakeItems = test.map((val) => val.item);
    expect(itemNames).toEqual(fakeItems);
  })
);

if (
  ("Total length of list should be 3",
  () => {
    const { getAllByTestId } = render(<ProductList test={test} />);
    const listUI = getAllByTestId("item-list-wrap");
    expect(listUI.children.length).toBe(2);
  })
);
