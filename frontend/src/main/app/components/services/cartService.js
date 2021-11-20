import axios from "axios";

export default class CartService {
    static items = JSON.parse(localStorage.getItem('cartItems'));

    static getList() {
        return this.items;
    }

    static remove(id) {
        this.items = this.items.filter(item => item.id !== id);
        return this.items;
    }

    static add(item) {
        if (this.items.includes(item))
            return;

        item.quantity = 1;
        this.items.push(item);
        localStorage.setItem('cartItems', JSON.stringify(this.items));
    }

    static isPresentInCart(id) {
        return this.items.some(item => item.id === id)
    }

    static updateAmount(id, quantity) {
        if (quantity <= 0)
            return;

        this.items.find(item => item.id === id).quantity = quantity;
    }

    static getTotalPrice() {
        let dCartItems = [];
        this.items.forEach(item => dCartItems.push(new DCartItem(item.id, item.quantity)));

        return axios
            .post(`http://localhost:8081/api/cart/totalPrice`, dCartItems)
            .then(response => response.data);
    }

    static toOrder() {
        let dCartItems = [];
        this.items.forEach(item => dCartItems.push(new DCartItem(item.id, item.quantity)));

        axios
            .post(`http://localhost:8081/api/cart/order`, dCartItems)
            .then(() => console.log("Thank you for your purchase"));

        this.items.length = 0;
    }

}

class DCartItem {
    constructor(id, quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}