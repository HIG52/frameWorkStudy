package org.example.frameworkstudy.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.frameworkstudy.cmmnEntity.Timestamped;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users extends Timestamped {
    @Id
    @Column(name = "USERID", nullable = false, length = 50)
    private String userid;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

}


