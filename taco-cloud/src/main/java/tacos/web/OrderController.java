package tacos.web;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

@GetMapping("/current")
public String orderForm(Model model){
 System.out.println("inside orderForm method");	
 model.addAttribute("order", new Order());
 return "orderForm";
 }

@PostMapping
public String processOrder(@Valid Order order, Errors errors) {
	if(errors.hasErrors()) {
		return "orderForm";
	}
	System.out.println("inside processOrder");
	log.info("Order submitted: " + order);
	return "redirect:/";
 }
}