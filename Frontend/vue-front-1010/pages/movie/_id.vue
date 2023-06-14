<template>
  <div id="aCoursesList" class="bg-fa of">
    <section class="container">
      <div class="movie-container">
        <div class="movie-header" :style="{backgroundImage: 'linear-gradient(to bottom, rgba(255,255,255,0), rgba(255,255,255,1)), url(' + baseUrl + movieWebVo.bannerPath + ')'}">
          <div class="left-section">
            <img :src="baseUrl+movieWebVo.picUrl" :alt="movieWebVo.title" class="movie-poster">
            <h3 class="cast">Cast</h3>
            <div class="actor-list">
              <div class="actor-card" v-for="actor in movieWebVo.actorList" :key="actor.id">
                <img :src="baseUrl+actor.imagePath"  class="actor-img">
                <p class="actor-name">{{actor.name}}</p>
              </div>
            </div>
          </div>
          <div class="movie-info">
            <h2 class="movie-title">{{movieWebVo.title}}</h2>
            <div class="movie-genre">
              <span v-for="genre in movieWebVo.genreList" :key="genre.genreID" class="genre-tag">{{genre.genreName}}</span>
            </div>
            <p class="movie-description">{{movieWebVo.overview}}</p>
          </div>
        </div>
      </div>

      <!---->
      <div class="">
          
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
  margin-top: 300px;
  border-radius: 12px;
  width: 300px;
  height: 450px;
  object-fit: cover;
}

.movie-info {
  margin-top: 300px;
  width: 70%;
  padding-left: 20px;
}

.movie-title {
  margin-top: 20px;
  font-size: 70px;
  line-height: 0.9;
  font-weight: bold;
  color: #000;
  margin-bottom: 10px;
  margin-right: 38px;
  word-break: keep-all;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

.movie-genre {
  margin-top: 50px;
}

.genre-tag {
  border: 2px solid white;
  padding: 6px 11px;
  border-radius: 50px;
  font-size: 20px;
  color: #fff;
  background-color: black;
  margin-right: 12px;
  font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  justify-content: center; 
  align-items: center;

}

.movie-description {
  margin: 20px 0;
  font-size: 20px;
  line-height: 1.6;
  color: #000;
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  margin-right: 38px;
  word-break: keep-all;

}
.left-section {
  display: flex;
  flex-direction: column;
  margin-left: 38px;
  align-items: flex-start; 
}

.cast {
  font-size: 23px;
  font-weight: bold;
  text-align: left;
  margin-top: 80px;
  margin-left: 3px;
  font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

.actor-list {
  display: flex;
  overflow-x: scroll;
}

.actor-card {
  width: 120px;
  text-align: center;
  margin-right: 25px;
}

.actor-img {
  margin-top: 20px;
  width: 100%;
  height: auto;
  object-fit: cover;
  border-radius: 12px;

}

.actor-name {
  font-size: 16.5px;
  margin-top: 4px;
  line-height: 1.6;
  color: #000;
  word-break: keep-all;
  font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

.movie-info {
  margin-left: 20px; /* 设定与movie-poster的距离 */
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
