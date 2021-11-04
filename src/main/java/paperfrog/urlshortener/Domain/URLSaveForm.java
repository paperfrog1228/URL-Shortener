package paperfrog.urlshortener.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import paperfrog.urlshortener.ValidURL;

@Getter
@Setter
public class URLSaveForm {
    @ValidURL
    private String address;
}
