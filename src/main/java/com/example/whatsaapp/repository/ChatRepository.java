package com.example.whatsaapp.repository;

import com.example.whatsaapp.entity.Chat;
import com.example.whatsaapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
    Optional<Chat> findByChatKey(String chatKey);
    List<Chat> findByUsers(User user);
}
