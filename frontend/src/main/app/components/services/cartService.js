import axios from "axios";

export default class CartService {
    static getList() {
        return axios
            .get('http://localhost:8081/api/cart')
            .then(response => response.data);
    }

    static remove(id) {
        return axios
            .delete(`http://localhost:8081/api/cart/${id}`)
            .then(response => response.data);
    }

    static add(item) {
        axios
            .post(`http://localhost:8081/api/cart/${item.id}`, {
                id: item.id,
                name: item.name,
                price: item.price,
                epicture: item.epicture
            });
    }

    static isPresentInCart(id) {
        return axios
            .get(`http://localhost:8081/api/cart/${id}`)
            .then(response => response.data);
    }

    static updateAmount(id, quantity) {
        return axios
            .post(`http://localhost:8081/api/cart/amount`, {
                id: id,
                quantity: quantity
            });
    }

    static getTotalPrice() {
        return axios
            .get(`http://localhost:8081/api/cart/totalPrice`)
            .then(response => response.data);
    }

    static toOrder() {
        axios
            .get(`http://localhost:8081/api/cart/order`)
            .then(() => console.log("Thank you for your purchase"));
    }
}