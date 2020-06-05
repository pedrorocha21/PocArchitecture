package com.pedro.poc;



import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pedro.poc.domain.entity.Filme;
import com.pedro.poc.domain.entity.Usuario;
import com.pedro.poc.domain.repository.FilmeRepository;
import com.pedro.poc.domain.repository.UsuarioRepository;

import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {



    private final UsuarioRepository usuarioRepository;    

    private final FilmeRepository filmeRepository;
    
    
    public Initializer( UsuarioRepository usuarioRepository,  FilmeRepository filmeRepository) {
        this.usuarioRepository = usuarioRepository;
        this.filmeRepository = filmeRepository;
    }

    @Override
    public void run(String... strings) {
       
        
        Stream.of("Pedro", "Pedro1", "Pedro2",
                "Pedro3").forEach(nome ->                
                usuarioRepository.save(new Usuario(nome))
        );
        
        Stream.of("Filme1", "Filme2", "Filme3",
                "Filme4").forEach(nome ->                
                filmeRepository.save(new Filme(nome, 5))
        );
        

        usuarioRepository.findAll().forEach(System.out::println);
        
        
    }
}