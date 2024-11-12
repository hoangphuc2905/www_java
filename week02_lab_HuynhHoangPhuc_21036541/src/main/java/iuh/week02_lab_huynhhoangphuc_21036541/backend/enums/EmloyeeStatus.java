package iuh.week02_lab_huynhhoangphuc_21036541.backend.enums;

public enum EmloyeeStatus {
    ACTIVE((byte) 1),
    REST((byte) 0),
    QUIT((byte) -1);

    private final byte value;

    EmloyeeStatus(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

}
