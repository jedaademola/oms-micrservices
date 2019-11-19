package com.wawa.oms.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Address {
    @Id
    private Long id;
    private String addressId;
    private String streetName;
    private String zipCode;
    private String city;
    private String countryName;
}
