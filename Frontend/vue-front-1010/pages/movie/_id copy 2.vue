<template>
  <div id="aCoursesList" class="bg-fa of">
    <section class="container">
      <div class="movie-container">
        <div class="movie-header" :style="{backgroundImage: 'linear-gradient(to bottom, rgba(255,255,255,0), rgba(255,255,255,1)), url(' + baseUrl + movieWebVo.bannerPath + ')'}">
          <img :src="baseUrl+movieWebVo.picUrl" :alt="movieWebVo.title" class="movie-poster">
          <div class="movie-info">
            <h2 class="movie-title">{{movieWebVo.title}}</h2>
            <div class="movie-genre">
              <span class="genre-tag">Action</span>
              <span class="genre-tag">Adventure</span>
            </div>
            <p class="movie-description">{{movieWebVo.overview}}</p>
            <div class="actor-list">
              <div class="actor-card" v-for="actor in movieWebVo.actors" :key="actor.id">
                <img :src="actor.image" :alt="actor.name" class="actor-img">
                <p class="actor-name">{{actor.name}}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="mt20 c-infor-box">
        <!-- /课程介绍 -->
      </div>
    </section>
  </div>
</template>

<style scoped>
.movie-container {
  color: black;
  font-family: Arial, sans-serif;
}

.movie-header {
  display: flex;
  background: #fff;
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  padding: 20px;
}

.movie-poster {
  border-radius: 10px;
  width: 300px;
  height: 450px;
  object-fit: cover;
}

.movie-info {
  width: 70%;
  padding-left: 20px;
}

.movie-title {
  margin-top: 20px;
  font-size: 70px;
  font-weight: bold;
  color: #fff;
  margin-bottom: 10px;
}

.movie-genre {
  margin-top: 50px;
}

.genre-tag {
  border: 2px solid white;
  padding: 5px 10px;
  border-radius: 50px;
  font-size: 22px;
  color: #fff;
  background-color: black;
  margin-right: 10px;
}

.movie-description {
  margin: 20px 0;
  font-size: 20px;
  line-height: 1.6;
  color: #000;
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.actor-list {
  display: flex;
  overflow-x: scroll;
}

.actor-card {
  width: 120px;
  text-align: center;
  margin-right: 10px;
}

.actor-img {
  width: 100%;
  height: auto;
}

.actor-name {
  font-size: 14px;
  margin-top: 5px;
}
</style>

<script>
import courseApi from '@/api/course'
import vodApi from '@/api/vod'
import orderApi from '@/api/order'
export default {
  data(){
    return{
      courseWebVo:{},
      movieWebVo:{},
      baseUrl:'https://image.tmdb.org/t/p/original/',
    }
  },
  created(){
    this.getMovieInfo()
  },
  methods:{
    getMovieInfo(){
      courseApi.getMovieInfo(this.$route.params.id)
        .then(response =>{
          this.movieWebVo = response.data.data.movieVo
        })
    },
  },
};
</script>
