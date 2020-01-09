package global.coda.hospitalmanagementsystem.dao.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import global.coda.hospitalmanagementsystem.dao.PatientCriticality;
import global.coda.hospitalmanagementsystem.models.PatientForFile;

public class FindPatient {
	
	PatientCriticality getPatientPriority(int patientId) throws NumberFormatException, IOException
	{
		String filePath="C:\\Users\\CSS\\Desktop\\FindPatient.txt";
		File fileObject=new File(filePath);
		BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
		String currLine;
		while ((currLine=bufferedReader.readLine())!=null)
		{
			String tempElement[]=currLine.split(" ");
			if(Integer.parseInt(tempElement[0])==patientId)
			{
				bufferedReader.close();
				if(tempElement[1]=="TemporaryPatient")
				{
					
					System.out.print(tempElement[1]);
					bufferedReader.close();
					return PatientCriticality.TEMPORARY;
				}
				else 
				{
					
					System.out.print(tempElement[1]);
					bufferedReader.close();
					return PatientCriticality.CRITICAL;
				}
				
			}
			
		}
		return null;
		
		
	}
	

}
