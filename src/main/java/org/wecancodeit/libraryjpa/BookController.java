package org.wecancodeit.libraryjpa;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
	@Resource
	BookRepository repository;

	@RequestMapping("/books")
	public String showBooks(Model model) {
		model.addAttribute("books", repository.findAll());
		return "books";

	}
}
