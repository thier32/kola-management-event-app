package com.kola.management.event.event.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListDataDto<T> {
    public long total;
    public int numberPage;
    public String key;
    public int elementPerPage = 10;
    public int currentPage;
    public List<String> keys;
    public List<T> listElements;
    public T singleElement;;
}

