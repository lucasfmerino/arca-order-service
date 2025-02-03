package com.arca.order.modules.address.service;

import com.arca.order.client.ViaCepClient;
import com.arca.order.modules.address.domain.dto.AddressDisplayDto;
import com.arca.order.modules.address.domain.dto.ViaCepResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final ViaCepClient viaCepClient;

    public AddressService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }


    /*
     *  GET VIA CEP RESPONSE
     *
     */
    public ViaCepResponse getViaCepResponse(String cep) {
        return viaCepClient.getAddress(cep);
    }


    /*
     *  GET ADDRESS
     *
     */
    public AddressDisplayDto getAddress(String cep) {
        return new AddressDisplayDto(viaCepClient.getAddress(cep));
    }


    /*
     *  GET ADDRESS LIST
     *
     */
    public Page<AddressDisplayDto> getAddressList(String state, String city, String address, Pageable pageable) {

        try {
            List<ViaCepResponse> response = viaCepClient.getAddressList(state, city, address);

            if (response == null || response.isEmpty()) {
                return Page.empty(pageable);
            }

            List<AddressDisplayDto> allAddresses = response.stream()
                    .map(AddressDisplayDto::new)
                    .toList();

            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(), allAddresses.size());

            return new PageImpl<>(allAddresses.subList(start, end), pageable, allAddresses.size());
        } catch (Exception e) {
            return Page.empty(pageable);
        }

    }
}
