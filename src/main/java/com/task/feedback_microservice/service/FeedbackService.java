package com.task.feedback_microservice.service;


import com.task.feedback_microservice.dto.FeedbackDTO;
import com.task.feedback_microservice.entity.FeedbackEntity;
import com.task.feedback_microservice.mapper.FeedbackMapper;
import com.task.feedback_microservice.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackMapper feedbackMapper;

    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        FeedbackEntity feedback = feedbackMapper.toEntity(feedbackDTO);
        FeedbackEntity savedFeedback = feedbackRepository.save(feedback);
        return feedbackMapper.toDTO(savedFeedback);
    }

    public FeedbackDTO getFeedbackById(Long id) {
        FeedbackEntity feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
        return feedbackMapper.toDTO(feedback);
    }

    public List<FeedbackDTO> getFeedbackByPropertyId(Long propertyId) {
        List<FeedbackEntity> feedbacks = feedbackRepository.findByPropertyId(propertyId);
        List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
        for (FeedbackEntity feedback : feedbacks) {
            feedbackDTOs.add(feedbackMapper.toDTO(feedback));
        }
        return feedbackDTOs;
    }

    public List<FeedbackDTO> getAllFeedbacks() {
        List<FeedbackEntity> feedbacks = feedbackRepository.findAll();
        List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
        for (FeedbackEntity feedback : feedbacks) {
            feedbackDTOs.add(feedbackMapper.toDTO(feedback));
        }
        return feedbackDTOs;
    }

    public FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO) {
        FeedbackEntity existingFeedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
        existingFeedback.setPropertyId(feedbackDTO.getPropertyId());
        existingFeedback.setComments(feedbackDTO.getComments());
        FeedbackEntity updatedFeedback = feedbackRepository.save(existingFeedback);
        return feedbackMapper.toDTO(updatedFeedback);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
