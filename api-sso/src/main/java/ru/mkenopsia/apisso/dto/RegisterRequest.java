package ru.mkenopsia.apisso.dto;

public record RegisterRequest(String username,
                              String fio,
                              String email,
                              String password) {
}
