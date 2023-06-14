import request from '@/utils/request'

export default{
    getMovieList(page, limit,searchObj){
        return request({
            url:`/movieservice/movie/getFrontMovieList/${page}/${limit}`,
            method:'post',
            data:searchObj
        })
    },
    getAllCate(){
        return request({
            url:`/movieservice/movie/getAllCate`,
            method:'get'
        })
    },
    getCourseInfo(id){
        return request({
            url:`/eduservice/coursefront/getFrontCourseInfo/${id}`,
            method:'get'
        })
    },
    getMovieInfo(id){
        return request({
            url:`/movieservice/movie/getMovieInfo/${id}`,
            method:'get'
        })
    },
    getNewMovies(num){
        return request({
            url:`/movieservice/movie/getNewMovies/${num}`,
            method:'get'
        })
    }

}