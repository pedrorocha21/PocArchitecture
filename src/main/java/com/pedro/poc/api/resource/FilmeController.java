package com.pedro.poc.api.resource;






import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.poc.api.dto.FilmeDTO;
import com.pedro.poc.service.FilmeService;

@RestController

@RequestMapping("/api/filme")
public class FilmeController {
	
	@Autowired
    FilmeService filmeService;
	
    
    
    @GetMapping
    private ResponseEntity<List<FilmeDTO>> getAllUsuarios() {
        return new ResponseEntity<>(filmeService.getAllFilme(), HttpStatus.OK);
    }    
    
    @GetMapping("/{id}")
    private ResponseEntity<FilmeDTO> getUsuario(@PathVariable("id") Long id) {
    	FilmeDTO dto = filmeService.getById(id);
    	if(dto==null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(filmeService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Long> delete(@PathVariable("id") Long id) {    	
    	
        boolean isRemoved = filmeService.delete(id);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    
    @PostMapping
    private ResponseEntity<FilmeDTO> saveUsuario(@RequestBody FilmeDTO filme) {
        return new ResponseEntity<>(filmeService.saveOrUpdate(filme), HttpStatus.OK);
    }    
    


	
	
}
