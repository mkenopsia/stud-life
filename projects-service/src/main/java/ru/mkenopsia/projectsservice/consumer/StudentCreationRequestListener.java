package ru.mkenopsia.projectsservice.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.mkenopsia.common.dto.StudentInfoDto;
import ru.mkenopsia.projectsservice.service.StudentService;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentCreationRequestListener {

    private final StudentService studentService;

    @KafkaListener(topics = "${kafka.student-creation-topic}")
    public void listen(StudentInfoDto studentInfoDto) {
        log.info("Получено сообщение из kafka: {}", studentInfoDto);
//        studentService.save(studentInfoDto);
    }
}
