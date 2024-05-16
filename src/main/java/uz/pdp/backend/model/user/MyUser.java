package uz.pdp.backend.model.user;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.backend.enums.UserState;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUser {
    private String username;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String state;
    private Long id;
    private String baseState;
    private String tempData;


}
