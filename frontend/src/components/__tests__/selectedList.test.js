import { render, screen, cleanup } from "@testing-library/react";
import SelectedList from "../selectedList";

afterEach(cleanup);
const test = [
  { id: 1, name: "test2", value: 1 },
  { id: 2, name: "test4", value: 2 },
];
const testName = "test-recipes";
it("should render SelectedList component", () => {
  const ListComponent = render(
    <SelectedList products={test} productName={testName} />
  );
  expect(ListComponent).toBeTruthy();
});

if (
  ("renders data correctly",
  () => {
    const { getAllByTestId } = render(
      <SelectedList products={test} productName={testName} />
    );
    const itemNames = getAllByTestId("single-item").map((li) => li.textContent);
    const fakeItems = test.map((val) => val.item);
    expect(itemNames).toEqual(fakeItems);
  })
);

if (
  ("Total length of list should be 3",
  () => {
    const { getAllByTestId } = render(
      <SelectedList products={test} productName={testName} />
    );
    const listUI = getAllByTestId("item-list-wrap");
    expect(listUI.children.length).toBe(2);
  })
);
