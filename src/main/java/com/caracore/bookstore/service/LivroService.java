package com.caracore.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.caracore.bookstore.domain.Categoria;
import com.caracore.bookstore.domain.Livro;
import com.caracore.bookstore.dto.LivroDTO;
import com.caracore.bookstore.exception.ObjectNotFoundException;
import com.caracore.bookstore.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private CategoriaService categoriaService;

	public Livro findById(Integer id) {
		Optional<Livro> obj = livroRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
	}

	public List<Livro> findAll(Integer id_cat) {
		categoriaService.findById(id_cat);
		return livroRepository.findAllByCategoria(id_cat);
	}

	public Livro create(Livro obj) {
		obj.setId(null);
		return livroRepository.save(obj);
	}

	public Livro update(Integer id, LivroDTO objDto) {
		Livro obj = findById(id);
		obj.setTitulo(objDto.getTitulo());
		obj.setNome_autor(objDto.getNome_autor());
		obj.setTexto(objDto.getTexto());
		if (objDto.getCategoriaId() != null) {
			Categoria categoria = categoriaService.findById(objDto.getCategoriaId());
			obj.setCategoria(categoria);
		}
		return livroRepository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			livroRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.caracore.bookstore.exception.DataIntegrityViolationException(
					"Objeto não pode ser deletado! Possui associação com outros objetos.");
		}
	}

}
