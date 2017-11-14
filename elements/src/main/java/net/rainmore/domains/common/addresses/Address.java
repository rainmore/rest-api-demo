package net.rainmore.domains.common.addresses;

import net.rainmore.domains.Model;

import javax.persistence.*;

@Entity(name = "common.Address")
@Table(name = "addresses")
public class Address implements Model {

    private Long   id;
    private Type   type;
    private String address1;
    private String address2;
    private String postcode;
    private String suburb;
    private String state;
    // TODO country could be defined in a separate country table
    private String country;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public Address setId(Long id) {
        this.id = id;
        return this;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public Type getType() {
        return type;
    }

    public Address setType(Type type) {
        this.type = type;
        return this;
    }

    @Column(name = "address1", nullable = false)
    public String getAddress1() {
        return address1;
    }

    public Address setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    @Column(name = "address2")
    public String getAddress2() {
        return address2;
    }

    public Address setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    @Column(name = "postcode")
    public String getPostcode() {
        return postcode;
    }

    public Address setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    @Column(name = "suburb")
    public String getSuburb() {
        return suburb;
    }

    public Address setSuburb(String suburb) {
        this.suburb = suburb;
        return this;
    }

    @Column(name = "state")
    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }
}
