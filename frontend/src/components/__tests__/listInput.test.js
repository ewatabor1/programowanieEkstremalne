import React from "react";
import ReactDOM from "react-dom";
import ListInput from "../listInput";
import {render,fireEvent, cleanup, screen} from '@testing-library/react'

afterEach(cleanup);

test('Inputing text updates the state',()=>{
    render(<ListInput/>)
    const inEl = screen.getByTestId('productName-input')
    expect(inEl).toBeInTheDocument();
    expect(inEl).toHaveAttribute('type','text')
    // const {getByText, getByLabelText} = render(<ListInput/>);
    // expect(getByText(/Change/i).textContent).toBe('Change: ')
    // fireEvent.change(getByLabelText('Input Text: '), {target:{value:'Text'}})
    // expect(getByText(/Change/i).textContent).not.toBe('Change: ')
})

