package paperfrog.urlshortener;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.HttpURLConnection;
import java.net.URL;

public class AccessibleURL implements ConstraintValidator<ValidURL, String> {
    @SneakyThrows
    @Override
    public boolean isValid(String urlString, ConstraintValidatorContext context) {
        URL url;
        System.out.println("url String : "+urlString);
        if(!urlString.contains(".")) return false;
        HttpURLConnection connection=null;
        try {
            url = new URL(urlString);
        }
        catch(Exception e){
            return false;
        }
        connection =(HttpURLConnection) url.openConnection();
        System.out.println("응답 코드 : "+connection.getResponseCode());
        if(connection.getResponseCode()!=HttpURLConnection.HTTP_NOT_FOUND)
            return true;
        return false;
    }
}
