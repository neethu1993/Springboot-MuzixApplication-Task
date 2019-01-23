/**
 * Controller to control the Muzix Application
 */
package com.stackroute.controller;

import com.stackroute.domain.Muzix;
import com.stackroute.exceptions.MuzixAlreadyExistsException;
import com.stackroute.exceptions.MuzixNotFoundException;
import com.stackroute.service.MuzixService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController //used to enable @Controller, and  @Responsebody
@RequestMapping(value="api/v1")
@Api(value="Musicstore", description="Operations pertaining to tracks")
public class MuzixController extends ResponseEntityExceptionHandler {

    //A variable of type MuzixService
    MuzixService muzixService;

    //Autowired constructor
    @Autowired
    public MuzixController(MuzixService muzixService) {
        this.muzixService = muzixService;
    }

    //Method to perform POST operation
    @PostMapping("muzix")
    public ResponseEntity<?> saveMuzix(@RequestBody Muzix muzix) throws MuzixAlreadyExistsException{
        muzixService.saveMuzix(muzix);
        return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
    }

    //Method to perform GET operation
    @GetMapping("muzix")
    public ResponseEntity<?> getAllMuzixs() {
        return new ResponseEntity<List<Muzix>>(muzixService.getAllMuzixs(), HttpStatus.OK);
    }

    //Method to perform PUT operation
    @PutMapping("muzix")
    public ResponseEntity<?> updateMuzix(@RequestBody Muzix muzix) throws MuzixAlreadyExistsException{
        muzixService.updateMuzix(muzix);
        return new ResponseEntity<String>("Successfully updated", HttpStatus.CREATED);
    }

    //Method to perform DELETE operation
    @DeleteMapping("muzix/{trackId}")
    public ResponseEntity<?> deleteMuzix(@PathVariable int trackId) throws MuzixNotFoundException{
        muzixService.removeMuzix(trackId);
        return new ResponseEntity<String>("Successfully Deleted", HttpStatus.CREATED);
    }

    //Mehtod to track by name
    @GetMapping("muzixname/{trackName}")
    public ResponseEntity<?> getByName(@PathVariable String trackName) throws MuzixNotFoundException {
        return new ResponseEntity<List<Muzix>>(muzixService.trackByTrackName(trackName), HttpStatus.OK);
    }

}