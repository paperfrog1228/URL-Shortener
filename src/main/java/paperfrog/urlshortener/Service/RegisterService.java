package paperfrog.urlshortener.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import paperfrog.urlshortener.Domain.Shorten;
import paperfrog.urlshortener.Domain.URLSaveForm;
import paperfrog.urlshortener.Repository.ShortenRepository;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final ShortenRepository shortenRepository;
    private static Long id=0L;
    public String registerURL(URLSaveForm urlSaveForm){
        Shorten shortenURL=new Shorten();
        shortenURL.setOriginalURL(urlSaveForm.getAddress());
        String shortenAddress="test"+id;
        id++;
        shortenURL.setAddress(shortenAddress);
        shortenRepository.save(shortenURL);
        return shortenAddress;
    }

}
