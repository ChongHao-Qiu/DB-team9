<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /電影詳細开始 -->
    <section class="container">
      <!-- /圖片 -->
      <div>
        <article class="c-v-pic-wrap" style="height: 200px;">
          <section class="p-h-video-box" id="videoPlay">
            <img :src="baseUrl+movieWebVo.picUrl" :alt="movieWebVo.title" class="dis c-v-pic">
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{movieWebVo.title}}</span>
            </h2>
            <section class="c-attr-mt of">
              <span class="ml10 vam">
                <em class="icon18 scIcon"></em>
                <a class="c-fff vam" title="收藏" href="#" >Add to watchlist</a>
              </span>
            </section>
          </section>
        </aside>
        <div class="clear"></div>
      </div>
      <!-- /評論與評分 -->
      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="课程详情">课程详情</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>课程介绍</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body">
                      <div v-html="courseWebVo.description">
                        
                      </div>
                    </section>
                  </div>
                </div>
                <!-- /课程介绍 -->
                <div class="mt50">
                  <h6 class="c-g-content c-infor-title">
                    <span>用戶評論</span>
                  </h6>
                  <section class="mt20">
                    <div class="lh-menu-wrap">
                      <menu id="lh-menu" class="lh-menu mt10 mr10">
                        <ul>
                          <!-- 評分 -->
                          <li class="lh-menu-stair" v-for="chapter in chapterVideoList" :key="chapter.id">
                            <a href="javascript: void(0)" :title="chapter.title" class="current-1">
                              <em class="lh-menu-i-1 icon18 mr10"></em>{{chapter.title}}
                            </a>
                          </li>
                        </ul>
                      </menu>
                    </div>
                  </section>
                </div>
                <!-- /课程大纲 -->
              </article>
            </div>
          </section>
        </article>
        <div class="clear"></div>
      </div>
    </section>
    <!-- /電影詳細 结束 -->
  </div>
</template>

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
