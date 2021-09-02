import axios from "axios";

export default class AuthService {
    static registration(login,
                        password,
                        mail,
                        firstName,
                        lastName,
                        phone) {
        return axios
            .post("http://localhost:8081/api/user/registration", {
                login: login,
                password: password,
                mail: mail,
                firstName: firstName,
                lastName: lastName,
                phone: phone
            });
    }

    static isUserPresentInDirectory(id) {
        return axios
            .get(`http://localhost:8081/api/user/examination/${id}`);
    }
}