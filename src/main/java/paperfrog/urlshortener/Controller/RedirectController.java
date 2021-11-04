package paperfrog.urlshortener.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RedirectController {
    @GetMapping("/naver")
    public String RedirectToNaver(){
        return "redirect:http://naver.com";
    }
    @GetMapping("/{domain}")
    public String RedirectToPathVariable(@PathVariable String domain){
        if(domain.equals("home"))
            return "redirect:home";
        return "redirect:http://"+domain;
    }

    @GetMapping("/")
    public String RedirectToHome(){
        return "redirect:home";
    }
}
