package com.example.whatsaapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;

    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<ChatUser> chats;

    @JsonIgnore
    @OneToMany(mappedBy = "sender",fetch = FetchType.LAZY)
    private List<Message> sentMessages;








}
