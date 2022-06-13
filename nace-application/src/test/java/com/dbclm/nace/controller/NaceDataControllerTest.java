package com.dbclm.nace.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dbclm.nace.advice.NaceExceptionHandler;
import com.dbclm.nace.dto.NaceDto;
import com.dbclm.nace.service.NaceService;
import com.dbclm.persistence.repository.NaceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class NaceDataControllerTest {

	private MockMvc mvc;

	@Mock
	private NaceService naceService;
	@InjectMocks
	private NaceDataController contoller;
	@Mock
	private NaceRepository naceRepository;
	@Mock
	private ModelMapper modelMapper;
	@Autowired

	private JacksonTester<NaceDto> jsonNash;

	@BeforeEach
	public void setup() {
		// We would need this line if we would not use the MockitoExtension
		// MockitoAnnotations.initMocks(this);
		// Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
		JacksonTester.initFields(this, new ObjectMapper());
		// MockMvc standalone approach
		mvc = MockMvcBuilders.standaloneSetup(contoller)
				.setControllerAdvice(new NaceExceptionHandler())
				.addFilters()
				.build();
	}

	@Test
	public void canRetrieveByorderIdWhenExists() throws Exception {

		NaceDto naceDto = new NaceDto();
		naceDto.setOrder("2234");
		// given
		given(naceService.getNaceDetailsById("2234"))
		.willReturn(naceDto);
		// when
		MockHttpServletResponse response = mvc.perform(
				get("/nace/2234")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(
				jsonNash.write(naceDto).getJson()
				);
	}

	
}
