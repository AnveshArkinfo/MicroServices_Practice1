package com.MicroServices.RatingDataService.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroServices.RatingDataService.Model.Rating;

@RestController
@RequestMapping("/ratings")
public class RatingResource {
	
	@RequestMapping("/{movieName}")
	public Rating getRatings(@PathVariable String movieName) {
		
		return new Rating(movieName, 4);
	}
	
}
