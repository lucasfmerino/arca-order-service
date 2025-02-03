package com.arca.order.modules.address.controller;

import com.arca.order.modules.address.domain.dto.AddressDisplayDto;
import com.arca.order.modules.address.domain.dto.ViaCepResponse;
import com.arca.order.modules.address.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;


    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    /*
     *  GET ADDRESS
     *
     */
    @Operation(summary = "Obter endereço formatado")
    @ApiResponse(responseCode = "200", description = "Endereço encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AddressDisplayDto.class)))
    @GetMapping("/{cep}")
    public ResponseEntity<AddressDisplayDto> getAddress(@PathVariable String cep) {
        return ResponseEntity.ok().body(addressService.getAddress(cep));
    }


    /*
     *  GET VIACEP RESPONSE
     *
     */
    @GetMapping("/viacep/{cep}")
    public ResponseEntity<ViaCepResponse> getViaCepResponse(@PathVariable String cep) {
        return ResponseEntity.ok().body(addressService.getViaCepResponse(cep));
    }


    /*
     *  GET ADDRESS LIST
     *
     */
    @GetMapping("/search")
    public Page<AddressDisplayDto> getAddressList(
            @RequestParam String state,
            @RequestParam String city,
            @RequestParam String address,
            Pageable pageable) {
        return addressService.getAddressList(state, city, address, pageable);
    }


}
