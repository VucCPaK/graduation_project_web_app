import axios from "axios";

export default class WarehouseService {
    static getList() {
        return axios
            .get("http://localhost:8081/api/warehouse")
            .then(response => response.data);
    }

    static submit(id) {
        return axios
            .post(`http://localhost:8081/api/warehouse/${id}?sent=true`);
    }

    static getItemsInOrder(id) {
        return axios
            .get(`http://localhost:8081/api/warehouse/${id}`)
            .then(response => response.data)
    }
}