package paperfrog.urlshortener.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import paperfrog.urlshortener.Domain.Shorten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ShortenRepository {
    private final HashMap<String, Shorten> URLToShorten;
    private final HashMap<String, Shorten> shortenURLToShorten;
    public void save(Shorten shorten) {
        URLToShorten.put(shorten.getOriginalURL(),shorten);
        shortenURLToShorten.put(shorten.getAddress(),shorten);

    }
    public List<Shorten> findAll(){
        List<Shorten> list=new ArrayList<>();
        for(Shorten item:shortenURLToShorten.values())
            list.add(item);
        return list;
    }
}
