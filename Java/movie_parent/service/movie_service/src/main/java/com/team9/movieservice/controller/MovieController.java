package com.team9.movieservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team9.commonutils.R;
import com.team9.movieservice.entity.Movie;
import com.team9.movieservice.entity.MovieGenre;
import com.team9.movieservice.entity.frontVo.MovieFrontVo;
import com.team9.movieservice.entity.frontVo.MovieShowVo;
import com.team9.movieservice.service.MovieGenreService;
import com.team9.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
@RestController
@CrossOrigin
@RequestMapping("/movieservice/movie")
public class MovieController {


    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieGenreService movieGenreService;

    @GetMapping("getBanner")
    public R getBanner(){
        List<String> picList = movieService.getTopBanner();
        return R.ok().data("picList",picList);
    }

    @GetMapping("getAllCate")
    private R getAllCate(){
        List<MovieGenre> list = movieGenreService.getAllGenre();
        return R.ok().data("list",list);
    }


    @PostMapping("getFrontMovieList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) MovieFrontVo movieFrontVo){
        Page<Movie> pageCourse = new Page<>(page, limit);

        Map<String, Object> map = movieService.getCourseFrontList(pageCourse, movieFrontVo);

        return R.ok().data(map);
    }

    @GetMapping("getMovieInfo/{id}")
    public R getMovieInfo(@PathVariable String id){
        MovieShowVo movie = movieService.getMovieInfoById(id);
        return R.ok().data("movieVo",movie);
    }

    @GetMapping("getNewMovies/{num}")
    public R getTopFourMovies(@PathVariable Integer num){
        List<Movie> list = movieService.getNewMoviesByNum(num);
        return R.ok().data("movieList",list);
    }


}

