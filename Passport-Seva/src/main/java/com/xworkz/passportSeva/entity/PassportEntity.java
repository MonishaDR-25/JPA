package com.xworkz.passportSeva.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "passport_info")
@NamedQuery(name = "fetchEmail",query = "select a.emailId from PassportEntity a where a.emailId=:emailId")
public class PassportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="passport_id")
    private Integer passportId;

    @Column(name="passport_office")
    private String passportOffice;

    @Column(name="given_name")
    private String givenName;

    @Column(name="surname")
    private String surname;

    @Column(name="date_of_birth")
    private String dateOfBirth;

    @Column(name="email_id")
    private String emailId;

    @Column(name="login_id")
    private String loginId;

    private String password;

    @Column(name="confirm_password")
    private String confirmPassword;

    @Column(name="hint_question")
    private String hintQuestion;

    @Column(name="hint_answer")
    private String hintAnswer;

    @Column(name="phone_number")
    private Long phoneNumber;
}
