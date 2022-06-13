package com.dbclm.nace.service.helper.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.dbclm.nace.dto.NaceDto;


public class CSVHelper {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "Order", "Level", "Code", "Description" , "Reference" ,"Parent" };
	public static boolean hasCSVFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<NaceDto> csvToNaceDto(InputStream is) {
		try 
		(BufferedReader fileReader = new BufferedReader(new InputStreamReader(is,"UTF-8")); 
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());)
		{ 
			List<NaceDto> naceDtoList = new ArrayList<NaceDto>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord
					csvRecord : csvRecords) {
				NaceDto naceDto = new NaceDto(
						csvRecord.get(0),csvRecord.get(1),
						csvRecord.get(2),csvRecord.get(3),
						csvRecord.get(4),csvRecord.get(9));
				naceDtoList.add(naceDto);
			} 
			return naceDtoList; 
		} catch (IOException e) { 
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage()); 
		} 
	}


}
