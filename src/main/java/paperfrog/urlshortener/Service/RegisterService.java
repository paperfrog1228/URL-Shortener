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
    private final ServerTimeManager serverTimeManager;
    public Shorten registerURL(URLSaveForm urlSaveForm){
        Shorten shortenURL=new Shorten();

        String shortenAddress=serverTimeManager.getAddressByNanoTime();

        shortenURL.setOriginalURL(urlSaveForm.getAddress());
        shortenURL.setAddress(shortenAddress);
        return shortenRepository.save(shortenURL);
    }

}
