package br.com.meli.helpdesksimapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageInfoDTO<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;

}
