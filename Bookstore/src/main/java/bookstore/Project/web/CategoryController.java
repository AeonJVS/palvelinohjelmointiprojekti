package bookstore.Project.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bookstore.Project.domain.Category;
import bookstore.Project.domain.CategoryRepository;


@CrossOrigin
@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository categoryrepository; 
	
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public @ResponseBody List<Category> getCategoriesRest() {
		return (List<Category>) categoryrepository.findAll();
	}
	
	@RequestMapping(value = "/categorylist/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long categoryId) {	
		return categoryrepository.findById(categoryId);
	} 
    
	@RequestMapping(value = "/categorylist", method = RequestMethod.POST)
	public @ResponseBody Category saveCategoryRest(@RequestBody Category category) {	
		return categoryrepository.save(category);
	}
}