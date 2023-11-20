package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Compromisso;
import com.example.demo.repository.CompromissoRepository;


@RestController
@RequestMapping("/compromisso")
public class CompromissoController {
	@Autowired
	CompromissoRepository repo;
	
	@GetMapping()
	public ResponseEntity<List<Compromisso>>getCompromisso(){
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Compromisso> inserirCompromisso(@RequestBody Compromisso compromisso){
		Compromisso ct = repo.save(compromisso);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Compromisso> alterarCompromisso(@PathVariable("id") Long idcompromisso,
			@RequestBody Compromisso compromisso){
		Optional<Compromisso> opCompromisso = repo.findById(idcompromisso);
				try {
					Compromisso ct = opCompromisso.get();
					ct.setLocal(compromisso.getLocal());
					ct.setContato(compromisso.getContato());
					ct.setData(compromisso.getData());
					ct.setHora(compromisso.getHora());
					ct.setStatus(compromisso.getStatus());
					ct.setIdcontato(compromisso.getIdcontato());
					repo.save(ct);
					return ResponseEntity.status(HttpStatus.OK).body(ct);
				}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Compromisso>getUmCompromisso(@PathVariable("id")Long id){
		Optional<Compromisso> opCompromisso = repo.findById(id);
		try {
			Compromisso ct = opCompromisso.get();
			return ResponseEntity.status(HttpStatus.OK).body(ct);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
		
	@GetMapping("/local/{local}")
	public ResponseEntity<List<Compromisso>> getCompromissosPorLocal(@PathVariable("local") String local) {
	    List<Compromisso> compromissos = repo.findByLocal(local);
	    if (compromissos.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(compromissos);
	    }
	}
	
	@GetMapping("/data/{datacomp}")
	public ResponseEntity<List<Compromisso>> getCompromissosPorData(@PathVariable("datacomp") String data) {
	    List<Compromisso> compromissos = repo.findByData(data);
	    
	    if (compromissos.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(compromissos);
	    }
	}
	
	@GetMapping("/contato/{contato}")
	public ResponseEntity<List<Compromisso>> getCompromissosContato(@PathVariable("contato") String contato) {
	    List<Compromisso> compromissos = repo.findByContato(contato);
	    
	    if (compromissos.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(compromissos);
	    }
	}

		
	@DeleteMapping("/{id}")
	public ResponseEntity<Compromisso>DeletarUmCompromisso(@PathVariable("id")Long id){
		Optional<Compromisso> opCompromisso = repo.findById(id);
		try {
			Compromisso ct = opCompromisso.get();
			repo.delete(ct);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ct);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
}
