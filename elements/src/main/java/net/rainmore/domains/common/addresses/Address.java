package net.rainmore.domains.common.addresses;

import net.rainmore.domains.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "common.Address")
@Table(name = "addresses")
public class Address implements Model {

    public static final int ADDRESS_MAX_LENGTH  = 200;
    public static final int POSTCODE_MAX_LENGTH = 200;
    public static final int SUBURB_MAX_LENGTH   = 100;
    public static final int STATE_MAX_LENGTH    = 50;
    public static final int COUNTRY_MAX_LENGTH  = 100;

    // TODO apart from ID, UUID should be introduced to support API
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
    @NotNull
    public Type getType() {
        return type;
    }

    public Address setType(Type type) {
        this.type = type;
        return this;
    }

    @Column(name = "address1", nullable = false)
    @Size(max = ADDRESS_MAX_LENGTH)
    @NotNull
    public String getAddress1() {
        return address1;
    }

    public Address setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    @Column(name = "address2")
    @Size(max = ADDRESS_MAX_LENGTH)
    public String getAddress2() {
        return address2;
    }

    public Address setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    @Column(name = "postcode")
    @Size(max = POSTCODE_MAX_LENGTH)
    public String getPostcode() {
        return postcode;
    }

    public Address setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    @Column(name = "suburb")
    @Size(max = SUBURB_MAX_LENGTH)
    public String getSuburb() {
        return suburb;
    }

    public Address setSuburb(String suburb) {
        this.suburb = suburb;
        return this;
    }

    @Column(name = "state")
    @Size(max = STATE_MAX_LENGTH)
    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    @Column(name = "country")
    @Size(max = COUNTRY_MAX_LENGTH)
    public String getCountry() {
        return country;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }
}
