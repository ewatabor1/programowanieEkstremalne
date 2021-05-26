import {screen, cleanup, act } from "@testing-library/react";
import renderer from 'react-test-renderer'
import navigationBar from '../navigationBar'
import {render, unmountComponentAtNode} from 'react-dom'
import NavigationBar from '../navigationBar'
let container = null;

beforeEach(() => {
    // setup a DOM element as a render target
    container = document.createElement("div");
    document.body.appendChild(container);
  });
  
  afterEach(() => {
    // cleanup on exiting
    unmountComponentAtNode(container);
    container.remove();
    container = null;
  });

  test("should render navigation component", () => {
    const test ={ path:'/', name:'Home' }
    render(<NavigationBar navigation ={test} />);
    const todoElement = screen.getByTestId('Home');
    expect(todoElement).toBeInTheDocument();
  });