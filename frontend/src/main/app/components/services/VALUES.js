export default class VALUES {
    // add PROXY_ADDRESS_FORWARDING=true (docker)
    static KEYCLOAK_DOMAIN = "http://localhost:8080";
    static CLIENT_ID = "my_client";
    static REALM_ID = "my_realm";
    static REDIRECT_URI = "http://localhost:8082";
    static GATEWAY = "http://localhost:8086";
}