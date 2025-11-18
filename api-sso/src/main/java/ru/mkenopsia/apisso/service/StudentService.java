package ru.mkenopsia.apisso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mkenopsia.common.dto.StudentInfoDto;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.student-creation-topic}")
    private String STUDENT_CREATION_TOPIC;

    public void produceSaveMessage(String username, String email, String fio) {
        StudentInfoDto infoDto = StudentInfoDto.builder()
                .username(username)
                .email(email)
                .fio(fio)
                .build();

        kafkaTemplate.send(STUDENT_CREATION_TOPIC, infoDto);
    }
}
