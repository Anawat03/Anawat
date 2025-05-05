/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AnawatRubrica.Rubrica;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RubricaController {

    private final RubricaService contactService;

    public RubricaController(RubricaService contactService) {
        this.contactService = contactService;
    }

    // Mostra tutti i contatti
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        return "home";
    }

    // Aggiungi un nuovo contatto
    @PostMapping("/add")
    public String addContact(@ModelAttribute Contact contact) {
        contactService.addContact(contact);
        return "redirect:/";
    }

    // Elimina un contatto
    @PostMapping("/delete")
    public String deleteContact(@ModelAttribute Contact contact) {
        contactService.deleteContact(contact);
        return "redirect:/";
    }

    // Mostra la pagina di modifica contatto
    @GetMapping("/edit/{name}/{phone}/{email}")
    public String editContact(@PathVariable("name") String name,
                              @PathVariable("phone") String phone,
                              @PathVariable("email") String email, Model model) {
        Contact contact = new Contact(name, phone, email);
        model.addAttribute("contact", contact);
        return "edit";
    }

    // Modifica un contatto
    @PostMapping("/update")
    public String updateContact(@RequestParam("oldName") String oldName,
                                @RequestParam("oldPhone") String oldPhone,
                                @RequestParam("oldEmail") String oldEmail,
                                @RequestParam("newName") String newName,
                                @RequestParam("newPhone") String newPhone,
                                @RequestParam("newEmail") String newEmail) {
        // Crea contatti vecchio e nuovo
        Contact oldContact = new Contact(oldName, oldPhone, oldEmail);
        Contact newContact = new Contact(newName, newPhone, newEmail);

        // Chiamata al servizio per l'aggiornamento
        contactService.updateContact(oldContact, newContact);

        // Redirige alla home dopo l'aggiornamento
        return "redirect:/";
    }
}
