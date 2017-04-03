package ovir.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Pants {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Length(min = 3)
    private String color;

    private String description;

    @ManyToOne
    @NotNull
    private User user;
}
