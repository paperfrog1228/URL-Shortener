package paperfrog.urlshortener.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import paperfrog.urlshortener.Domain.Shorten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShortenRedisRepository extends CrudRepository<Shorten, String> {

}
