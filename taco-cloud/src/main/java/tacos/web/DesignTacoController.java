package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.data.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
private final IngredientRepository ingredientRepo;	
	
@Autowired
public DesignTacoController(IngredientRepository ingredientRepo) {
	
	this.ingredientRepo = ingredientRepo;
	
	
}
	
	
	
@GetMapping
public String showDesignForm(Model model) {
		/*
		 * List<Ingredient> ingredients = Arrays.asList( new Ingredient("FLTO",
		 * "Flour Tortilla", Type.WRAP), new Ingredient("COTO", "Corn Tortilla",
		 * Type.WRAP), new Ingredient("TMTO", "Diced Tomatoes", Type.PROTEIN), new
		 * Ingredient("LETC", "Lettuce", Type.PROTEIN), new Ingredient("CHED",
		 * "CHEDDAR", Type.CHEESE) );
		 */
	
	List<Ingredient> ingredients = new ArrayList<Ingredient>();
	ingredientRepo.findAll().forEach(i -> ingredients.add(i));
	
	
	Type[] types = Ingredient.Type.values();
	
	for(Type type : types) {
		model.addAttribute(type.toString().toLowerCase(),
				           filterByType(ingredients, type));
	}
	model.addAttribute("design", new Taco());
	return "design";
}

private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type)
{
	return ingredients.stream().filter(x -> x.getType().equals(type))
			                               .collect(Collectors.toList());
}

@PostMapping
public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors) {
	
	
	if(errors.hasErrors()) {
		System.out.println("inside hasErrors method ");
		
		return "design";
	}
	
	//Save the taco design
	//We will do this in chapter 3
	log.info("Processing design: " + design);
	return "redirect:/orders/current";
	
}
			
}
	


