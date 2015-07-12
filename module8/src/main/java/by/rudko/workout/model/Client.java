package by.rudko.workout.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Client extends AbstractEntity{

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private PersonalData personalData;

}
