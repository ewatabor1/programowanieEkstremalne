import { render, screen, cleanup } from "@testing-library/react";
import ReceiptList from "../receiptList";

afterEach(cleanup);
const testData = [
  {
    id: 1,
    description:null,
    steps: [],
    name: "test1",
    ingredients: [
      {
        productId: 2,
        amount: 2,
        product: { id: 2, name: "testproduct", kcal: 300, quantity: 10 },
      },
      {
        productId: 3,
        amount: 2,
        product: { id: 3, name: "testproduct2", kcal: 400, quantity: 5 },
      },
    ],
  },
  {
    id: 4,
    description:null,
    steps: [],
    name: "test2",
    ingredients: [
      {
        productId: 5,
        amount: 2,
        product: { id: 2, name: "testproduct", kcal: 300, quantity: 10 },
      },
      {
        productId: 6,
        amount: 2,
        product: { id: 3, name: "testproduct2", kcal: 400, quantity: 5 },
      },
    ],
  },
];
//const test = [{products:[{id:1,product:'test2',quantity:1},{id:2,product:'test4',quantity:2}]},]
it("should render ReceiptList component", () => {
  const ListComponent = render(<ReceiptList recipes={testData} />);
  expect(ListComponent).toBeTruthy();
});

if (
  ("renders data correctly",
  () => {
    const { getAllByTestId } = render(<ReceiptList recipes={testData} />);
    const itemNames = getAllByTestId("single-item").map((li) => li.textContent);
    const fakeItems = test.map((val) => val.item);
    expect(itemNames).toEqual(fakeItems);
  })
);

if (
  ("Total length of list should be 3",
  () => {
    const { getAllByTestId } = render(<ReceiptList recipes={testData} />);
    const listUI = getAllByTestId("item-list-wrap");
    expect(listUI.children.length).toBe(2);
  })
);
