package com.project.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // 'users'는 DB 예약어라 안전하게 'users'
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_USER;  // 기본값은 일반 사용자

}
