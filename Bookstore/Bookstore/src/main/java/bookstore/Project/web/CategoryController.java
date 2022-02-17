package bookstore.Project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bookstore.Project.domain.Category;
import bookstore.Project.domain.CategoryRepository;

@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository categoryrepository; 
	
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public String listCategories(Model model) {
		model.addAttribute("categories", categoryrepository.findAll());
		return "categorylist";
	}
	
    @RequestMapping(value = "/addcategory")
    public String addCategory(Model model){
    	model.addAttribute("category", new Category());
        return "addcategory";
    }
    
    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String saveCategory(Category category){
    	categoryrepository.save(category);
        return "redirect:categorylist";
    }
}