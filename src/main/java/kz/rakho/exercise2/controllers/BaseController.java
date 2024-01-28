package kz.rakho.exercise2.controllers;


import kz.rakho.exercise2.models.TemperatureModel;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class BaseController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/change")
    public String change() {
        return "change-temperatures";
    }


    @GetMapping("/show")
    public String show() {
        return "show-temperatures";
    }


    @MessageMapping("/temperature")
    @SendTo("/topic/temp-changed")
    public TemperatureModel temp(TemperatureModel temperature) throws Exception {
        return temperature;
    }

}
