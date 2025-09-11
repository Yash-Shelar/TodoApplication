package com.project.TodoApplication.enums;

public enum TodoStatus {
    CREATED,
    IN_PROGRESS,
    COMPLETED;

    public static TodoStatus fromInteger(int i) {
        return switch(i) {
            case 0 -> CREATED;
            case 1 -> IN_PROGRESS;
            case 2 -> COMPLETED;
            default -> throw new IllegalArgumentException("invalid int value for todo status.");
        };
    }
}
