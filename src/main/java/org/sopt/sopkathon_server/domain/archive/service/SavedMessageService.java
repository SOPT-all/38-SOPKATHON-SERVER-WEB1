package org.sopt.sopkathon_server.domain.archive.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sopkathon_server.domain.archive.dto.response.SavedMessageResponse;
import org.sopt.sopkathon_server.domain.archive.entity.SavedMessage;
import org.sopt.sopkathon_server.domain.archive.repository.SavedMessageRepository;
import org.sopt.sopkathon_server.domain.message.entity.Message;
import org.sopt.sopkathon_server.domain.message.repository.MessageRepository;
import org.sopt.sopkathon_server.global.exception.BaseException;
import org.sopt.sopkathon_server.global.response.error.ErrorCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavedMessageService {

    private final SavedMessageRepository savedMessageRepository;
    private final MessageRepository messageRepository;

    // password 해시 하기 위한 의존성
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public Long createSavedMessage(Long messageId, String password) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new BaseException(ErrorCode.MESSAGE_NOT_FOUND));

        String passwordHash = passwordEncoder.encode(password);

        SavedMessage savedMessage = SavedMessage.builder()
                .senderInitial(message.getSenderInitial())
                .receiverInitial(message.getReceiverInitial())
                .passwordHash(passwordHash)
                .message(message)
                .build();

        SavedMessage saved = savedMessageRepository.save(savedMessage);

        return saved.getId();
    }

    // 아카이브 전체 조회
    @Transactional(readOnly = true)
    public List<SavedMessageResponse> getAllSavedMessages() {
        return savedMessageRepository.findAll()
                .stream()
                .map(SavedMessageResponse::from)
                .toList();
    }

    // 아카이브 단건 조회
    @Transactional(readOnly = true)
    public SavedMessageResponse getSavedMessage(Long savedMessageId, String password) {
        SavedMessage savedMessage = savedMessageRepository.findById(savedMessageId)
                .orElseThrow(() -> new BaseException(ErrorCode.SAVED_MESSAGE_NOT_FOUND));

        if (!passwordEncoder.matches(password, savedMessage.getPasswordHash())) {
            throw new BaseException(ErrorCode.INVALID_PASSWORD);
        }

        return SavedMessageResponse.from(savedMessage);
    }

    // 저장된 메시지 삭제
    @Transactional
    public void deleteSavedMessage(Long savedMessageId, String password) {
        SavedMessage savedMessage = savedMessageRepository.findById(savedMessageId)
                .orElseThrow(() -> new BaseException(ErrorCode.SAVED_MESSAGE_NOT_FOUND));

        if(!passwordEncoder.matches(password, savedMessage.getPasswordHash())) {
            throw new BaseException(ErrorCode.INVALID_PASSWORD);
        }

        savedMessageRepository.delete(savedMessage);
    }
}
