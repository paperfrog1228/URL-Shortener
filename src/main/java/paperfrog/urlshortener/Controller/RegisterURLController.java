package paperfrog.urlshortener.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import paperfrog.urlshortener.Domain.*;
import paperfrog.urlshortener.Repository.ShortenRepository;
import paperfrog.urlshortener.Service.RegisterService;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class RegisterURLController {
    private final RegisterService registerService;
    private final ShortenRepository shortenRepository;
    @GetMapping(value = {"/",""})
    public String home(Model model){
        model.addAttribute("URL",new URLSaveForm());
        model.addAttribute("shortenList",shortenRepository.findAll());
        System.out.println("size : "+shortenRepository.findAll().size());
        return "/home";
    }
    @PostMapping("/url")
    public String addUrl(@ModelAttribute("URL") URLSaveForm urlSaveForm, BindingResult bindingResult){
        System.out.println("Add Address : "+ urlSaveForm.getAddress());
        if(bindingResult.hasErrors()) return "/";
        registerService.registerURL(urlSaveForm);
        return "redirect:/home";
    }
}
