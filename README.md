# Spring Expandable Fields Example
Sometimes it will be beneficial to not allow your API consumers to "drink from the fire hose of data". Expandable entity fields can help.

## In Action
### The Models
Some example domain models we will use for demonstration purposes
#### Person
```java
public class Person {
    private String firstName;
    private String lastName;
    
    @JsonExpandableField
    private ContactInformation contactInformation;
}
```

#### Contact Information
```java
public class ContactInformation {
    @JsonExpandableField
    private Address address;
    
    @JsonExpandableField
    private Phone phone;
}
```

#### Address
```java
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
}
```

#### Phone
```java
public class Phone {
    private String phoneNumber;
    private PhoneType phoneType;
}
```

#### Phone Type
```java
public enum PhoneType {
    HOME, CELL, WORK
}
```

### The Requests

#### localhost:8080/people
```json
{
    "firstName": "Justin",
    "lastName": "Sexton",
    "contactInformation": {}
}
```

#### localhost:8080/people?expand=contactinformation
```json
{
    "firstName": "Justin",
    "lastName": "Sexton",
    "contactInformation": {
        "address": {},
        "phone": {}
    }
}
```

#### localhost:8080/people?expand=contactinformation.address
```json
{
    "firstName": "Justin",
    "lastName": "Sexton",
    "contactInformation": {
        "address": {
            "street": "1234 Street Drive",
            "city": "City",
            "state": "State",
            "zip": "12345"
        },
        "phone": {}
    }
}
```

#### localhost:8080/people?expand=contactinformation.address,contactinformation.phone
```json
{
    "firstName": "Justin",
    "lastName": "Sexton",
    "contactInformation": {
        "address": {
            "street": "1234 Street Drive",
            "city": "City",
            "state": "State",
            "zip": "12345"
        },
        "phone": {
            "phoneNumber": "1234567890",
            "phoneType": "CELL"
        }
    }
}
```
