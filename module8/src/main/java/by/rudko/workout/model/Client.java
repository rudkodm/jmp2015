package by.rudko.workout.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "CLIENTS")
public class Client extends AbstractEntity{

	@Embedded
    private PersonalData personalData;
	
	@Enumerated(EnumType.STRING)
	private TrainingGoalType trainingGoal;
    
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

}
