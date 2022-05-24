package com.solvd.OnlineShopping.fileparser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.OnlineShopping.model.Address;
import com.solvd.OnlineShopping.model.Vendor;

public class JacksonRunner {

	private static final Logger LOGGER = LogManager.getLogger(JacksonRunner.class);
	public static void main(String[] args) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		//Java object to Json
		Address address = new Address(6, 6754, "Broadway", "A11", "Seattle", "WA", "98112", "USA");
		Vendor vendor = new Vendor(5, "Apple store", "Brandon", "email1123@email.com", "800-000-0002", address);
		String date = "1996-9-21";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date estDate = dateFormat.parse(date);
			vendor.setEstDate(estDate);
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/vendoroutput.json"), vendor);
		} catch (ParseException e) {
			LOGGER.error(e.getMessage());
		} catch (StreamWriteException e) {
			LOGGER.error(e.getMessage());
		} catch (DatabindException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		
		//Json to java object
		try {
			
			JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Vendor.class);
			List <Vendor> otherVendors = mapper.readValue(new File("/Users/apple/Desktop/Solvd automation/OnlineShopping/src/main/resources/vendor.json"), type);
			otherVendors.forEach((v) -> {
//				Address address1 = v.getAddress();
//				LOGGER.info("Vendor id: " + v.getId());
//				LOGGER.info("Vendor name: " + v.getVendorName());
//				LOGGER.info("Contact name: " + v.getContactName());
//				LOGGER.info("Email: " + v.getEmail());
//				LOGGER.info("Phone: " + v.getPhone());
//				LOGGER.info("Business established in: " + v.getEstDate());
//				LOGGER.info("Address id: " + address1.getId());
//				LOGGER.info("House number: " + address1.getHouseNumber());
//				LOGGER.info("Street: " + address1.getStreet());
//				LOGGER.info("Apartment number: " + address1.getApartmentNumber());
//				LOGGER.info("City: " + address1.getCity());
//				LOGGER.info("State: " + address1.getState());
//				LOGGER.info("Zip Code: " + address1.getPostalCode());
//				LOGGER.info("Country: " + address1.getCountry());
				LOGGER.info(v);
				});

		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage());
		} catch (StreamReadException e) {
			LOGGER.error(e.getMessage());
		} catch (DatabindException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

}
