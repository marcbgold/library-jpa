package org.wecancodeit.libraryjpa;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenreController {

	@Resource
	GenreRepository repository;

	@RequestMapping("/genres")
	public String showGenres(Model model) {
		model.addAttribute("genres", repository.findAll());
		return "genres";

	}
}
