package com.example.whatsaapp.repository;

import com.example.whatsaapp.entity.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findTop20ByChat_ChatIdOrderByCreatedAtDescMessageIdDesc(Long chatId);

    @Query("""
        SELECT m FROM Message m
        WHERE m.chat.chatId = :chatId
        AND (
           m.createdAt < :createdAt
           OR (m.createdAt = :createdAt AND m.messageId < :id)
        )
          ORDER BY m.createdAt DESC, m.messageId DESC
   """)
    List<Message> findNextMessages(@Param("chatId")  Long chatId,
                                   @Param("createdAt")LocalDateTime createdAt,
                                   @Param("id") Long id,
                                   Pageable pageable);

    @Query("""
        SELECT COUNT(m)
        FROM Message m
        WHERE m.chat.chatId = :chatId
        AND (:lastReadAt IS NULL OR m.createdAt > :lastReadAt)
        AND m.sender.userId != :userId
   """)
    long countUnreadMessages(@Param("lastReadAt") LocalDateTime lastReadAt,
                             @Param("chatId") Long chatId,
                             @Param("userId") Long userId);


    @Modifying
    @Transactional
    @Query("""
            UPDATE Message m
            SET m.messageStatus = 'DELIVERED'
            WHERE m.sender.userId != :userId
            AND m.messageStatus = 'SENT'
            AND m.chat.chatId IN (
                       SELECT cu.chat.chatId FROM ChatUser cu
                       WHERE cu.user.userId = :userId
            )
""")
    void markAllSentToDelivered(@Param("userId") Long userId);



    @Query("""
        SELECT m.messageId FROM Message m
        WHERE m.messageStatus = 'SENT'
        AND m.sender.userId != :userId
        AND m.chat.chatId IN (
                SELECT cu.chat.chatId FROM ChatUser cu
                WHERE cu.user.userId = :userId
            )
""")
    Set<Long> findAllSentMessageIdForUser(@Param("userId") Long userId);


    @Query("""
        SELECT DISTINCT m.messageId FROM Message m
        WHERE m.sender.userId != :userId
        AND m.messageStatus = 'SENT'
        OR m.messageStatus = null
        AND m.chat.chatId IN (
            SELECT cu.chat.chatId FROM ChatUser cu
            WHERE cu.user.userId = :userId
            )

""")
    Set<Long> findAllUserIdForSentMessage(@Param("userId") Long userId);


    @Query("""
        SELECT DISTINCT m.messageId FROM Message m
        WHERE m.sender.userId != :userId
        AND m.messageStatus = 'DELIVERED'
        AND m.chat.chatId = :chatId
""")
    Set<Long> findAllMessageIdDeliveredInChat(@Param("userId")Long userId,
                                              @Param("chatId") Long chatId);


    @Modifying
    @Transactional
    @Query("""
        UPDATE Message m
        SET m.messageStatus = 'SEEN'
        WHERE m.messageStatus = 'DELIVERED'
        AND m.sender.userId != :userId
        AND m.chat.chatId = :chatID
  """)
    void markAllMessageStatusDeliveredToSeen(@Param("userId") Long userId,
                                             @Param("chatId") Long chatId);

    @Query("""
            SELECT DISTINCT m.sender.userId
            FROM Message m
            WHERE m.chat.chatId = :chatId
            AND m.sender.userId != :userId
            AND m.messageStatus = 'DELIVERED'
            """)
    Set<Long> getSenderIdInMsg(@Param("userId")Long userId,@Param("chatId")Long chatId);

}
