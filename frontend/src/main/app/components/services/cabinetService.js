import axios from "axios";
import VALUES from "./VALUES.js";

export default class WarehouseService {
    static getList(id) {
        return axios
            .get(`${VALUES.GATEWAY}/api/backend/cabinet/${id}`)
            .then(response => response.data);
    }
}