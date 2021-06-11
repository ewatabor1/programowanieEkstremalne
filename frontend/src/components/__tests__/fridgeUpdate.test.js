import React from "react";
import ReactDOM from "react-dom";
import {render,fireEvent, cleanup, screen} from '@testing-library/react'
import FridgeUpdate from '../fridgeUpdate'

afterEach(cleanup);

test('Inputing text updates the state',()=>{
    render(<FridgeUpdate/>)
    const inEl = screen.getByTestId('productValue-input')
    expect(inEl).toBeInTheDocument();
    expect(inEl).toHaveAttribute('type','text')
    // const {getByText, getByLabelText} = render(<ListInput/>);
    // expect(getByText(/Change/i).textContent).toBe('Change: ')
    // fireEvent.change(getByLabelText('Input Text: '), {target:{value:'Text'}})
    // expect(getByText(/Change/i).textContent).not.toBe('Change: ')
})
