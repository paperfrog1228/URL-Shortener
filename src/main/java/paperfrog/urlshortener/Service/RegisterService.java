package paperfrog.urlshortener.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import paperfrog.urlshortener.Domain.Shorten;
import paperfrog.urlshortener.Domain.URLSaveForm;
import paperfrog.urlshortener.Repository.ShortenRedisRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final ShortenRedisRepository shortenRedisRepository;
    private final ServerTimeManager serverTimeManager;
    public Shorten registerURL(URLSaveForm urlSaveForm){
        /* todo : 흠 value->key로 검색하려면 테이블을 하나 더 만드는 수 밖에 없나?
        Optional<Shorten> shorten=shortenRedisRepository.findById(urlSaveForm.getAddress());
        if(shorten.isPresent()) {
            System.out.println("중복되었음 ");
            return shorten.get();
        }
         */
        String shortenAddress=serverTimeManager.getAddressByNanoTime();

        Shorten shortenURL=new Shorten();
        shortenURL.setOriginalURL(urlSaveForm.getAddress());
        shortenURL.setAddress(shortenAddress);
        return shortenRedisRepository.save(shortenURL);
    }

}
