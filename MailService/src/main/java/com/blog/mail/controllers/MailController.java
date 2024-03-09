package com.blog.mail.controllers;

import com.blog.mail.entities.Mail;
import com.blog.mail.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/mail")
@Validated
public class MailController {
    @Autowired
    MailService mailService;
    @PostMapping
    public ResponseEntity<?> createMail(@Validated @RequestBody Mail mail) {
        return ResponseEntity.ok(mailService.saveEmail(mail));
    }
    @GetMapping
    public ResponseEntity<?> getAllMail(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10") int pageSize)
    {
        return ResponseEntity.ok(mailService.getAllMail(pageNo,pageSize));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@Validated @RequestBody Mail mail, @PathVariable Integer id){
        return ResponseEntity.ok(mailService.updateStatus(id,mail));
    }
}
