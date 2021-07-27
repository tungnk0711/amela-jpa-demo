package com.amela.service;

import com.amela.exception.DuplicateLastNameException;

import java.util.List;

public interface IGeneralService <T> {


    List<T> findAll();


}
