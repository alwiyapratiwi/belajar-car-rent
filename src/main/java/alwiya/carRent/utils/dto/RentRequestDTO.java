package alwiya.carRent.utils.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentRequestDTO {

    Integer car_id;
    Integer user_id;
}
