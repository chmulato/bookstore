package com.caracore.bookstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.caracore.bookstore.domain.Categoria;
import com.caracore.bookstore.domain.Livro;
import com.caracore.bookstore.repositories.CategoriaRepository;
import com.caracore.bookstore.repositories.LivroRepository;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat01 = new Categoria(null, "Informática", "Livros de TI");
		
		Livro l01 = new Livro(null, "Clean code", "Robert C Martin", "Lorem Ipsum", cat01);
		
		cat01.getLivros().addAll(Arrays.asList(l01));
		
		categoriaRepository.saveAll(Arrays.asList(cat01));
		livroRepository.saveAll(Arrays.asList(l01));

	}

}
