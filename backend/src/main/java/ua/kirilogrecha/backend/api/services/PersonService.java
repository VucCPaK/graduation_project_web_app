package ua.kirilogrecha.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.DPerson;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.LdapName;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class PersonService {
    private final LdapTemplate ldapTemplate;

    @Autowired
    public PersonService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public boolean isPresentInDirectory(String login) {
        return ldapTemplate.lookup(buildDn(login)) != null;
    }

    public String getUidByLogin(String login) {
        return ldapTemplate.searchForObject(query().where("login").is(login),
                new DPersonUidContextMapper());
    }

    public void createPerson(DPerson dPerson) {
        ldapTemplate.bind(
                buildDn(dPerson.getLogin()),
                null,
                buildAttributes(dPerson)
        );

        addPersonToSpecificGroup(dPerson);
    }

    private void addPersonToSpecificGroup(DPerson dPerson) {
        Name dn = buildDn(dPerson.getId());

        ldapTemplate.lookupContext(
                // get DirContextOperations for add attribute
                LdapNameBuilder.newInstance()
                        .add("ou", "groups")
                        .add("cn", Group.CUSTOMERS.toString().toLowerCase())
                        .build()
        ).addAttributeValue("uniqueMember", dn);
    }

//
//    public List<DPerson> getAllPersons() {
//        return ldapTemplate.search(query().where("objectClass").is("person"), new PersonAttributesMapper());
//    }
//
//    public List<String> getAllPersonNames() {
//        return ldapTemplate.search(query().where("objectClass").is("person"),
//                new PersonNameAttributesMapper());
//    }
//
//    public DPerson getPersonNamesByUid(String id) {
//        List<DPerson> people = ldapTemplate.search(query().where("uid").is(id), new PersonAttributesMapper());
//        return ((null != people && !people.isEmpty()) ? people.get(0) : null);
//    }

//    private static class PersonAttributesMapper implements AttributesMapper<DPerson> {
//        @Override
//        public DPerson mapFromAttributes(Attributes attrs) throws NamingException {
//            DPerson DPerson = new DPerson();
//            DPerson.setId(null != attrs.get("uid") ? (String) attrs.get("uid").get() : null);
//            DPerson.setFirstName((String) attrs.get("cn").get());
//            DPerson.setLastName((String) attrs.get("sn").get());
//            DPerson.setPassword(null != attrs.get("password") ? (String) attrs.get("password").get() : null);
//            DPerson.setPhone(null != attrs.get("phone") ? (String) attrs.get("phone").get() : null);
//            DPerson.setMail(null != attrs.get("mail") ? (String) attrs.get("mail").get() : null);
//            return DPerson;
//        }
//    }
//
    private static class DPersonUidContextMapper implements ContextMapper<String> {
    @Override
    public String mapFromContext(Object ctx) throws NamingException {
        return ((DirContextAdapter) ctx).getAttributes().get("uid").get().toString();
    }

//    @Override
//        public String mapFromAttributes(Attributes attrs) throws NamingException {
//            return attrs.get("uid").get().toString();
//        }
    }

    private LdapName buildDn(String login) {
        return LdapNameBuilder.newInstance()
                .add("ou", "users")
                .add("login", login)
                .build();
    }

    private Attributes buildAttributes(DPerson dPerson) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocattr = new BasicAttribute("objectClass");
        ocattr.add("top");
        ocattr.add("person");
        ocattr.add("organizationalPerson");
        ocattr.add("inetOrgPerson");
        attrs.put(ocattr);
        attrs.put("cn", dPerson.getFirstName());
        attrs.put("sn", dPerson.getLastName());
        attrs.put("uid", dPerson.getId());
        attrs.put("login", dPerson.getLogin());
        attrs.put("userPassword", new BCryptPasswordEncoder().encode(dPerson.getPassword())); // do encrypt
        attrs.put("phone", dPerson.getPhone());
        attrs.put("mail", dPerson.getMail());
        return attrs;
    }

    private enum Group {
        CUSTOMERS,
        SUPPLIERS,
        STOREKEEPERS,
        ADMINS
    }
}
