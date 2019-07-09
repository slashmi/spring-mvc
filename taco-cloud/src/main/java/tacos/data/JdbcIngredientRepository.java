package tacos.data;

import org.springframework.stereotype.Repository;

import tacos.Ingredient;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;




//stereotype annotation defined by String (like @Controller and @Component) which you 
//declare that this class should be automatically discovered by Spring component scanning
//and instantiated as a bean in the Spring application context

@Repository
public class JdbcIngredientRepository implements IngredientRepository{
	
	private JdbcTemplate jdbc;
	
	//when spring creates the JdbcIngredientRepository bean, it injects it with JdbcTemplate
	//via the Autowired annotation. The constructor assigns JdbcTemplate to an instance 
	//variable that will be used in other methods to query and insert into the database
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	/*
	 * The query() method of JdbcTemplate accepts the SQL query as well as an implementation 
	 * of Spring's RowMapper for the purpose of mapping each row in the result set to an object.
	 * RowMapper parameter for both findAll() and findById() is given as a method reference to 
	 * the mapRowToIngredient method.
	 * 
	 
	 * 
	 */
	
	@Override
	public Iterable<Ingredient> findAll(){
		return jdbc.query("select id, name, type from Ingredient",
				this::mapRowToIngredient);
	}
	
	@Override
	public Ingredient findOne(String id) {
		return jdbc.queryForObject(
				"select id, name, type from Ingredient where id=?",
				 this::mapRowToIngredient, id);
	}
	
	    /* Explicit RowMapper implementation is given below:
		 * 
		 * new RowMapper<Ingredient>(){
		 *  public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException{
		 *   return new Ingredient(
		 *      rs.getString("id"),
		 *      rs.getString("name"),
		 *      Ingredient.Type.valueOf(rs.getString("type")))
		 */  
	
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException{
		return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type")));
	}
	
	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update(
				"insert into Ingredient (id, name, type) values (?, ?, ?)",
				ingredient.getId(),
				ingredient.getName(),
				ingredient.getType().toString());
		return ingredient;
	}	
	
				
}


