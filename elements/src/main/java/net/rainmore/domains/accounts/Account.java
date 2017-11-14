package net.rainmore.domains.accounts;

import net.rainmore.domains.Model;
import net.rainmore.domains.common.addresses.Address;
import net.rainmore.domains.common.emails.Email;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "account")
@Table(name = "accounts")
public class Account implements Model {

    private Long id;
    private String firstName;
    private String lastName;
    private List<Address> addresses = new ArrayList<>();
    private List<Email> emails = new ArrayList<>();

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    @Column(name = "firstName", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public Account setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "lastName", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public Account setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @OneToMany(targetEntity = Address.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "accountsAddresses",
            joinColumns = @JoinColumn(name = "accountId"),
            inverseJoinColumns = @JoinColumn(name = "addressId"))
    public List<Address> getAddresses() {
        return addresses;
    }

    public Account setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    @OneToMany(targetEntity = Email.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "accountsEmails",
            joinColumns = @JoinColumn(name = "accountId"),
            inverseJoinColumns = @JoinColumn(name = "emailId"))
    public List<Email> getEmails() {
        return emails;
    }

    public Account setEmails(List<Email> emails) {
        this.emails = emails;
        return this;
    }
}
