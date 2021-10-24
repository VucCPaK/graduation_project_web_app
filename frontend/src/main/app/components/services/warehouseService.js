import axios from "axios";

export default class WarehouseService {
    static getList() {
        return axios
            .get("http://localhost:8081/api/warehouse")
            .then(response => response.data);
    }

    static submit(updatedItems) {
        let items = [];

        updatedItems
            .filter(item => item.sent === true)
            .forEach(item => items.push(new DWarehouseItem(item.id)));

        return axios
            .post("http://localhost:8081/api/warehouse/item", items)
            .then(response => response.data)
    }
}

class DWarehouseItem {
    constructor(id) {
        this.id = id;
    }
}