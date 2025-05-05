/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AnawatRubrica.Rubrica;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubricaService {

    // Restituisce tutti i contatti
    public List<Contact> getAllContacts() {
        return CsvUtils.readContacts();
    }

    // Aggiunge un nuovo contatto
    public void addContact(Contact contact) {
        CsvUtils.addContact(contact);
    }

    // Elimina un contatto
    public void deleteContact(Contact contact) {
        List<Contact> contacts = CsvUtils.readContacts();
        contacts.removeIf(c -> c.getName().equals(contact.getName()) && c.getPhone().equals(contact.getPhone()) && c.getEmail().equals(contact.getEmail()));
        CsvUtils.writeContacts(contacts);
    }

    // Modifica un contatto esistente
    public void updateContact(Contact oldContact, Contact newContact) {
        CsvUtils.updateContact(oldContact, newContact);
    }
}