/**
 * 
 */
package com.product.post.controller;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.product.post.bo.ProductBo;
import com.product.post.dto.ProductDto;
import com.product.post.dto.UserDto;

/**
 * @author yuvaraj
 *
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

	/**
	 * Add new product.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/addProduct")
	public String addProduct(Model model) {
		checkUserAuthStatus();
		model.addAttribute("addProductForm", new ProductBo());
		return "addProduct";
	}

	/**
	 * Add new product.
	 * 
	 * @param addProductForm
	 * @return
	 */
	@PostMapping(value = "/addProduct")
	public String saveProduct(@ModelAttribute("addProductForm") ProductBo addProductForm) {
		checkUserAuthStatus();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject inputJson = new JSONObject();
		inputJson.put("name", addProductForm.getName());
		inputJson.put("price", addProductForm.getPrice());
		inputJson.put("image", addProductForm.getImage());
		inputJson.put("description", addProductForm.getDescription());
		inputJson.put("category", addProductForm.getCategory());
		inputJson.put("width", addProductForm.getWidth());
		inputJson.put("height", addProductForm.getHeight());
		inputJson.put("weight", addProductForm.getWeight());
		inputJson.put("quantity", addProductForm.getQuantity());
		inputJson.put("shippingFee", addProductForm.getShippingFee());
		HttpEntity<String> request = new HttpEntity<>(inputJson.toString(), headers);

		new RestTemplate().postForEntity("http://localhost:8001/product/save", request, ProductDto.class);

		return "redirect:/product/";
	}

	/**
	 * Update product detail.
	 * 
	 * @param model
	 * @param productId
	 * @return
	 */
	@GetMapping(value = "/updateProduct/{productId}")
	public String updateProduct(Model model, @PathVariable Long productId) {
		checkUserAuthStatus();
		ResponseEntity<ProductDto> response = new RestTemplate()
				.getForEntity("http://localhost:8001/product/details/" + productId, ProductDto.class);

		model.addAttribute("updateProductForm", response.getBody());
		return "updateProduct";
	}

	/**
	 * Update product detail.
	 * 
	 * @param updateProductForm
	 * @return
	 */
	@PostMapping(value = "/updateProduct")
	public String updateProduct(@ModelAttribute("updateProductForm") ProductBo updateProductForm) {
		checkUserAuthStatus();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject inputJson = new JSONObject();
		inputJson.put("id", updateProductForm.getId());
		inputJson.put("name", updateProductForm.getName());
		inputJson.put("price", updateProductForm.getPrice());
		inputJson.put("image", updateProductForm.getImage());
		inputJson.put("description", updateProductForm.getDescription());
		inputJson.put("category", updateProductForm.getCategory());
		inputJson.put("width", updateProductForm.getWidth());
		inputJson.put("height", updateProductForm.getHeight());
		inputJson.put("weight", updateProductForm.getWeight());
		inputJson.put("quantity", updateProductForm.getQuantity());
		inputJson.put("shippingFee", updateProductForm.getShippingFee());
		HttpEntity<String> request = new HttpEntity<>(inputJson.toString(), headers);

		new RestTemplate().put("http://localhost:8001/product/update", request, ProductDto.class);

		return "redirect:/product/";
	}

	/**
	 * Delete product by product id.
	 * 
	 * @param productId
	 * @return
	 */
	@DeleteMapping("/remove/{productId}")
	public String removeProduct(@PathVariable Long productId) {
		checkUserAuthStatus();
		new RestTemplate().delete("http://localhost:8001/product/remove/" + productId);
		return "redirect:/product/";
	}

	/**
	 * List all products.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String getProducts(Model model) {
		checkUserAuthStatus();
		ResponseEntity<ProductDto[]> response = new RestTemplate().getForEntity("http://localhost:8001/product/",
				ProductDto[].class);
		List<ProductDto> productDtos = Arrays.asList(response.getBody());
		model.addAttribute("products", productDtos);
		return "products";
	}

	/**
	 * Logout service.
	 * 
	 * @return
	 */
	@GetMapping(value = "/logout")
	public String logoutUser() {
		HttpStatus response = new RestTemplate().getForEntity("http://localhost:8000/user/logout", UserDto.class)
				.getStatusCode();
		return "redirect:http://localhost:8101/user/login";
	}

	/**
	 * Checking authentication while access api.
	 * 
	 * @return
	 */
	private String checkUserAuthStatus() {
		try {
			HttpStatus response = new RestTemplate().getForEntity("http://localhost:8000/user/status", UserDto.class)
					.getStatusCode();
			if (response.FORBIDDEN == HttpStatus.FORBIDDEN) {
				return "redirect:http://localhost:8101/user/login";
			}
		} catch (HttpStatusCodeException exception) {
			if (exception.getStatusCode().FORBIDDEN == HttpStatus.FORBIDDEN) {
				return "redirect:http://localhost:8101/user/login";
			}
		}

		return "";
	}
//
//	@GetMapping("/category/{categoryId}/product/{productId}")
//	public String getProducts(@PathVariable Long categoryId, @PathVariable Long productId) {
//		return "";
//	}

}
