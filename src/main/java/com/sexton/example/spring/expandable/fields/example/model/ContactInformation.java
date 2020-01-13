package com.sexton.example.spring.expandable.fields.example.model;

import com.sexton.example.spring.expandable.fields.example.serialization.JsonExpandableField;

public class ContactInformation {
    @JsonExpandableField
    private Address address;

    @JsonExpandableField
    private Phone phone;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
