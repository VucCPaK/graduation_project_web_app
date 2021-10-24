import axios from "axios";

export default class ShopService {

    static getListOfItems() {
        return axios
            .get('http://localhost:8081/api/item')
            .then(response => response.data);
    }

    static getItemById(id) {
        return axios
            .get(`http://localhost:8081/api/item/${id}`)
            .then((response) => response.data);
    }

    static getPictureUrl(id, epicture) {
        return `http://localhost:8081/api/picture/${id}/pictures/${epicture}`;
    }
}
