package com.app.covid.service.implementation;

import com.app.covid.domain.Example;
import com.app.covid.repository.IExampleRepository;
import com.app.covid.service.IExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService implements IExampleService {

	@Override
	public Example getExampleInfo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Example findByName(String value) {
		// TODO Auto-generated method stub
		return null;
	}
/*
  private final IExampleRepository exampleRepository;

  @Override
  public Example getExampleInfo(Long id) {
    return exampleRepository.findById(id).orElse(null);
  }

  @Override
  public Example findByName(String value) {
    return exampleRepository.findByName(value);
  }*/
}
