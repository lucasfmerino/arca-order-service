package com.arca.order.modules.address.domain.dto;

public record AddressDisplayDto (
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String uf
) {
    public AddressDisplayDto(ViaCepResponse viaCepResponse) {
        this (
                viaCepResponse.cep(),
                viaCepResponse.logradouro(),
                viaCepResponse.complemento(),
                viaCepResponse.bairro(),
                viaCepResponse.uf()
        );
    }
}
