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

import com.pedro.poc.api.dto.UsuarioDTO;
import com.pedro.poc.service.LocacaoService;
import com.pedro.poc.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
    UsuarioService usuarioService;
	
	@Autowired
    LocacaoService locacaoService;	
	

    @GetMapping
    private ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        return new ResponseEntity<>(usuarioService.getAllUsuario(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    private ResponseEntity<UsuarioDTO> getUsuario(@PathVariable("id") Long id) {
    	UsuarioDTO dto = usuarioService.getById(id);
    	if(dto==null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(usuarioService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Long> delete(@PathVariable("id") Long id) {    	
    	
        boolean isRemoved = usuarioService.delete(id);
        if (!isRemoved) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<UsuarioDTO> saveUsuario(@RequestBody UsuarioDTO usuario) {
        return new ResponseEntity<>(usuarioService.saveOrUpdate(usuario), HttpStatus.OK);
    }
    

	
}
