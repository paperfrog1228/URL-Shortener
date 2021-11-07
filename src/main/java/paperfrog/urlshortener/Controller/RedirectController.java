package paperfrog.urlshortener.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import paperfrog.urlshortener.Domain.Shorten;
import paperfrog.urlshortener.Repository.ShortenRepository;

@Controller
@RequiredArgsConstructor
public class RedirectController {
    private final ShortenRepository shortenRepository;
    @GetMapping("/naver")
    public String RedirectToNaver(){
        return "redirect:home";
    }
    @GetMapping("/{domain}")
    public String RedirectToPathVariable(@PathVariable String domain){
        if(domain.equals("home")||domain.equals(""))
            return "redirect::/home";
        Shorten shorten=shortenRepository.findByShortenAddress(domain);
        return "redirect:"+shorten.getOriginalURL();
    }

    @GetMapping(value = {"","/"})
    public String RedirectToHome(){
        return "redirect:/home";
    }
}
