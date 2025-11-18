package ru.mkenopsia.apisso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mkenopsia.apisso.dto.RegisterRequest;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final StudentService studentService;

    public void registerUser(RegisterRequest request) {
        String password = encoder.encode(request.password());
        this.userService.save(request.username(), request.email(), password);
        this.studentService.produceSaveMessage(request.username(),
                request.email(),
                request.fio());
    }
}
