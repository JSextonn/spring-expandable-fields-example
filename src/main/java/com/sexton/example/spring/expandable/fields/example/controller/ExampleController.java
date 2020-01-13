package com.sexton.example.spring.expandable.fields.example.controller;

import com.sexton.example.spring.expandable.fields.example.model.Address;
import com.sexton.example.spring.expandable.fields.example.model.ContactInformation;
import com.sexton.example.spring.expandable.fields.example.model.Person;
import com.sexton.example.spring.expandable.fields.example.model.Phone;
import com.sexton.example.spring.expandable.fields.example.model.PhoneType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class ExampleController {
    @GetMapping
    public ResponseEntity<Person> getPerson() {
        final Person person = createExamplePerson();
        return ResponseEntity.ok(person);
    }

    private Person createExamplePerson() {
        final Address address = new Address();
        address.setStreet("1234 Street Drive");
        address.setCity("City");
        address.setState("State");
        address.setZip("12345");

        final Phone phone = new Phone();
        phone.setPhoneNumber("1234567890");
        phone.setPhoneType(PhoneType.CELL);

        final ContactInformation contactInformation = new ContactInformation();
        contactInformation.setAddress(address);
        contactInformation.setPhone(phone);

        final Person person = new Person();
        person.setFirstName("Justin");
        person.setLastName("Sexton");
        person.setContactInformation(contactInformation);

        return person;
    }
}
