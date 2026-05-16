package org.sopt.sopkathon_server.domain.archive.repository;

import org.sopt.sopkathon_server.domain.archive.entity.SavedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedMessageRepository extends JpaRepository<SavedMessage, Long> {
}
