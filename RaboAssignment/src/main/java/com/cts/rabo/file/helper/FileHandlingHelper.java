package com.cts.rabo.file.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.cts.rabo.constants.ErrorConstants;
import com.cts.rabo.constants.RaboConstants;
import com.cts.rabo.exception.ApplicationException;
import com.cts.rabo.pojo.TransactionRecord;
import com.cts.rabo.pojo.TransactionStatus;

/**
 * @author 550072
 *
 */
@Component
public class FileHandlingHelper {

	/**
	 * @param bytes <p> input upload CSV data - bytes </p>
	 * 
	 * <p> process the CSV data and convert into map </p>
	 * 
	 * @return
	 */
	public HashMap<Integer, TransactionRecord> getTransactionRecordsfromCSV(byte[] bytes) {

		HashMap<Integer, TransactionRecord> trnsRecordMap = new HashMap<>();
		InputStream is = null;
		BufferedReader bfReader = null;

		Function<String, TransactionRecord> mapToItem = (line) -> {
			String[] p = line.split(RaboConstants.COMMA);// a CSV has comma separated lines
			TransactionRecord record = new TransactionRecord();
			int refId = Integer.parseInt(p[0]);
			record.setReferenceId(refId);
			record.setAccountNumber(p[1]);
			record.setDescription(p[2]);
			record.setStartBalance(Double.parseDouble(p[3]));
			record.setMutation(Double.parseDouble(p[4]));
			record.setEndBalance(Double.parseDouble(p[5]));
			return record;
		};

		try {
			is = new ByteArrayInputStream(bytes);
			bfReader = new BufferedReader(new InputStreamReader(is));

			List<TransactionRecord> inputList = bfReader.lines().skip(1).map(mapToItem).collect(Collectors.toList());

			for (TransactionRecord record : inputList) {
				setTransactionStatus(record);
				setDuplicationStatus(trnsRecordMap, record);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorConstants.ERROR_RB_4, ErrorConstants.CSV_FILE_PROCESS_EXCEPTION);
		}

		finally {
			try {
				bfReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return trnsRecordMap;
	}

	/**
	 * @param bytes <p> input upload XML data - bytes </p>
	 * 
	 * <p> process the XML data and convert into map </p>
	 * 
	 * @return
	 */
	public HashMap<Integer, TransactionRecord> getTransactionRecordsfromXML(byte[] bytes) {

		HashMap<Integer, TransactionRecord> trnsRecord = new HashMap<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(new String(bytes))));
			NodeList nList = doc.getElementsByTagName(RaboConstants.RECORD_TAG);

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					int refId = Integer.parseInt(eElement.getAttribute(RaboConstants.REFERENCE_TAG));
					TransactionRecord record = new TransactionRecord();
					record.setReferenceId(refId);
					record.setAccountNumber(eElement.getElementsByTagName(RaboConstants.ACCOUNTNUMBER_TAG).item(0).getTextContent());
					record.setDescription(eElement.getElementsByTagName(RaboConstants.DESCRIPTION_TAG).item(0).getTextContent());
					record.setStartBalance(
							Double.parseDouble(eElement.getElementsByTagName(RaboConstants.STARTBAL_TAG).item(0).getTextContent()));
					record.setEndBalance(
							Double.parseDouble(eElement.getElementsByTagName(RaboConstants.ENDBAL_TAG).item(0).getTextContent()));
					record.setMutation(
							Double.parseDouble(eElement.getElementsByTagName(RaboConstants.MUTATION_TAG).item(0).getTextContent()));

					setTransactionStatus(record);
					setDuplicationStatus(trnsRecord, record);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorConstants.ERROR_RB_3, ErrorConstants.XML_FILE_PROCESS_EXCEPTION);
		}
		return trnsRecord;

	}

	/**
	 * @param trnsRecord  <p> processed map - key[ReferenceID] values[corresponding record] </p>
	 * @param record      <p> single record - CSV/XML </p>
	 * 
	 * <p> this method is to find duplicate transactions </p>
	 * 
	 */
	private void setDuplicationStatus(HashMap<Integer, TransactionRecord> trnsRecord, TransactionRecord record) {

		int refId = record.getReferenceId();

		if (trnsRecord != null) {

			if (trnsRecord.get(refId) == null) {
				trnsRecord.put(refId, record);
			} else {
				TransactionRecord dupRef = new TransactionRecord();
				dupRef.setReferenceId(refId);
				List<TransactionRecord> dupRecordList = new ArrayList<>();
				dupRecordList.add(record);

				if (trnsRecord.get(refId).getDuplicateRecords() == null)
					dupRecordList.add(trnsRecord.get(refId));

				if (trnsRecord.get(refId).getDuplicateRecords() != null
						&& !trnsRecord.get(refId).getDuplicateRecords().isEmpty())
					dupRecordList.addAll(trnsRecord.get(refId).getDuplicateRecords());

				dupRef.setDuplicateRecords(dupRecordList);
				dupRef.setTransactionStatus(TransactionStatus.DUPLICATED);
				dupRef.setDescription(RaboConstants.DUPLICATE_TRANSACTION);
				trnsRecord.put(refId, dupRef);
			}
		}
	}

	/**
	 * @param record <p> single record - CSV/XML </p>
	 * 
	 * <p> this method is to set transaction status based on manipulation </p>
	 */
	private void setTransactionStatus(TransactionRecord record) {

		double sbalance = record.getStartBalance();
		double mutation = record.getMutation();
		double ebalance = record.getEndBalance();

		Double totalamt = BigDecimal.valueOf(new Double(sbalance + mutation)).setScale(2, RoundingMode.HALF_UP)
				.doubleValue();

		if (totalamt == ebalance) {
			record.setTransactionStatus(TransactionStatus.PASSED);
		} else {
			record.setTransactionStatus(TransactionStatus.FAILED);
		}

	}

}
