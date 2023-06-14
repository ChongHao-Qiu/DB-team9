package com.team9.movieservice.service.impl;

import com.team9.movieservice.entity.Review;
import com.team9.movieservice.mapper.ReviewMapper;
import com.team9.movieservice.service.ReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

}
