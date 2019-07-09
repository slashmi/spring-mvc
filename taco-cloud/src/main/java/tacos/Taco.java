package tacos;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Data;

import tacos.Ingredient;

@Data
public class Taco {
	
private long id;

private Date createdAt;


@NotNull
@Size(min=5, message="Name must be at least 5 characters long")
private String name;



public String getName() {
	return name;
}



public List<String> getIngredients() {
	return ingredients;
}



@NotNull
@Size(min=1, message="You must choose at least 1 ingredient")
private List<String> ingredients;	


}
