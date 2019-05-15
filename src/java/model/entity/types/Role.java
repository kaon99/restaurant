package model.entity.types;

public enum Role {

    UNREGISTERED(1),
    CLIENT(2),
    ADMIN(3);

    private int role;

    Role(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }
}
