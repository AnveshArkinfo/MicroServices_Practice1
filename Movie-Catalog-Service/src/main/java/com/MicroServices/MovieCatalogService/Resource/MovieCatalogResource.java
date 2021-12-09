package com.MicroServices.MovieCatalogService.Resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.MicroServices.MovieCatalogService.Model.CatalogItem;
import com.MicroServices.MovieCatalogService.Model.Movie;
import com.MicroServices.MovieCatalogService.Model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" +userId, UserRating.class);
		
		return ratings.getUserRating().stream().map(rating -> {
			
		Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getRating(), Movie.class);
		return new CatalogItem(movie.getName() , "Sample Desc", rating.getRating());
		})
			.collect(Collectors.toList());

	}
	
}