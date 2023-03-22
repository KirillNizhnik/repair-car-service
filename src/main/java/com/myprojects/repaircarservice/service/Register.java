package com.myprojects.repaircarservice.service;

@FunctionalInterface
public interface Register<T> {
    public void register(T model);
}
