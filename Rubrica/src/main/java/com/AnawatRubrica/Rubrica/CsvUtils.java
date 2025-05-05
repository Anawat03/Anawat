/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AnawatRubrica.Rubrica;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.File;

public class CsvUtils {
    private static final String FILE_PATH = "contacts.csv";

    // Verifica che il file esista, altrimenti lo crea
    private static void ensureFileExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile(); // Crea il file se non esiste
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Legge i contatti dal CSV
    public static List<Contact> readContacts() {
        ensureFileExists(); // Assicurati che il file esista prima di leggere
        List<Contact> contacts = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            String[] line;
            reader.readNext(); // Salta l'intestazione
            while ((line = reader.readNext()) != null) {
                contacts.add(new Contact(line[0], line[1], line[2]));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // Scrive una lista di contatti nel CSV
    public static void writeContacts(List<Contact> contacts) {
        ensureFileExists(); // Assicurati che il file esista prima di scrivere
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            writer.writeNext(new String[]{"Name", "Phone", "Email"}); // Intestazione
            for (Contact c : contacts) {
                writer.writeNext(new String[]{c.getName(), c.getPhone(), c.getEmail()});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Aggiunge un contatto al CSV
    public static void addContact(Contact contact) {
        List<Contact> contacts = readContacts();
        contacts.add(contact);
        writeContacts(contacts);
    }

    // Modifica un contatto nel CSV
    public static void updateContact(Contact oldContact, Contact newContact) {
        List<Contact> contacts = readContacts();
        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            if (c.getName().equals(oldContact.getName()) && c.getPhone().equals(oldContact.getPhone()) && c.getEmail().equals(oldContact.getEmail())) {
                contacts.set(i, newContact); // Sostituisce il contatto
                break;
            }
        }
        writeContacts(contacts); // Riscrive tutti i contatti
    }
}
