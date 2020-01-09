package global.coda.hospitalmanagementsystem.dao;

import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
//import java.util.LinkedHashSet;
import java.util.Map;
import java.util.ResourceBundle;

//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import org.w3c.dom.NamedNodeMap;
//import javax.xml.*;
import org.w3c.dom.*;

import global.coda.hospitalmanagementsystem.constants.ApplicationConstant;
//import global.coda.hospitalmanagementsystem.customexception.DataNotFound;
import global.coda.hospitalmanagementsystem.models.PatientForFile;
import global.coda.hospitalmanagementsystem.services.HospitalManagementServicesImpl;

public class HospitalManagementDaoImpXML implements HospitalManagementDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(HospitalManagementServicesImpl.class);
	/*
	 * create logger for output stream
	 */
	ResourceBundle mybundle = ResourceBundle.getBundle(ApplicationConstant.HMS1000D);

	public Map<Integer, PatientForFile> readAll() throws ParserConfigurationException, SAXException, IOException {
		Map<Integer, PatientForFile> patientInfo = new LinkedHashMap<>();
		// Get Document Builder
		try {

			File fXmlFile = new File("C:\\Users\\CSS\\Desktop\\TempPatient.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc;
			try {
			doc= dBuilder.parse(fXmlFile);
			}
			catch(SAXParseException e) {
				return new HashMap<Integer, PatientForFile>();
			}
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			//doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			NodeList nList = doc.getElementsByTagName("patients");
			
			
			

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					PatientForFile patient = new PatientForFile();

					int id=Integer.parseInt((eElement.getElementsByTagName("patientId").item(0).getTextContent()).toString());
					patient.setPatientNames(eElement.getElementsByTagName("patientName").item(0).getTextContent());
					patient.setPatientDiagnosis(
							eElement.getElementsByTagName("patientDiagnosis").item(0).getTextContent());
					patient.setPatientAge(eElement.getElementsByTagName("patientAge").item(0).getTextContent());
					patient.setAddress(
							Arrays.asList((eElement.getElementsByTagName("address").item(0).getTextContent())));
					patientInfo.put(id, patient);
				}
			}
			System.out.print(patientInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return patientInfo;
	}

	public boolean write(Map<Integer, PatientForFile> patientInfo) throws IOException {

		try {

			System.out.print(patientInfo);
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			org.w3c.dom.Document document = documentBuilder.newDocument();
			String filePath = "C:\\\\Users\\\\CSS\\\\Desktop\\\\";
			File fb=new File(filePath,"TempPatient.xml");
			FileWriter fileWriter = null;
			File fb1=null;
			if(fb.exists())
			{	
			fb.delete();
			fb1=new File(filePath,"TempPatient.xml");
			
			}
			fileWriter = new FileWriter(fb1,true);

			// root element
			// Patient patient1=new Patient();
			Transformer transformer =null;
			//transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			int flag=1;
			Element root = null;
			for (Integer key : patientInfo.keySet()) {
				if(flag==1)
				{
				root = (document).createElement("patients");
				(document).appendChild(root);
				}
		

				// employee element
				Element employee = (document).createElement("patient");

				root.appendChild(employee);

				// set an attribute to staff element
				Element patientId = (document).createElement("patientId");
				patientId.appendChild((document).createTextNode(key.toString()));
				employee.appendChild(patientId);

				// you can also use staff.setAttribute("id", "1") for this

				// patientname element
				Element patientName = (document).createElement("patientName");
				patientName.appendChild((document).createTextNode(patientInfo.get(key).getPatientNames()));
				employee.appendChild(patientName);

				// patientDiagnosis element
				Element patientDiagnosis = (document).createElement("patientDiagnosis");
				patientDiagnosis.appendChild((document).createTextNode(patientInfo.get(key).getPatientDiagnosis()));
				employee.appendChild(patientDiagnosis);

				// patientAge element
				Element patientAge = (document).createElement("patientAge");
				patientAge.appendChild((document).createTextNode(patientInfo.get(key).getPatientAge()));
				employee.appendChild(patientAge);

				// address elements
				Element address = (document).createElement("address");
				address.appendChild((document).createTextNode(patientInfo.get(key).getAddress().toString()));
				employee.appendChild(address);

				// create the xml file
				// transform the DOM Object to an XML File
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource((Node) document);
				StreamResult streamResult = new StreamResult(fileWriter);

				// If you use
				// StreamResult result = new StreamResult(System.out);
				// the output will be pushed to the standard output ...
				// You can use that for debugging
				if(flag==1)
				{
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				}
				flag=2;

				transformer.transform(domSource, streamResult);
				System.out.println("Done creating XML File");
			}

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		return true;
	}
}
