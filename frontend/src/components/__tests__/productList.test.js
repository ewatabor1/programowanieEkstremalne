import { render, screen, cleanup } from "@testing-library/react";
import {mount, shallow, configure} from 'enzyme'
import Adapter from 'enzyme-adapter-react-16'
import ProductList from '../productList'
configure({adapter: new Adapter()})
const test = [{id:1,product:'test2',quantity:1},{id:2,product:'test2',quantity:1}]
describe('<ProductList/>', () => {
    const wrapper = shallow(
        <ProductList test={test}/>
    )

    it('renders', () => {
        shallow(<ProductList/>)
    })

    it('display li',()=>{
        expect(wrapper.find('li')).toHaveLength(2);
    });
});