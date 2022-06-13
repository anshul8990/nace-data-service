package com.dbclm.nace.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.dbclm.nace.dto.NaceDto;
import com.dbclm.nace.dto.exception.ServiceException;
import com.dbclm.nace.service.implementation.NaceServiceImpl;
import com.dbclm.persistence.entity.NaceEntity;
import com.dbclm.persistence.repository.NaceRepository;
@ExtendWith(MockitoExtension.class)
public class NaceServiceImplTest {

	@Mock
	private NaceRepository naceRepository;
	@InjectMocks
	private NaceServiceImpl naceService;
	@Mock
	private ModelMapper modelMapper;

	@Test
	public void testGetNaceDetailsByIdSuccess() {
		NaceEntity naceEntity = new NaceEntity("1", "2", "3","", "Nace 1", "ref");
		NaceDto naceDto = new NaceDto("1", "2", "3","", "Nace 1", "ref");
		when(naceRepository.findNaceByOrder("1")).thenReturn(Optional.of(naceEntity));
		when(modelMapper.map(naceEntity, NaceDto.class)).thenReturn(naceDto);
		Assertions.assertEquals(naceDto.getOrder(), naceService.getNaceDetailsById("1").getOrder(), "expected Nace entity");
	}

	@Test
	public void testGetNaceDetailsByIdFailure() {
		Assertions.assertThrows(EntityNotFoundException.class,
				()->{
					when(naceRepository.findNaceByOrder("1")).thenThrow(EntityNotFoundException.class);
					naceService.getNaceDetailsById("1");
				});
	}

	@Test
	public void testInsertNaceDetails() throws ServiceException {
		NaceDto nace1 = new NaceDto("1", "2", "3","", "Nace 1", "ref1");
		NaceDto nace2 = new NaceDto("2", "6", "8","", "Nace 2", "ref2");
		List<NaceDto> naceDataList = new ArrayList<>();
		naceDataList.add(nace1);
		naceDataList.add(nace2);
		naceService.insertNaceData(naceDataList);
		Mockito.verify(modelMapper, Mockito.times(2)).map(any(), any());
	}

}
