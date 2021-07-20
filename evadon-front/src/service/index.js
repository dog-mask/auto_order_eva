import axios from 'axios';

export const getAllProduct = ()=>{
    return axios.post("http://localhost:8080/get_db")
}