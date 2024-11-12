package iuh.week02_lab_huynhhoangphuc_21036541.backend.enums;

public enum ProductStatus {
    ACTIVE( 1),
    IN_ACTIVE( 0),
    TERMINATED( -1);

    private  int value;

    ProductStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}