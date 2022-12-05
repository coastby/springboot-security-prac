package com.example.securityprac.controller;

import com.example.securityprac.dto.ReviewRequest;
import com.example.securityprac.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @ApiOperation(value = "리뷰 쓰기")
    @PostMapping
    public String write(@RequestBody ReviewRequest reviewRequest){
        return reviewService.write(reviewRequest);
    }
}
