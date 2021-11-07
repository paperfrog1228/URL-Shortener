package paperfrog.urlshortener.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import paperfrog.urlshortener.Domain.*;
import paperfrog.urlshortener.Repository.ShortenRepository;
import paperfrog.urlshortener.Service.RegisterService;

import javax.validation.Valid;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class RegisterURLController {
    private final RegisterService registerService;
    private final ShortenRepository shortenRepository;

    @GetMapping(value = {"/", ""})
    public String home(Model model) {
        model.addAttribute("URL", new URLSaveForm());
        model.addAttribute("shortenList", shortenRepository.findAll());
        model.addAttribute("error", "");
        model.addAttribute("shorten",null);
        System.out.println("size : " + shortenRepository.findAll().size());
        return "/home";
    }

    @PostMapping("/url")
    public String addUrl(@Validated @ModelAttribute("URL") URLSaveForm urlSaveForm,
                         BindingResult bindingResult,
                         Model model) {
        model.addAttribute("URL", new URLSaveForm());
        model.addAttribute("error", "");
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "URL이 유효하지 않습니다!");
            model.addAttribute("shortenList", shortenRepository.findAll());
//            return "/home";
            return "/home :: #URLTable";
        }

        Shorten shorten=registerService.registerURL(urlSaveForm);
        model.addAttribute("shortenList", shortenRepository.findAll());
        model.addAttribute("shorten",shorten);
        return "/home :: #URLTable";
    }
}
