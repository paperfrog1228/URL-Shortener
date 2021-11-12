package paperfrog.urlshortener.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import paperfrog.urlshortener.Domain.Shorten;
import paperfrog.urlshortener.Domain.URLSaveForm;
import paperfrog.urlshortener.Repository.ShortenRedisRepository;
import paperfrog.urlshortener.Repository.ShortenRepository;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final ShortenRepository shortenRepository;
    private final ShortenRedisRepository shortenRedisRepository;
    private final ServerTimeManager serverTimeManager;
    public Shorten registerURL(URLSaveForm urlSaveForm){
        Shorten shorten=shortenRepository.findByOriginalURL(urlSaveForm.getAddress());
        if(shorten!=null) {
            return shorten;
        }

        String shortenAddress=serverTimeManager.getAddressByNanoTime();

        Shorten shortenURL=new Shorten();
        shortenURL.setOriginalURL(urlSaveForm.getAddress());
        shortenURL.setAddress(shortenAddress);
        return shortenRedisRepository.save(shortenURL);
    }

}
