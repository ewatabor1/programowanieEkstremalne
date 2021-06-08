import { render, screen, cleanup } from "@testing-library/react";
import {mount, shallow, configure} from 'enzyme'
import Adapter from 'enzyme-adapter-react-16'
import ProductList from '../productList'
// configure({adapter: new Adapter()})
// const test = [{id:1,product:'test2',quantity:1},{id:2,product:'test2',quantity:1}]
// describe('<ProductList/>', () => {
//     const wrapper = shallow(
//         <ProductList test={test}/>
//     )

//     it('renders', () => {
//         shallow(<ProductList/>)
//     })

//     it('display li',()=>{
//         expect(wrapper.find('li')).toHaveLength(2);
//     });
// });
afterEach(cleanup)
const test = [{products:[{id:1,product:'test2',quantity:1},{id:2,product:'test4',quantity:2}]},]
it('should render ProductList component',()=>{
    const ListComponent = render(<ProductList test={test}/>)
    expect(ListComponent).toBeTruthy();
});

if('renders data correctly', ()=>{

    const {getAllByTestId} = render(<ProductList test={test}/>)
    const itemNames = getAllByTestId('single-item').map(li=>li.textContent)
    const fakeItems = test.map(val=>val.item)
    expect(itemNames).toEqual(fakeItems)
});

if('Total length of list should be 3', ()=>{
    const {getAllByTestId} = render(<ProductList test={test}/>)
    const listUI = getAllByTestId('item-list-wrap')
    expect(listUI.children.length).toBe(2)
});