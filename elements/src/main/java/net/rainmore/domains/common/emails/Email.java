package net.rainmore.domains.common.emails;

import net.rainmore.domains.Model;

import javax.persistence.*;

@Entity(name = "common.Email")
@Table(name = "emails")
public class Email implements Model {

    private Long   id;
    private Type   type;
    private String name;

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
    public Type getType() {
        return type;
    }

    public Email setType(Type type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Email setName(String name) {
        this.name = name;
        return this;
    }
}
