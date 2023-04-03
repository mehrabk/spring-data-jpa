package com.mehrab.springdatajpa.model.user;

public enum RoleName {
    ROLE_STUDENT(1),
    ROLE_TEACHER(2),
    ROLE_MODERATOR(3),
    ROLE_ADMIN(4);

    private final int id;

    RoleName(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }

    public static RoleName getType(Integer id) {
        if (id == null) return null;

        for (RoleName role: RoleName.values()) {
            if(id.equals(role.getValue())) {
                return role;
            }
        }
        throw new IllegalArgumentException("No matching type for id" + id);
    }






}
