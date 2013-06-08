package org.example.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.example.utils.StringUtilities;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "CarStorage", uniqueConstraints = @UniqueConstraint(columnNames = "numberFrame"))
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    public Car() {
    }

    public Car(String numberFrame, String model, String color) {
        this.model = model;
        this.color = color;
        this.numberFrame = numberFrame;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    private String model;

    @NotNull
    @NotEmpty
    private String color;

    @NotNull
    @NotEmpty
    private String numberFrame;

    public String getNumberFrame() {
        return numberFrame;
    }

    public void setNumberFrame(String numberFrame) {
        this.numberFrame = numberFrame;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return StringUtilities.concatStrings(super.toString(), " [ id=", id, " model=", model, " color=", color,
                " numberFrame=", numberFrame, " ]");
    }
}
