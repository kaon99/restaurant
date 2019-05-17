package model.entity.types;

public enum Status {
    UNPAID(0),
    PAID(1);
    private int status;

    Status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


}
