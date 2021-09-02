package ua.kirilogrecha.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class DPerson {
    private String id;
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String mail;

    public DPerson() {
        this.id = UUID.randomUUID().toString();
    }
}
