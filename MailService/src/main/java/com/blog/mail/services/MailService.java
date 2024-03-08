package com.blog.mail.services;

import com.blog.mail.entities.Mail;
import com.blog.mail.repositories.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final MailRepository repository;

    public Mail saveEmail(Mail mail) {
        return repository.save(mail);
    }
}
