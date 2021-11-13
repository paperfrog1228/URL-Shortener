package paperfrog.urlshortener.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash(value = "shorten",timeToLive = 3600)
public class Shorten {
    @Id
    private String address;
    private String originalURL;
}
