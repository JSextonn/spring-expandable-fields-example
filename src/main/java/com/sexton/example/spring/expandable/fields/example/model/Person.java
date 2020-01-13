package com.sexton.example.spring.expandable.fields.example.model;

import com.sexton.example.spring.expandable.fields.example.serialization.JsonExpandableField;

public class Person {
    private String firstName;
    private String lastName;

    @JsonExpandableField
    private ContactInformation contactInformation;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }
}
