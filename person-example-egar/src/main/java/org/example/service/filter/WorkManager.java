package org.example.service.filter;

import org.example.entity.Person;

public interface WorkManager<T> {

    double getWorkTime(T essence);

}
