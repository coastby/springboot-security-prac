package com.example.securityprac.service;

import com.example.securityprac.dto.ReviewRequest;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    public String write(ReviewRequest reviewRequest){
        return "리뷰등록이 되었습니다.";
    }
}
