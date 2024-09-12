package com.task.feedback_microservice;

import com.task.feedback_microservice.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import com.task.feedback_microservice.dto.FeedbackDTO;
import com.task.feedback_microservice.entity.FeedbackEntity;
import com.task.feedback_microservice.mapper.FeedbackMapper;
import com.task.feedback_microservice.repository.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FeedbackMicroserviceApplicationTests {

	@Mock
	private FeedbackRepository feedbackRepository;

	@Mock
	private FeedbackMapper feedbackMapper;

	@InjectMocks
	private FeedbackService feedbackService;

//	public FeedbackServiceTest() {
//		MockitoAnnotations.openMocks(this);
//	}

	@Test
	public void testCreateFeedback() {
		FeedbackDTO feedbackDTO = new FeedbackDTO();
		FeedbackEntity feedbackEntity = new FeedbackEntity();

		when(feedbackMapper.toEntity(feedbackDTO)).thenReturn(feedbackEntity);
		when(feedbackRepository.save(feedbackEntity)).thenReturn(feedbackEntity);
		when(feedbackMapper.toDTO(feedbackEntity)).thenReturn(feedbackDTO);

		FeedbackDTO result = feedbackService.createFeedback(feedbackDTO);

		assertNotNull(result);
		verify(feedbackMapper).toEntity(feedbackDTO);
		verify(feedbackRepository).save(feedbackEntity);
		verify(feedbackMapper).toDTO(feedbackEntity);
	}

	@Test
	public void testGetFeedbackById() {
		Long id = 1L;
		FeedbackDTO feedbackDTO = new FeedbackDTO();
		FeedbackEntity feedbackEntity = new FeedbackEntity();

		when(feedbackRepository.findById(id)).thenReturn(Optional.of(feedbackEntity));
		when(feedbackMapper.toDTO(feedbackEntity)).thenReturn(feedbackDTO);

		FeedbackDTO result = feedbackService.getFeedbackById(id);

		assertNotNull(result);
		verify(feedbackRepository).findById(id);
		verify(feedbackMapper).toDTO(feedbackEntity);
	}

	@Test
	public void testGetFeedbackByIdNotFound() {
		Long id = 1L;

		when(feedbackRepository.findById(id)).thenReturn(Optional.empty());

		RuntimeException thrown = assertThrows(RuntimeException.class, () -> feedbackService.getFeedbackById(id));

		assertEquals("Feedback not found", thrown.getMessage());
	}
	@Test
	public void testUpdateFeedback() {
		Long id = 1L;
		FeedbackDTO feedbackDTO = new FeedbackDTO();
		FeedbackEntity existingFeedback = new FeedbackEntity();
		FeedbackEntity updatedFeedback = new FeedbackEntity();

		when(feedbackRepository.findById(id)).thenReturn(Optional.of(existingFeedback));
		when(feedbackMapper.toDTO(updatedFeedback)).thenReturn(feedbackDTO);
		when(feedbackRepository.save(existingFeedback)).thenReturn(updatedFeedback);

		FeedbackDTO result = feedbackService.updateFeedback(id, feedbackDTO);

		assertNotNull(result);
		verify(feedbackRepository).findById(id);
		verify(feedbackMapper).toDTO(updatedFeedback);
	}

	@Test
	public void testDeleteFeedback() {
		Long id = 1L;

		doNothing().when(feedbackRepository).deleteById(id);

		feedbackService.deleteFeedback(id);

		verify(feedbackRepository).deleteById(id);
	}
}
