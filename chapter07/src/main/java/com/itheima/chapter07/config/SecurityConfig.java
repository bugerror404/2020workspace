package com.itheima.chapter07.config;

import com.itheima.chapter07.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;


@EnableWebSecurity //开启MVC Security技术支持
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //密码需要设置编码器
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        //使用UserDetails进行身份认证
        auth.userDetailsService(userDetailService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //需要对static文件夹下静态资源进行统一放行
                .antMatchers("/login/**").permitAll()
                .antMatchers("/").permitAll() //路径为“/”的请求直接放行
                .antMatchers("/").permitAll()//路径为“/”的请求直接放行
                .antMatchers("/detail/common/**").hasRole("common") //只有用户角色为common（ROLE_common）才允许访问
                .antMatchers("/detail/vip/**").hasRole("vip")    //只有用户角色为vip（ROLE_vip）才允许访问
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("pwd")
                .defaultSuccessUrl("/")
                .failureUrl("/userLogin?error");
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
        http.rememberMe()
                .rememberMeParameter("rememberme")  //参数值应和页面上的记住我勾选框name属性值一致
                .tokenValiditySeconds(200);
                //对Cookie信息进行持久化管理
               // .tokenRepository(tokenResposity());
    }
    //持久化Token存储
//    @Bean
//    public JdbcTokenRepositoryImpl tokenRepository(){
//        JdbcTokenRepositoryImpl jr=new JdbcTokenRepositoryImpl();
//        jr.setDataSource(dataSource);
//        return jr;
//    }
}
