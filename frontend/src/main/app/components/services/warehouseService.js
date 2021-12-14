import axios from "axios";
import VALUES from "./VALUES.js";

export default class WarehouseService {
    static getList() {
        return axios
            .get(`${VALUES.GATEWAY}/api/backend/warehouse`)
            .then(response => response.data);
    }

    static submit(id) {
        return axios
            .post(`${VALUES.GATEWAY}/api/backend/warehouse/${id}?sent=true`);
    }

    static getItemsInOrder(id) {
        return axios
            .get(`${VALUES.GATEWAY}/api/backend/warehouse/${id}`)
            .then(response => response.data)
    }
}