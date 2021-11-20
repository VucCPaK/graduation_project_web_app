import axios from "axios";

export default class {
    static updateItem(id, item) {
        return  axios
            .post(`http://localhost:8081/api/item/${id}`,
                new DItem(item.name, item.price, item.amount, item.description,
                    item.pictures, item.categories, item.type, item.supplierId));
    }

    static addImages(id, formData) {
        axios
            .post(`http://localhost:8081/api/picture/${id}`,
                formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }
            );
    }

    static swapPriority(id, firstName, secondName) {
        axios
            .post(`http://localhost:8081/api/picture/${id}/priority?firstName=${firstName}&secondName=${secondName}`);
    }

    static removeImages(pictures) {
        axios
            .post(`http://localhost:8081/api/picture/deletion`, pictures);
    }

    static createItem(itemProperties) {
        return axios
            .post(`http://localhost:8081/api/item/new`,
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