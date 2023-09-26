package org.example.service.filter;

import org.example.entity.Person;

public interface WorkManager<T, R> {

    R getWorkTime(T essence);

}
