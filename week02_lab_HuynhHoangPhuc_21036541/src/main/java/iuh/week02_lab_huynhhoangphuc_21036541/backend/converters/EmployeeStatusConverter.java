package iuh.week02_lab_huynhhoangphuc_21036541.backend.converters;

import iuh.week02_lab_huynhhoangphuc_21036541.backend.enums.EmloyeeStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class EmployeeStatusConverter implements AttributeConverter<EmloyeeStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmloyeeStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return (int) attribute.getValue();
    }

    @Override
    public EmloyeeStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(EmloyeeStatus.values())
                .filter(c -> c.getValue() == dbData.byteValue())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}