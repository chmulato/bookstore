package com.caracore.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caracore.bookstore.domain.Categoria;
import com.caracore.bookstore.domain.Livro;
import com.caracore.bookstore.repositories.CategoriaRepository;
import com.caracore.bookstore.repositories.LivroRepository;

@Service
public class DBService {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	LivroRepository livroRepository;

	public void instanciaBaseDeDados() {

		Categoria cat01 = new Categoria(null, "Informática", "Livros de TI");
		Categoria cat02 = new Categoria(null, "Ficção Científica", "Livros de Ficção Científica");
		Categoria cat03 = new Categoria(null, "Biografias", "Livros de Biografias");

		Livro liv01 = new Livro(null, "Clean code", "Robert C Martin", "Lorem Ipsum", cat01);
		Livro liv02 = new Livro(null, "Engenharia de Software", "Louis V. Gerstner", "Lorem Ipsum", cat01);
		Livro liv03 = new Livro(null, "The Time Machine", "H.G. Wells", "Lorem Ipsum", cat02);
		Livro liv04 = new Livro(null, "The War of the Worlds", "H.G. Wells", "Lorem Ipsum", cat02);
		Livro liv05 = new Livro(null, "I, Robot", "Isaac Asimov", "Lorem Ipsum", cat02);

		cat01.getLivros().addAll(Arrays.asList(liv01, liv02));
		cat02.getLivros().addAll(Arrays.asList(liv03, liv04, liv05));

		categoriaRepository.saveAll(Arrays.asList(cat01, cat02, cat03));
		livroRepository.saveAll(Arrays.asList(liv01, liv02, liv03, liv04, liv05));

	}
}
