package com.blog.mail.exceptions;

import com.blog.mail.entities.TimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorEntity {
    private String status;
    private String field;
    private String error;
    private String time = TimeUtils.convertTime();

}
