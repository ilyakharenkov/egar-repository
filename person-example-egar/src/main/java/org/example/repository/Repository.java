package org.example.repository;

import java.util.List;

public interface Repository<T, L> {

    List<T> getList();

    void add(T essence);

    void delete(L id);

}
