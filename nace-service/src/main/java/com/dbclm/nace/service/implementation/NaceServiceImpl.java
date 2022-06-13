package com.dbclm.nace.service.implementation;


import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbclm.nace.dto.NaceDto;
import com.dbclm.nace.service.NaceService;
import com.dbclm.persistence.entity.NaceEntity;
import com.dbclm.persistence.repository.NaceRepository;
@Service
public class NaceServiceImpl implements NaceService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private NaceRepository naceRepository;
	private ModelMapper modelMapper;

	@Autowired 
	public NaceServiceImpl(NaceRepository naceRepository ,ModelMapper modelMapper) {
		this.naceRepository = naceRepository; 
		this.modelMapper = modelMapper;
	}

	@Override
	public void insertNaceData(List<NaceDto> naceDataList) {
		logger.debug("data insertion");
		naceRepository.saveAll(naceDataList 
				.stream()
				.map(naceData -> modelMapper.map(naceData, NaceEntity.class))
				.collect(Collectors.toList()));
	}

	@Override
	public NaceDto getNaceDetailsById(String  order) {
		logger.debug("getting data for {}",order);
		NaceEntity nace = naceRepository.findNaceByOrder(order).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(nace, NaceDto.class);
	}

}
