import { render, screen, cleanup } from "@testing-library/react";
import NavigationBar from '../navigationBar'

afterEach(cleanup)
const test = [{ path: "/", name: "Home" },{ path: "/receipt", name: "Receipt" },];
it('should render ProductList component',()=>{
    const ListComponent = render(<NavigationBar navigation={test}/>)
    expect(ListComponent).toBeTruthy();
});

if('renders data correctly', ()=>{

    const {getAllByTestId} = render(<NavigationBar navigation={test}/>)
    const itemNames = getAllByTestId('single-item').map(li=>li.textContent)
    const fakeItems = test.map(val=>val.item)
    expect(itemNames).toEqual(fakeItems)
});

if('Total length of list should be 3', ()=>{
    const {getAllByTestId} = render(<NavigationBar navigation={test}/>)
    const listUI = getAllByTestId('item-list-wrap')
    expect(listUI.children.length).toBe(2)
});
