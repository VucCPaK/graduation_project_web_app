import axios from "axios";
import VALUES from "./VALUES.js";

export default class WarehouseService {
    static getList(id) {
        return axios
            .get(`${VALUES.GATEWAY}/api/backend/cabinet/${id}`)
            .then(response => response.data);
    }

    static getSupplierStatistic(id, period) {
        return axios
            .get(`${VALUES.GATEWAY}/api/backend/statistic/supplier/${id}`, {
                responseType: 'blob',
                params: {
                    startDate: period.startDate,
                    endDate: period.endDate
                }
            })
            .then(response => new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' }));
    }

    static getAdminStatistic(period) {
        return axios
            .get(`${VALUES.GATEWAY}/api/backend/statistic/admin`, {
                responseType: 'blob',
                params: {
                    startDate: period.startDate,
                    endDate: period.endDate
                }
            })
            .then(response => new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' }));
    }
}