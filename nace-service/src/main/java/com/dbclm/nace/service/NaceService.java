package com.dbclm.nace.service;


import java.util.List;

import com.dbclm.nace.dto.NaceDto;
import com.dbclm.nace.dto.exception.ServiceException;
import com.dbclm.persistence.entity.NaceEntity;

public interface NaceService  {

    void insertNaceData(List<NaceDto> naceEntities);
    NaceDto getNaceDetailsById(String orderId);
}