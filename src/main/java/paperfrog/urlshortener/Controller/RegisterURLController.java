package paperfrog.urlshortener.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import paperfrog.urlshortener.Domain.*;
@Controller
@RequestMapping("/home")
public class RegisterURLController {
    @GetMapping(value = {"/",""})
    public String home(Model model){
        model.addAttribute("URL",new URLSaveForm());
        return "/home";
    }
    @PostMapping("/url")
    public String addUrl(@ModelAttribute("URL") URLSaveForm urlSaveForm, BindingResult bindingResult){
        System.out.println("Add Address : "+ urlSaveForm.getAddress());
        if(bindingResult.hasErrors()) return "/home";
        return "redirect:http://"+urlSaveForm.getAddress();
    }

}
