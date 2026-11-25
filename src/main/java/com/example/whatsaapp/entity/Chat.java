package com.example.whatsaapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;


    @OneToMany(mappedBy = "chat")
    private List<ChatUser> users;

    @OneToMany(mappedBy = "chat",cascade = CascadeType.ALL)
    private List<Message> messages;

    @Column(unique = true,nullable = false)
    private String chatKey;

    private LocalDateTime lastMessageAt;

    private String lastMessage;

    private LocalDateTime createdAt;
}
