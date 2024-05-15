package br.com.ada.tech.ecommerce.integration.serase;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "serasa-client", url = "${feign.serasa-score.url}")
public interface SerasaFeignClient {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ScoreResponseDto search(@Param("document") String document);

}
