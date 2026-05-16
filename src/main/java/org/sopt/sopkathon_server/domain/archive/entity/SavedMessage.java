package org.sopt.sopkathon_server.domain.archive.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.sopkathon_server.domain.message.entity.Message;
import org.sopt.sopkathon_server.global.entity.BaseTimeEntity;

@Entity
@Table(name = "saved_message")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavedMessage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String senderInitial;

    @Column(nullable = false)
    private String receiverInitial;

    @Column(nullable = false)
    private String passwordHash;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", unique = true)
    private Message message;

    @Builder
    private SavedMessage(String senderInitial, String receiverInitial, String passwordHash, Message message) {
        this.senderInitial = senderInitial;
        this.receiverInitial = receiverInitial;
        this.passwordHash = passwordHash;
        this.message = message;
    }
}