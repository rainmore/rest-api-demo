package net.rainmore.domains.common.emails;

import net.rainmore.domains.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "common.Email")
@Table(name = "emails")
public class Email implements Model {

    public static final int EMAIL_MAX_LENGTH  = 255;

    // TODO apart from ID, UUID should be introduced to support API
    private Long   id;
    private Type   type;
    private String email;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public Email setId(Long id) {
        this.id = id;
        return this;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    public Type getType() {
        return type;
    }

    public Email setType(Type type) {
        this.type = type;
        return this;
    }

    @Column(name = "email")
    @Size(max = EMAIL_MAX_LENGTH)
    @NotNull
    @org.hibernate.validator.constraints.Email
    public String getEmail() {
        return email;
    }

    public Email setEmail(String email) {
        this.email = email;
        return this;
    }
}
