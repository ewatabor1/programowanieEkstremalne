import React from 'react'
import {render, waitFor,cleanup} from '@testing-library/react'
import FridgeScreen from '../../sites/FridgeScreen'
import mockedAxios from 'axios'

afterEach(cleanup);

test('mocking axios request', async () => {
    const data = {
        data:[
            {name:'Pepsi',
            kcal:250,
            expiryDate:'2021-05-05',
            quantity:3,
            minQuantity:2},
            {name:'Cola',
            kcal:200,
            expiryDate:'2021-06-05',
            quantity:2,
            minQuantity:1},
    
        ]
    }
    mockedAxios.get.mockResolvedValueOnce(data);
    const {getByText} = render(<FridgeScreen/>);
        expect(getByText).toMatch('Cola')
})