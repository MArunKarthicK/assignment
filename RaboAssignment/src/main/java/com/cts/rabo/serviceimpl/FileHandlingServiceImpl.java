package com.cts.rabo.serviceimpl;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cts.rabo.constants.ErrorConstants;
import com.cts.rabo.constants.RaboConstants;
import com.cts.rabo.exception.ApplicationException;
import com.cts.rabo.file.generator.CSVFileGenerator;
import com.cts.rabo.file.helper.FileHandlingHelper;
import com.cts.rabo.pojo.TransactionRecord;
import com.cts.rabo.service.IFileHandlingService;

/**
 * @author 550072
 *
 */
@Component
public class FileHandlingServiceImpl implements IFileHandlingService {

	@Autowired
	private CSVFileGenerator csvFileGenerator;

	@Autowired
	private FileHandlingHelper fileHandlingHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cts.rabo.service.IHomeService#getFailedTransaction(java.lang.String,
	 * byte[])
	 */
	@Override
	public void getFailedTransaction(String fileType, MultipartFile file, HttpServletResponse httpresponse) {
		HashMap<Integer, TransactionRecord> resultRecordMap = null;

		try {
			byte[] bytes = !file.isEmpty() ? file.getBytes() : null;
			boolean extensioncheck = FilenameUtils.getExtension(file.getOriginalFilename()).equalsIgnoreCase(fileType)?true:false;
			if (extensioncheck && fileType.equalsIgnoreCase(RaboConstants.XML_FORMAT) && bytes != null) {
				resultRecordMap = fileHandlingHelper.getTransactionRecordsfromXML(bytes);
			} else if (extensioncheck && fileType.equalsIgnoreCase(RaboConstants.CSV_FORMAT) && bytes != null) {
				resultRecordMap = fileHandlingHelper.getTransactionRecordsfromCSV(bytes);
			} else {
				throw new ApplicationException(ErrorConstants.ERROR_RB_1, ErrorConstants.FILE_EMPTY_EXCEPTION);
			}
			csvFileGenerator.constructCSVreport(resultRecordMap, httpresponse);
		} catch (IOException e) {
			throw new ApplicationException(ErrorConstants.ERROR_RB_2, ErrorConstants.FILE_PROCESS_EXCEPTION);
		}
	}

}
