package br.senac.spring.authentication.account;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "User")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fullName")
    @NotNull
    @NotEmpty
    private String fullName;

    @Column(name = "userName")
    @NotNull
    @NotEmpty
    private String userName;

    @Column(name = "password")
    @NotNull
    @NotEmpty
    private String password;
}
