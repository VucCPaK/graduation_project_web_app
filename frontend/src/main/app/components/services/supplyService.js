import axios from "axios";
import VALUES from "./VALUES.js";

export default class {
    static updateItem(id, item) {
        return axios
            .post(`${VALUES.GATEWAY}/api/backend/item/${id}`,
                new DItem(item.name, item.price, item.amount, item.description,
                    item.pictures, item.categories, item.type, item.supplierId));
    }

    static addImages(id, formData) {
        axios
            .post(`${VALUES.GATEWAY}/api/mongo/pictures`,
                formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }
            ).then((ids) => {
                axios.post(`${VALUES.GATEWAY}/api/backend/pictures/${id}`, ids.data)
            }
        )
    }


    static changePriorities(id, array) {
        axios
            .post(`${VALUES.GATEWAY}/api/backend/pictures/${id}/priority`, array);
    }

    static removeImages(pictures) {
        axios
            .post(`${VALUES.GATEWAY}/api/mongo/pictures/deletion`, pictures);
    }

    static createItem(itemProperties) {
        return axios
            .post(`${VALUES.GATEWAY}/api/backend/item/new`,
                new DItem(itemProperties.name, itemProperties.price, itemProperties.amount, itemProperties.description,
                    itemProperties.pictures, itemProperties.categories, itemProperties.type, itemProperties.supplierId))
    }
}


class DItem {
    constructor(name, price, amount, description, pictures, categories, type, supplierId) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.pictures = pictures;
        this.categories = categories;
        this.type = type;
        this.supplierId = supplierId;
    }
}