package dev.ruanvictor.sleepystorie.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User {

    private String birthday;
    private String celular;
    private String address;
    private String city;
    private String cep;
    private String uf;
}
