import axios from 'axios';
const host = "http://localhost"
const port = ":8080"

export const getAllProduct = ()=>{
    return axios.post(host+port+"/get_db")
}
export const popQuantity = (selectedRow) => {
    return axios.post(host+port+"/pop",selectedRow)
}
export const addQuantity = (selectedRow) => {
    return axios.post(host+port+"/add",selectedRow)
}
export const popOnedayNeed = (selectedRow) => {
    return axios.post(host+port+"/pop-options",selectedRow)
}
export const addOnedayNeed = (selectedRow) => {
    return axios.post(host+port+"/add-options",selectedRow)
}