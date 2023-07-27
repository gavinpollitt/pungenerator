package uk.gav.pun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uk.gav.pun.entity.Pun;
import uk.gav.pun.service.PunService;

@RestController
@RequestMapping("/punme")
public class PunController {
    @Autowired
    private PunService punService;
    
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Pun getPun() {
        Pun pun = punService.getPun(); 
        return pun;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void addPun(@RequestBody Pun pun) {
        this.punService.addPun(pun);
    }
}
