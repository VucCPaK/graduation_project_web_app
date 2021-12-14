import axios from "axios";
import VALUES from "./VALUES.js";

export default class ShopService {
    static getListOfItems() {
        return axios
            .get(`${VALUES.GATEWAY}/api/backend/item`)
            .then(response => response.data);
    }

    static getItemById(id) {
        return axios
            .get(`${VALUES.GATEWAY}/api/backend/item/${id}`)
            .then((response) => response.data);
    }

    static getPictureUrl(epicture) {
        return `${VALUES.GATEWAY}/api/mongo/pictures/${epicture}`;
    }
}
