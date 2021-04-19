package com.app.covid.controller;

import com.app.covid.constants.ResourceMapping;
import com.app.covid.domain.Example;
import com.app.covid.service.IExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ResourceMapping.EXAMPLE)
public class ExampleController {

  private final IExampleService exampleService;

  @GetMapping("/{id}")
  public Example getExample(@PathVariable("id") Long id) {
    return exampleService.getExampleInfo(id);
  }
  //EN INSOMNIA O POSTMAN:
  // - TIPO: GET
  // - URL: http://localhost:8080/example/1

  @PostMapping()
  public Example getExample(@RequestBody Example example) {
    return exampleService.findByName(example.getValueExample());
  }
  //EN INSOMNIA O POSTMAN:
  // - TIPO: POST
  // - URL: http://localhost:8080/example
  // - en Body, cambiarlo a JSON
  /* Dentro del JSON:
  {
    "id": 1,
    "valueExample": "example",
    "description": "this is an example"
  }
  * */

}
