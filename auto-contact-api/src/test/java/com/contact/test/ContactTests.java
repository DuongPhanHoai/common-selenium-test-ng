package com.contact.test;

import org.testng.annotations.Test;

import com.contact.test.restapi.Contact;
import com.contact.test.restapi.dto.CreateContactRequest;
import com.google.gson.JsonObject;

public class ContactTests extends BaseContactApiTest {

    CreateContactRequest newContactInfo =
            CreateContactRequest.builder()
                    .firstName("Hello")
                    .lastName("World")
                    .birthdate("1970-01-01")
                    .email("tester@qa.test")
                    .phone("0978773922")
                    .street1("At the Stress 1 coner")
                    .street2("Tester road")
                    .city("Quality")
                    .stateProvince("QC")
                    .postalCode("345678")
                    .country("CHAU")
                    .build();

    @Test
    public void createNewContact() throws Exception {
        // Declare API usage
        Contact contact = new Contact(getSpecification());

        JsonObject res = contact.create(newContactInfo);

        int i = 123;
    }
}
