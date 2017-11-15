package net.rainmore.domains.accounts;

import net.rainmore.domains.Model;
import net.rainmore.domains.common.addresses.Address;
import net.rainmore.domains.common.emails.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "account")
@Table(name = "accounts")
public class Account implements Model {

    public static final int FIRST_NAME_MAX_LENGTH = 200;
    public static final int LAST_NAME_MAX_LENGTH = 200;

    // TODO apart from ID, UUID should be introduced to support API
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
    @Size(max = FIRST_NAME_MAX_LENGTH)
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public Account setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "lastName", nullable = false)
    @Size(max = LAST_NAME_MAX_LENGTH)
    @NotNull
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
