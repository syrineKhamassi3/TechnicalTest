package com.demo.OfferTechnicalTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_entity")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message = "userName should not be null")
    private String userName;

    @Column
    @NotNull(message = "birthdate should not be null")
    private LocalDate birthDate;

    @Column
    @NotNull(message = "country should not be null")
    private String country;

    @Column
    private String phoneNumber;

    @Column
    private String gender;

    @Column(name = "created_on", nullable = false)
    @CreationTimestamp
    private LocalDate createdOn;

    @Column(name = "updated_on", nullable = false)
    @UpdateTimestamp
    private LocalDate updatedOn;

}
