package models.modelsForLombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class CustomerBuilder {
    String firstName;
    String lastName;
    String zipPostalCode;
}
