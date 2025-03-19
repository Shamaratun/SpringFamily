package org.isdb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.RowMapper;

import org.isdb.model.Product;

@Repository
public class ProductRepository {

	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert productInsert;

	public ProductRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

		this.productInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("ProductSB")
				.usingGeneratedKeyColumns("id");
	}

	public int save(Product product) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", product.getName());
		parameters.put("email", product.getEmail());
		parameters.put("description", product.getDescription());
		parameters.put("quantity", product.getQuantity());
		parameters.put("address", product.getDescription());
		parameters.put("purchaseDate", product.getPurchaseDate());
		parameters.put("sellDate", product.getSellDate());

		parameters.put("price", product.getPrice());

		Number key = productInsert.executeAndReturnKey(parameters);
		return key.intValue();
	}

	public Optional<Product> findById(int id) {
		try {
			String sql = "SELECT * FROM ProductSB WHERE id = ?";
			return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public List<Product> findAll() {
		String sql = "SELECT * FROM ProductSB";
		return jdbcTemplate.query(sql, new EmployeeRowMapper());
	}

	public int update(Product product) {
		String sql = "UPDATE ProductSB SET name = ?, email = ?, description = ?, "
				+ "quantity = ?, address = ?, pruchaseDate = ?, sellDate = ?, price = ? WHERE id = ?";

		return jdbcTemplate.update(sql, product.getName(), product.getEmail(), product.getDescription(),
				product.getQuantity(), product.getAddress(), product.getPurchaseDate(), product.getSellDate(),
				product.getPrice(), product.getId());
	}

	public int deleteById(int id) {
		String sql = "DELETE FROM ProductSB WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	public boolean existsById(int id) {
		String sql = "SELECT COUNT(*) FROM ProductSB WHERE id = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
		return count != null && count > 0;
	}

	public List<Product> findByName(String name) {
		String sql = "SELECT * FROM ProductSB WHERE name like ?";
		return jdbcTemplate.query(sql, newProductRowMapper(), "%" + name + "%");
	}

	private static class ProductRowMapper implements RowMapper<Product> {

		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
					rs.getString("description"), rs.getInt("quantity"), rs.getString("address"),
					rs.getObject("purchaseDate", LocalDate.class), rs.getObject("sellDate", LocalDate.class),
					rs.getDouble("price"));
		}
	}

	public Product saveAndReturnEmp(Product product) {
		try (Connection connection = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO ProductSB (name, email, description, quantity, address, purchaseDate,sellDate, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {

			// Set the parameters
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getEmail());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setInt(4, product.getQuantity());
			preparedStatement.setString(5, product.getAddress());
			preparedStatement.setObject(6, product.getPurchaseDate());
			preparedStatement.setObject(7, product.getSellDate());

			preparedStatement.setDouble(8, product.getPrice());

			// Execute the insert
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating product failed, no rows affected.");
			}

			// Get the generated ID
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int id = generatedKeys.getInt(1);

					// Set the ID in the product object
					Product savedEmployee = new Product(id, product.getName(), product.getEmail(),
							product.getDescription(), product.getQuantity(), product.getAddress(),
							product.getPurchaseDate(), product.getSellDate(), product.getPrice());

					return savedEmployee;
				} else {
					throw new SQLException("Creating product failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error saving product", e);
		}
	}

}