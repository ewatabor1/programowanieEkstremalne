import { render, screen, cleanup } from "@testing-library/react";
import renderer from 'react-test-renderer'
import ListButton from '../listButton'

afterEach(()=>{
    cleanup();
})

test('should render button for list', ()=>{
    const data = {products: [{product: 'test', quantity:'5'}]}
    render(<ListButton value={data}/>)
    const testElement = screen.getByTestId('test');
    expect(testElement).toBeInTheDocument();
    expect(testElement).toHaveTextContent('testilość: 5')
})