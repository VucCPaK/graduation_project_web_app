export default class AuthService {
    static KEYCLOAK_DOMAIN = "localhost:8085";
    static CLIENT_ID = "my_client"
    static REDIRECT_URI = "http://localhost:8082";

    static async handleCallback() {
        const search = new URLSearchParams(window.location.search);
        if (!search.has('code')) {
            return;
        }
        const code = search.get('code');

        const config = await this.getConfig();

        // exchange the authorization code for a tokenset
        const tokenSet = await fetch(config.token_endpoint, {
            method: 'POST',
            body: new URLSearchParams({
                client_id: this.CLIENT_ID,
                redirect_uri: this.REDIRECT_URI,
                grant_type: 'authorization_code',
                code: code,
            }),
            headers: new Headers({
                'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8'
            })
        }).then(r => r.json());

        //this has access_token, id_token, expires_in, scope, token_type.
        // console.dir(tokenSet);

        window.sessionStorage.setItem('token_type', tokenSet.token_type);
        window.localStorage.setItem('refresh_token', tokenSet.refresh_token);
        window.sessionStorage.setItem('access_token', tokenSet.access_token);

        //remove the querystring from the url in the address bar
        const url = new URL(window.location);
        url.search = '';
        window.history.pushState('', document.title, url);
    };

    static async getConfig() {
        const response = await fetch(
            `http://${this.KEYCLOAK_DOMAIN}/auth/realms/my_realm/.well-known/openid-configuration`);
        return response.json();
    }


    static getRole() {
        let token = sessionStorage.getItem('access_token');
        if (token === null || token === 'undefined')
            return 'anonymous';

        let decodedToken = this.parseJwt(token);
        let value = decodedToken.group[0];
        if (value === undefined)
            return 'anonymous';

        return value;
    }

    static getUserId() {
        let token = sessionStorage.getItem('access_token');
        if (token === null || token === 'undefined')
            return;

        let decodedToken = this.parseJwt(token);
        let value = decodedToken.user_id;
        if (value === undefined)
            return;

        return value;
    }


    static parseJwt(token) {
        let base64Url = token.split('.')[1];
        let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        let jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        return JSON.parse(jsonPayload);
    };

    static async updateAccessToken() {
        let refreshToken = localStorage.getItem('refresh_token');
        if (refreshToken && refreshToken !== 'undefined') {
            const config = await this.getConfig();

            const tokenSet = await fetch(config.token_endpoint, {
                method: 'POST',
                body: new URLSearchParams({
                    client_id: this.CLIENT_ID,
                    redirect_uri: this.REDIRECT_URI,
                    grant_type: 'refresh_token',
                    refresh_token: refreshToken
                }),
                headers: new Headers({
                    'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8'
                })
            }).then(r => r.json());

            // console.dir(tokenSet)

            window.localStorage.setItem('refresh_token', tokenSet.refresh_token);
            window.sessionStorage.setItem('token_type', tokenSet.token_type);
            window.sessionStorage.setItem('access_token', tokenSet.access_token);

            //remove the querystring from the url in the address bar
            // const url = new URL(window.location);
            // url.search = '';
            // window.history.pushState('', document.title, url);
        }
    }

    // static isAccessTokenAlive() {
    //     let accessToken = sessionStorage.getItem('access_token');
    //
    //     if (!accessToken || accessToken === 'undefined')
    //         return false;
    //
    //     let parsedToken = this.parseJwt(accessToken);
    //
    //     return (parsedToken.exp > Date.now() / 1000);
    // }

    static isRefreshTokenPresent() {
        let refreshToken = localStorage.getItem('refresh_token');

        return refreshToken && refreshToken !== 'undefined';
    }

    static removeAccessAndRefreshToken() {
        window.localStorage.removeItem('refresh_token');
        window.sessionStorage.removeItem('token_type');
        window.sessionStorage.removeItem('access_token');
    }


    // lifetime of token - 3 min
    static async refreshToken() {
        await this.handleCallback();

        if (this.isRefreshTokenPresent()) {
            await this.updateAccessToken();

            setInterval(() => {
                this.updateAccessToken();
            }, 175000); // update 5 second before expiration
        }
    }
}