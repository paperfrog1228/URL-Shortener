package paperfrog.urlshortener.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shorten {
    private String address;
    private String originalURL;
}
