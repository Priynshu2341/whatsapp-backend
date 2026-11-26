package com.example.whatsaapp.repository;

import com.example.whatsaapp.entity.Chat;
import com.example.whatsaapp.entity.ChatUser;
import com.example.whatsaapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatUserRepository extends JpaRepository<ChatUser,Long> {

    Optional<ChatUser> findByChatAndUser(Chat chat, User user);
    List<ChatUser> findByUser(User user);


    @Query("""
SELECT cu FROM ChatUser cu
WHERE cu.user = :user
ORDER BY cu.chat.lastMessageAt DESC""")
    List<ChatUser> findByUserOrderByLastMessage(@Param("user")  User user );
}

