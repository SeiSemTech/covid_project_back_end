package com.app.covid.service;

import com.app.covid.domain.Example;

public interface IExampleService {

  Example getExampleInfo(Long id);

  Example findByName(String value);

}
