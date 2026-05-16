package org.sopt.sopkathon_server.domain.message.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.sopkathon_server.global.entity.BaseTimeEntity;

@Entity
@Table(name = "message")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String senderInitial;

    @Column(nullable = false)
    private String receiverInitial;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Message parent;

    @Builder
    private Message(String senderInitial, String receiverInitial, String content, Message parent) {
        this.senderInitial = senderInitial;
        this.receiverInitial = receiverInitial;
        this.content = content;
        this.parent = parent;
    }
}