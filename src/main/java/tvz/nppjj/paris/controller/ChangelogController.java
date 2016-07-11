package tvz.nppjj.paris.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jo on 7/11/16.
 */
@RestController
public class ChangelogController {

    @Value("${appVersion:unknown}")
    String appVersion;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");;
    @RequestMapping("/changelog")
    public String getAppVersion(){

        return appVersion + " " + LocalDateTime.now().format(dateTimeFormatter);
    }
}
