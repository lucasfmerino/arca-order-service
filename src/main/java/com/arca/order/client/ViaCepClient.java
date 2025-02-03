package com.arca.order.client;

import com.arca.order.modules.address.domain.dto.ViaCepResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Hidden
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    ViaCepResponse getAddress(@PathVariable("cep") String cep);

    @GetMapping("/{state}/{city}/{address}/json/")
    List<ViaCepResponse> getAddressList(@PathVariable("state") String state, @PathVariable("city") String city, @PathVariable("address") String cep);
}