package com.team9.movieservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team9.commonutils.JwtUtils;
import com.team9.commonutils.MD5;
import com.team9.movieservice.entity.User;
import com.team9.movieservice.mapper.UserMapper;
import com.team9.movieservice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team9.servicebase.exceptionhandler.EasyPcException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author QCH
 * @since 2023-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public String login(User user) {
        String password = user.getPassword();
        String email = user.getEmail();
        System.out.println(user);

        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            throw new EasyPcException(20001, "Login Failed");
        }

        //email number is Correct?
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("Email", email);
        User member = baseMapper.selectOne(wrapper);
        if(member == null){
            throw new EasyPcException(20001,"Login Failed, Email number not found");
        }

        //password:
        if(!MD5.encrypt(password).equals(member.getPassword())){
            throw new EasyPcException(20001,"Login Failed");
        }

        String jwtToken = JwtUtils.getJwtToken(member.getUserID().toString(), member.getUsername());

        return jwtToken;
    }

    @Override
    public void register(User user) {
        //獲取註冊的數據
        String email = user.getEmail();
        String nickname = user.getUsername();
        String password = user.getPassword();

        System.out.println(user);
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(nickname)
                || StringUtils.isEmpty(password) ){
            throw new EasyPcException(20001,"Register Failed - Please enter full information");
        }

        //判斷驗證碼
        //獲取驗證碼
//        String redisCode = redisTemplate.opsForValue().get(mobile);
//        if(!code.equals(redisCode)){
//            throw new EasyPcException(20001,"Register Failed - 驗證碼錯誤");
//        }

        //判斷email
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("Email", email);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0){
            throw new EasyPcException(20001,"Failed - Already have this email account");
        }

        //數據添加到數據庫
        User member = new User();
        member.setPassword(MD5.encrypt(password));
        member.setEmail(email);
//        member.setPassword(MD5.encrypt(password)); //encrypt
        member.setUsername(nickname);
        member.setAvatarPath("https://chonghaoqiu-edu.oss-cn-hongkong.aliyuncs.com/2023/06/09/avatar.png");//默認圖像
        baseMapper.insert(member);
    }
}
