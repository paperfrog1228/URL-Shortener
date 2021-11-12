package paperfrog.urlshortener.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import paperfrog.urlshortener.Domain.Shorten;
import paperfrog.urlshortener.Repository.ShortenRedisRepository;
import paperfrog.urlshortener.Repository.ShortenRepository;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RedirectController {
    private final ShortenRedisRepository shortenRedisRepository;
    @GetMapping("/naver")
    public String RedirectToNaver(){
        return "redirect:home";
    }
    @GetMapping("/{domain}")
    public String RedirectToPathVariable(@PathVariable String domain){
        System.out.println("request url : "+domain);
        if(domain.equals("home")||domain.equals("")||domain.equals("home/"))
            return "redirect::/home";
        Optional<Shorten> shorten=shortenRedisRepository.findById(domain);
        System.out.println("이 시팔 1 :"+shorten.get().getOriginalURL());
        if(shorten==null)
            return "redirect::/home";
        return "redirect:"+shorten.get().getOriginalURL();
    }

    @GetMapping(value = {"","/"})
    public String RedirectToHome(){
        return "redirect:/home";
    }
}
