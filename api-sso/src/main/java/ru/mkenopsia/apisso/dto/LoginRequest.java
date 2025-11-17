package ru.mkenopsia.apisso.dto;

public record LoginRequest(String identifier,
                           String password) {
}
