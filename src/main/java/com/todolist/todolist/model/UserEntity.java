package com.todolist.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "public")
public class UserEntity extends RevisableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_sequence_generator")
    @SequenceGenerator(
        name = "user_sequence_generator",
        sequenceName = "user_id_sequence",
        allocationSize = 1
    )
    @Column(name = "id")
    private Integer id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password_hash", nullable = false)
    private String password;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_person", referencedColumnName = "id")
    private PersonEntity person;
    @Column(name = "token")
    private String token;
    @Column(name = "token_expiration_date")
    private LocalDateTime tokenExpirationDate;
    @Column(name = "account_expiration_date")
    private LocalDate accountExpirationDate;
    @Column(name = "locked", nullable = false)
    private Boolean locked;
    @Column(name = "unauthorized_access_attempts", nullable = false)
    private Integer unauthorizedAccessAttempts;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "many_user_has_many_role",
        joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false))
    private Set<RoleEntity> roles;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && email.equals(that.email) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
