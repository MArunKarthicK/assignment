package com.cts.rabo.constants;

/**
 * @author 550072
 *<p> interface to hold generic constants </p>
 *
 */
public interface RaboConstants {
	
	String NO_FAILED_RECORDS="NO FAILED RECORDS TO SHOW";
	String NO_RECORDS_TO_SHOW= "NO RECORDS TO SHOW ";
	
	String COLUMN_METADATA="ReferenceID,Description,Transaction Status,Duplicate Reference Count";
	String NEW_LINE="\n";
	String DUPLICATE_RECORDS="DUPLICATE RECORDS";
	String LINE_SEPARATOR="line.separator";
	
	String XML_FORMAT="xml";
	String CSV_FORMAT="csv";
	String FORMAT="UTF-8";

	String OCTET_STEAM="application/octet-stream";
	String CONTENT_DISPOSITION="Content-Disposition";
	String ATTACHMENT_FILENAME="attachment;filename=FailedTransactions.csv";
	
	String HOMEPAGE_URL="/";
	String UPLOAD_URL="/upload";
	String REQPARAM_FILETYPE="filetype";
	String REQPARAM_FILE="file";
	String RES_ERRORMSG="errormessage";
	String ERRORPAGE = "error";
	String RECORD_TAG = "record";
	String ACCOUNTNUMBER_TAG = "accountNumber";
	String DESCRIPTION_TAG = "description";
	String STARTBAL_TAG = "startBalance";
	String ENDBAL_TAG = "endBalance";
	String MUTATION_TAG = "mutation";
	String DUPLICATE_TRANSACTION = "duplicate_transaction";
	String COMMA = ",";
	String REFERENCE_TAG = "reference";
	String HOMEPAGE = "home";

}
