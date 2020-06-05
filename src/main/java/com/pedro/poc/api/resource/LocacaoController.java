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

import com.pedro.poc.api.dto.LocacaoDTO;
import com.pedro.poc.service.LocacaoService;

@RestController

@RequestMapping("/api/locacao")
public class LocacaoController {
	
	
	@Autowired
    LocacaoService locacaoService;	
    
    
    @PostMapping()
    private ResponseEntity<LocacaoDTO> savelocacao(@RequestBody LocacaoDTO locacao) {
        return new ResponseEntity<>(locacaoService.save(locacao), HttpStatus.OK);
    }
    
    @GetMapping("/renova/{id}")
    private ResponseEntity<List<LocacaoDTO>> renovalocacao(@PathVariable("id") Long id) {
        return new ResponseEntity<>(locacaoService.renova(id), HttpStatus.OK);
    }
    
    @GetMapping("/recebe/{id}")
    private ResponseEntity<List<LocacaoDTO>> recebelocacao(@PathVariable("id") Long id) {
        return new ResponseEntity<>(locacaoService.recebe(id), HttpStatus.OK);
    }
    
    @GetMapping("/usuario/{id}")
    private ResponseEntity<List<LocacaoDTO>> getUsuarioLocacoes(@PathVariable("id") Long idUsuario) {
        return new ResponseEntity<>(locacaoService.getAllLocacaoPorUsuario(idUsuario), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    private ResponseEntity<Long> deleteLocacao(@PathVariable("id") Long id) {    	
    	
        boolean isRemoved = locacaoService.delete(id);
        if (!isRemoved) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
        
    @GetMapping("/filme/{id}")
    private ResponseEntity<List<LocacaoDTO>> getFilmeLocacoes(@PathVariable("id") Long id) {
        return new ResponseEntity<>(locacaoService.getAllLocacaoPorFilme(id), HttpStatus.OK);
    }  
    
    

	
	
}
