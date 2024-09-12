package com.task.feedback_microservice.mapper;
import com.task.feedback_microservice.dto.FeedbackDTO;
import com.task.feedback_microservice.entity.FeedbackEntity;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {

    public FeedbackDTO toDTO(FeedbackEntity feedback) {
        if (feedback == null) {
            return null;
        }
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(feedback.getId());
        dto.setPropertyId(feedback.getPropertyId());
        dto.setComments(feedback.getComments());
        return dto;
    }

    public FeedbackEntity toEntity(FeedbackDTO feedbackDTO) {
        if (feedbackDTO == null) {
            return null;
        }
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setId(feedbackDTO.getId());
        feedback.setPropertyId(feedbackDTO.getPropertyId());
        feedback.setComments(feedbackDTO.getComments());
        return feedback;
    }
}
