package fr.bpifrance.qua.qformation.web.restclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "formationClient", url = "${formation.api.url}")
public interface FormationRestClient extends FormationRestService {
}
