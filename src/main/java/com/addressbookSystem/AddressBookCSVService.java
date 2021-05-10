package com.addressbookSystem;

import com.addressbookSystem.AddressBook;
import com.addressbookSystem.Contact;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddressBookCSVService {

    public static void writeDataToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (Writer writer = Files.newBufferedWriter(Paths.get("Contacts.csv"));) {
            StatefulBeanToCsvBuilder<Contact> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<Contact> beanWriter = builder.build();
            beanWriter.write(AddressBook.contactList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readDataFromCSV() throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get("Contacts.csv"));
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();){
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("First Name = " + nextRecord[0]);
                System.out.println("Last Name = " + nextRecord[1]);
                System.out.println("Address = " + nextRecord[2]);
                System.out.println("City = " + nextRecord[3]);
                System.out.println("State = " + nextRecord[4]);
                System.out.println("Email = " + nextRecord[5]);
                System.out.println("Phone Number = " + nextRecord[6]);
                System.out.println("Zip Code = " + nextRecord[7]);
            }
        }
    }
}
