package com.campany.dto;

import com.campany.validation.annotation.ValidEmail;
import com.campany.validation.annotation.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto implements Serializable {
    private static final long serialVersionUID = 1230768705888665229L;
    @JsonProperty("name")
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @ValidEmail(message = "El correo debe tener un formato válido")
    private String email;
    @ValidPassword(message = "La contraseña no cumple con el formato requerido")
    private String password;
    private List<PhoneDto> phones;
}
