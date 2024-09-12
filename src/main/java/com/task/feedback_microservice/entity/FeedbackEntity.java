package com.task.feedback_microservice.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="feedback")
@Data
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long propertyId;
    private String comments;

}
