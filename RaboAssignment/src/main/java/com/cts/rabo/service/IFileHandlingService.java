
package com.cts.rabo.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 550072
 *
 */
public interface IFileHandlingService {

	/**
	 * @param fileType
	 * @param file
	 * @param response 
	 * @return
	 * 
	 * <p> to get failed transactions based on uploaded data </p>
	 * 
	 */
	void getFailedTransaction(String fileType, MultipartFile file, HttpServletResponse response);

}
