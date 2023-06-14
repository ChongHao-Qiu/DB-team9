<template>
    <div class="main">
      <div class="title">
        <a href="/login">sign in</a>
        <span>·</span>
        <a class="active" href="/register">sign up</a>
      </div>
  
      <div class="sign-up-container">
        <el-form ref="userForm" :model="params">
  
          <el-form-item class="input-prepend restyle" prop="username" :rules="[{ required: true, message: 'please enter name', trigger: 'blur' }]">
            <div>
              <el-input type="text" placeholder="Your nickname" v-model="params.username"/>
              <i class="iconfont icon-user"/>
            </div>
          </el-form-item>
  
          <el-form-item class="input-prepend restyle no-radius" prop="mobile" :rules="[{ required: true, message: 'Please enter your email', trigger: 'blur' },{validator: checkEmail, trigger: 'blur'}]">
            <div>
              <el-input type="text" placeholder="Your email account" v-model="params.email"/>
              <i class="iconfont icon-phone"/>
            </div>
          </el-form-item>
  
          <el-form-item class="input-prepend" prop="password" :rules="[{ required: true, message: 'please enter passowrd', trigger: 'blur' }]">
            <div>
              <el-input type="password" placeholder="Setting Password" v-model="params.password"/>
              <i class="iconfont icon-password"/>
            </div>
          </el-form-item>
  
          <div class="btn">
            <input type="button" class="sign-up-button" value="sign up" @click="submitRegister()">
          </div>
          <!-- <p class="sign-up-msg">
            点击 “注册” 即表示您同意并愿意遵守简书
            <br>
            <a target="_blank" href="http://www.jianshu.com/p/c44d171298ce">用户协议</a>
            和
            <a target="_blank" href="http://www.jianshu.com/p/2ov8x3">隐私政策</a> 。
          </p> -->
        </el-form>
        <!-- 更多注册方式 -->
        <!-- <div class="more-sign">
          <h6>社交帐号直接注册</h6>
          <ul>
            <li><a id="weixin" class="weixin" target="_blank" href="http://huaan.free.idcfengye.com/api/ucenter/wx/login"><i
              class="iconfont icon-weixin"/></a></li>
            <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li>
          </ul>
        </div> -->
      </div>
    </div>
  </template>
  
  <script>
    import '~/assets/css/sign.css'
    import '~/assets/css/iconfont.css'
  
    import register from '@/api/register'

    export default {
      layout: 'sign',
      data() {
        return {
          params: { //封装注册输入数据
            email: '',
            // code: '',  //验证码
            username: '',
            password: ''
          },
          sending: true,      //是否发送验证码
          second: 60,        //倒计时间
          codeTest: '获取验证码'
        }
      },
      methods: {
        submitRegister(){
            register.registerUser(this.params)
                .then(response =>{
                    this.$message({
                        type:'success',
                        message:'Register Success'
                    })
                    this.$router.push({path:'/login'})
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
  