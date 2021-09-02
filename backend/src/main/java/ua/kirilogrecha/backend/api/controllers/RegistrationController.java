package ua.kirilogrecha.backend.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.kirilogrecha.backend.api.dto.DPerson;
import ua.kirilogrecha.backend.api.services.PersonService;


@RestController
public class RegistrationController {
    private final PersonService personService;

    @Autowired
    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/api/user/registration")
    public void registration(@RequestBody DPerson dPerson) {
        personService.createPerson(dPerson);
    }

    @GetMapping("/api/user/uid/{login}")
    public String getUidByLogin(@PathVariable("login") String login) {
        return personService.getUidByLogin(login);
    }

    @GetMapping("/api/user/examination/{login}")
    public boolean isPresentInDirectory(@PathVariable("login") String login) {
        return personService.isPresentInDirectory(login);
    }
}
