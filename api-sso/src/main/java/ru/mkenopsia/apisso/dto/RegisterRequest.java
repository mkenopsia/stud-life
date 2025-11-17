package ru.mkenopsia.apisso.dto;

public record RegisterRequest(String username,
                              String email,
                              String password) {
}
