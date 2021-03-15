package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

//내장 타입
@Embeddable
@Getter
//값 타입은 변경되면 안되므로, Setter를 열어두지 않는다. (변경불가)
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){}

    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
