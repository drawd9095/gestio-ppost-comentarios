package com.system.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PublicacionCompleteDTO implements Serializable {

    private List<PublicacionDTO> contenido;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean ultima;


}
