package com.example.firebasespringboot.controller;

import com.example.firebasespringboot.entity.Person;
import com.example.firebasespringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/person")
public class PersonController {
    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public String savePerson(@RequestBody Person person) throws Exception{
       return productService.savePerson(person);
    }
@GetMapping("/get")
   public Person getPerson(@RequestParam String name) throws Exception {
    return productService.getPerson(name);
}
@GetMapping("/getAll")
    public Iterable<Person> getAllPersons() throws Exception {
    return productService.getAllPersons();
}
}
