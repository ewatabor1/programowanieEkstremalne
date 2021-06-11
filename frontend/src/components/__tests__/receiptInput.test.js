import React from "react";
import ReactDOM from "react-dom";
import ReceiptInput from "../receiptInput";
import { render, fireEvent, cleanup, screen } from "@testing-library/react";

afterEach(cleanup);

it("should render the form correctly", () => {
  render(<ReceiptInput/>)
    const inEl = screen.getByTestId('productName-input')
    expect(inEl).toBeInTheDocument();
    expect(inEl).toHaveAttribute('type','text')
});
