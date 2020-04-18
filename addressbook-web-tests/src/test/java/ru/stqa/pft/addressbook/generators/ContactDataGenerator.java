package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


import com.thoughtworks.xstream.XStream;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ContactDataGenerator {

        @Parameter(names = "-c", description = "Contact count")
        public int count;

        @Parameter(names = "-f", description = "File count")
        public String file;

        @Parameter(names = "-d", description = "Data Format")
        public String format;

        public static void main(String[] args) throws IOException {
            ContactDataGenerator generator = new ContactDataGenerator();
            JCommander jcommander = new JCommander(generator, args);
            generator.run();
            try {
                jcommander.parse(args);
            } catch (ParameterException ex){
                jcommander.usage();
                return;
            }
            //generator.run();
        }

        private void run() throws IOException {
            List<ContactData> contacts = generateContacts(count);
            if (format.equals("csv")) {
                saveAsCsv(contacts, new File(file));
            } else if (format.equals("xml")){
                saveAsXml(contacts, new File(file));
            } else if (format.equals("json")){
                saveAsJson(contacts, new File(file));
            } else {
                System.out.println("Unrecognized format " + format);
            }
        }

        private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(contacts);
            System.out.println(json);
            Writer writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        }

        private void saveAsXml(List<ContactData> contact, File file) throws IOException {
            XStream xstream = new XStream();
            xstream.alias("contact", ContactData.class);
            String xml = xstream.toXML(contact);
            Writer writer = new FileWriter(file);
            writer.write(xml);
            writer.close();

        }

        private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
            Writer writer = new FileWriter(file);
            for (ContactData contact : contacts){
                writer.write(String.format("%s; %s; %s\n", contact.getFirstName(), contact.getFirstName(), contact.getMiddleName(),contact.getAddress(),contact.getMobilePhone()));
            }
            writer.close();
        }


        private static List<ContactData> generateContacts(int count) {

            List<ContactData> contacts = new ArrayList<>();
            for (int i=0; i<count; i++){
                contacts.add(new ContactData().withName(String.format("test %s", i)).withLastName(String.format("lastname %s", i)).withName(String.format("name %s", i))
                        .withMiddlename(String.format("middlename %s", i)).withAddress(String.format("address %s", i)).withEmail(String.format("email %s", i))
                .withMobile(String.format("mobile %s", i)));
            }
            return contacts;
        }


}
