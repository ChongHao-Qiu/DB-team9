<template>
    <div class="main">
      <div class="title">
        <a class="active" href="/login">sign in</a>
        <span>·</span>
        <a href="/register">sign up</a>
      </div>
  
      <div class="sign-up-container">
        <el-form ref="userForm" :model="user">
  
          <el-form-item class="input-prepend restyle" prop="email" :rules="[{ required: true, message: 'please enter your email', trigger: 'blur' },{validator: checkEmail, trigger: 'blur'}]">
            <div >
              <el-input type="text" placeholder="Your email account" v-model="user.email"/>
              <i class="iconfont icon-phone" />
            </div>
          </el-form-item>
  
          <el-form-item class="input-prepend" prop="password" :rules="[{ required: true, message: 'please enter passowrd', trigger: 'blur' }]">
            <div>
              <el-input type="password" placeholder="Password" v-model="user.password"/>
              <i class="iconfont icon-password"/>
            </div>
          </el-form-item>
  
          <div class="btn">
            <input type="button" class="sign-in-button" value="Login" @click="submitLogin()">
          </div>
        </el-form>
        <!-- 更多登录方式 -->
        <!-- <div class="more-sign">
          <h6>社交帐号登录</h6>
          <ul>
            <li><a id="weixin" class="weixin" target="_blank" href="http://qy.free.idcfengye.com/api/ucenter/weixinLogin/login"><i class="iconfont icon-weixin"/></a></li>
            <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li>
          </ul>
        </div> -->


      </div>
  
    </div>
  </template>
  
  <script>
    import '~/assets/css/sign.css'
    import '~/assets/css/iconfont.css'
  
    import cookie from 'js-cookie'

    import loginApi from '@/api/login'
    export default {
      layout: 'sign',
  
      data () {
        return {
          user:{
            email:'',
            password:''
          },
          //用户信息
          loginInfo:{},
          token:''
        }
      },
  
      methods: {
        submitLogin(){
           //Step1: 调用接口，返回token字符串
            loginApi.submitLogin(this.user)
              .then(response =>{
                //Step2: 获取token 放入cookie中
                this.token = response.data.data.token
                console.log(this.token)
                cookie.set('guli_token',response.data.data.token,{domain:'localhost'})
                //Step3:創建攔截器
                //已經在request.js中實現
                //Step4: 根據token 獲取用戶信息，為了首頁顯示
                loginApi.getUserInfo()
                  .then(response =>{
                    console.log("response:"+response)
                    this.loginInfo = JSON.stringify(response.data.data.userInfo)
                    console.log("!!info:"+JSON.stringify(this.loginInfo))
                    cookie.set('guli_ucenter', this.loginInfo,{domain:'localhost'})
                    
                    //跳轉登陸頁面
                    window.location.href = "/"
                  })
              })
        },
        checkEmail(rule, value, callback) {
          if (!(/^[\w.%+-]+@[\w.-]+\.[A-Za-z]{2,}$/).test(value)) {
            return callback(new Error('Email账号格式不正确'));
          }
          return callback();
        }

      }
    }
  </script>
  <style> 
     .el-form-item__error{
      z-index: 9999999;
    }
  </style>