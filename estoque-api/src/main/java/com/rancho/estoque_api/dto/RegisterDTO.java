
package com.rancho.estoque_api.dto;

import com.rancho.estoque_api.model.Role;

public record RegisterDTO(String nomeGuerra, String senha, Role role) {}