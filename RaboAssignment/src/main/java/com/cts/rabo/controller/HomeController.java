package com.cts.rabo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cts.rabo.constants.RaboConstants;
import com.cts.rabo.exception.ApplicationException;
import com.cts.rabo.service.IFileHandlingService;

/**
 * @author 550072
 *
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private IFileHandlingService fileHandlingService;

	/**
	 * @return
	 * 
	 * <p> this mapping is to load home page</p>
	 */
	@RequestMapping(value = RaboConstants.HOMEPAGE_URL, method = RequestMethod.GET)
	public String home() {
		return RaboConstants.HOMEPAGE;
	}

	/**
	 * @param fileType <p> input file type </p>
	 * @param file     <p> upload file to process</p>
	 * @param response <p> to append failed transaction report</p>
	 * 
	 * <p> this mapping to get failed transaction report based on uploaded file </p>
	 */
	@RequestMapping(value = RaboConstants.UPLOAD_URL, method = RequestMethod.POST)
	public void uploadFile(@RequestParam(RaboConstants.REQPARAM_FILETYPE) String fileType, @RequestParam(RaboConstants.REQPARAM_FILE) MultipartFile file, HttpServletResponse response) {
		fileHandlingService.getFailedTransaction(fileType, file, response);
	}
	
	
	/**
	 * @param ex <p> user defined custom exception </p>
	 * @return
	 * 
	 * <p> this is to handle custom exception and redirect to error page </p>
	 */
	@ExceptionHandler(ApplicationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView errorhandler(ApplicationException ex){
		ModelAndView modelAndView = new ModelAndView(RaboConstants.ERRORPAGE);
	    modelAndView.addObject(RaboConstants.RES_ERRORMSG,ex.getErrorCode()+" : "+ex.getMessage());
	    return modelAndView;
	}
	
	
	/**
	 * @return
	 * 
	 * <p> this mapping is to REDIRECT from error page to home page </p>
	 */
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect() {
		return RaboConstants.HOMEPAGE;
	}
}
