package ua.kirilogrecha.backend.api.repositories;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;
import ua.kirilogrecha.backend.api.entities.EPerson;

@Repository
public interface PersonRepository extends LdapRepository<EPerson> {
}
