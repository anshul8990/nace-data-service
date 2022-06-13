package com.dbclm.nace.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dbclm.nace.dto.NaceDto;
import com.dbclm.nace.dto.exception.ServiceException;
import com.dbclm.nace.service.NaceService;
import com.dbclm.nace.service.helper.csv.CSVHelper;
import com.dbclm.persistence.entity.NaceEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("nace-data")
@Api(value = "Endpoint for exposing NACE data.")
public class NaceDataController {
    private NaceService naceService;
   
    @Autowired
    public void setNaceService(NaceService naceService) {
        this.naceService = naceService;
    }

    @ApiOperation(value = "Get NACE data details for an id")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Nace details Not found."),
            @ApiResponse(code = 200, message = "NACE details received successfully.", response = NaceEntity.class)})
    @GetMapping("/{orderId}")
    public NaceDto getNaceDetails(@PathVariable String orderId){
       return naceService.getNaceDetailsById(orderId);
    }

    @ApiOperation(value = "Insert NACE details.")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Incompatible data."),
            @ApiResponse(code = 201, message = "insertion successful")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "multipart/form-data")
    public void insertNaceDetails(@RequestParam("file") MultipartFile file) throws IOException, ServiceException  {
    	List<NaceDto> nacedata = CSVHelper.csvToNaceDto(file.getInputStream());
    	Optional.ofNullable(nacedata).orElseThrow(ServiceException::new);
        naceService.insertNaceData(nacedata);
    }
}
