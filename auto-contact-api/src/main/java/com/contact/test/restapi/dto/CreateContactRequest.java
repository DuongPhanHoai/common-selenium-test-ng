package com.contact.test.restapi.dto;

import com.david.test.core.base.BaseDto;

import lombok.Builder;

@Builder
public class CreateContactRequest extends BaseDto {
    String firstName;
    String lastName;
    String birthdate;
    String email;
    String phone;
    String street1;
    String street2;
    String city;
    String stateProvince;
    String postalCode;
    String country;
}
