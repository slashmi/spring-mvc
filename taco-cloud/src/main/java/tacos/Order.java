package tacos;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.CreditCardNumber;
import java.util.Date;
import lombok.Data;

@Data
public class Order {
	
	private long id;
	
	private Date placedAt;
	
	
	@NotBlank(message="Name is required")
	private String name;
	
	private String street;
	private String city;
	private String state;
	
	private String zip;
	
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
			           message="Must be formatted MM/YY")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;

}
