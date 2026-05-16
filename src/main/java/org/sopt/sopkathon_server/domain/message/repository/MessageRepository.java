package org.sopt.sopkathon_server.domain.message.repository;

import org.sopt.sopkathon_server.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
