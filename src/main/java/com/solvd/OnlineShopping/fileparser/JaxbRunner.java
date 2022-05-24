package com.solvd.OnlineShopping.fileparser;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.model.CommunicationPreference;
import com.solvd.OnlineShopping.model.Setting;
import com.solvd.OnlineShopping.model.User;

public class JaxbRunner {
	private static final Logger LOGGER = LogManager.getLogger(JaxbRunner.class);
	public static void main(String[] args) {
		CommunicationPreference c1 = new CommunicationPreference();
		Setting s1 = new Setting();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date3 = "1996-9-21";
		String date4 = "2021-12-23";
		String date5 = "2022-5-9";
		Date newDate;
		try {
			newDate = dateFormat.parse(date3);
			Date newDate1 = dateFormat.parse(date4);
			Date newDate2 = dateFormat.parse(date5);
			User u1 = new User(21, "Gebregiorgis", "Heaven", newDate , "1789@gmail.com", newDate1, newDate2, c1, s1);
			
		
		
		try {
			JAXBContext jc = JAXBContext.newInstance(User.class);
			//Marshling
			Marshaller mar = jc.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			mar.marshal(u1, new File("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/jaxboutput.xml"));
			
			//Unmarshling
			Unmarshaller unmar = jc.createUnmarshaller();
			User u2= (User) unmar.unmarshal(new File("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/user.xml"));

			LOGGER.info(u2);

			
			
		} catch (JAXBException e) {
			
			LOGGER.error(e.getMessage());
		}
		} catch (ParseException e1) {
			LOGGER.error(e1.getMessage());
		}
	}

}
