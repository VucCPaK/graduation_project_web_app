package ua.kirilogrecha.backend.api.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Getter
@Setter
@Entry(
        base ="dc=user, dc=db",
        objectClasses = { "top", "person", "organizationalPerson", "inetOrgPerson" }
)
public class EPerson {
    @Id
    private Name id; // login
    private @Attribute(name = "cn") String firstName;
    private @Attribute(name = "sn") String lastName;
    private @Attribute(name = "userPassword") String password;
    private @Attribute(name = "phone") String phone;
    private @Attribute(name = "mail") String mail;

    public EPerson() {
    }
}
