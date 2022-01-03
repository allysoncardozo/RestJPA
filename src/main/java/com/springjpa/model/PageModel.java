package com.springjpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageModel<T> {

    private long totalElements;
    private int pageSize;
    private int totalPage;
    private List<T> elements;


    public void montarObjetoPelaPagina(Page<T> page) {
        this.setPageSize(page.getNumberOfElements());
        this.setElements(page.getContent());
        this.setTotalPage(page.getTotalPages());
        this.setTotalElements(page.getTotalElements());
    }
}
