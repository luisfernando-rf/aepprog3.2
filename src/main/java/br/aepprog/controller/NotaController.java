package br.aepprog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.aepprog.model.Nota;
import br.aepprog.repository.NotaRepository;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

	@Autowired
	private NotaRepository repo;
	
	@GetMapping
	public List<Nota> getAll() {
		return repo.findAll();
	}
	
	@PostMapping
	public String create(@RequestBody Nota nova) {
		repo.save(nova);
		return nova.getId();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		repo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Nota getById(@PathVariable("id") String id) {
		return repo.findById(id).get();
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable("id") String id, @RequestBody Nota nota) {
		if (!id.equals(nota.getId())) {
			throw new RuntimeException("Id da nota está incorreto!");
		}
		repo.save(nota);	
	}
}
