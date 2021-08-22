import axios from 'axios';
//const host = "http://localhost"
const host = "http://34.64.252.46"
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
    return axios.post(host+port+"/pop-oneday-need",selectedRow)
}
export const addOnedayNeed = (selectedRow) => {
    return axios.post(host+port+"/add-oneday-need",selectedRow)
}
export const popNecessaryQuantity = (selectedRow) => {
    return axios.post(host+port+"/pop-necessary-quantity",selectedRow)
}
export const addNecessaryQuantity = (selectedRow) => {
    return axios.post(host+port+"/add-necessary-quantity",selectedRow)
}
export const addProduct = (item) => {
    return axios.post(host+port+"/add-product",item)
}
export const plusQuantity = () => {
    return axios.post(host+port+"/plus-quan")
}
export const findId = (index) => {
    return axios.post(host+port+"/findId",index)
}
export const modyCode = (item) => {
    return axios.post(host+port+"/modyCode",item)
}

