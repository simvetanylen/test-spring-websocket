package ovir.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Length(min = 3)
    private String login;

    @NotNull
    @Length(min = 6)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Pants> pantsList;
}
