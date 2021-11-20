import axios from "axios";

export default class WarehouseService {
    static getList(id) {
        return axios
            .get(`http://localhost:8081/api/cabinet/${id}`)
            .then(response => response.data);
    }
}