package by.rudko.workout.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@MappedSuperclass
@Getter
@ToString
@EqualsAndHashCode
public abstract class AbstractEntity{
	
	@Id
    @GeneratedValue
    private final @JsonIgnore Long id;

    protected AbstractEntity() {
        this.id = null;
    }
}
