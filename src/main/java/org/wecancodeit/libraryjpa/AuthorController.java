package org.wecancodeit.libraryjpa;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {
	@Resource
	AuthorRepository repository;

	@RequestMapping("/authors")
	public String showAuthors(Model model) {
		model.addAttribute("authors", repository.findAll());
		return "authors";

	}
}
