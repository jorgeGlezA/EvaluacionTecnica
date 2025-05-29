package com.mx.apiPlataformasEspeciales.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "segundaApi", url = "http://localhost:8081") // cambia el URL según tu config
public interface SegundaApiClient {
    @PostMapping("/api/segunda")
    String enviarSecreto(@RequestBody String secreto);
}

