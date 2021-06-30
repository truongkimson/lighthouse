import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
//        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//        auth.eraseCredentials(false);

        auth.userDetailsService(userDetailsService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()               .antMatchers().permitAll()
                .anyRequest().authenticated().and() // set login page
                .formLogin().loginPage("/Login")
                .loginProcessingUrl("/Login")//sent ajax
                .usernameParameter("username")
                .passwordParameter("password")
                // set login successful page
                .defaultSuccessUrl("/index")
                .permitAll().successHandler(loginSuccessHandler()).failureHandler(loginFailureHandler()) //self-determined username and password 
                //  .usernameParameter("username") //
                //  .passwordParameter("password")
                .and().logout().permitAll().invalidateHttpSession(true).
                deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler()).
                and().sessionManagement().maximumSessions(10).expiredUrl("/Login");
        http.csrf().disable();// close csrf
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/lib/**", "/iconfont/**", "/images/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //password encode
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() { //handle logout
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                User user = null;
                try {
                    user = (User) authentication.getPrincipal();
                    log.info("USER : " + user.getFirstName() + " LOGOUT SUCCESS !  ");
                } catch (Exception e) {
                    log.info("LOGOUT EXCEPTION , e : " + e.getMessage());
                }
                httpServletResponse.sendRedirect("/Login");
            }
        };
    }

    @Bean
    public AuthenticationFailureHandler loginFailureHandler() { //if login failed
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                ResponseResult jsonData = null;
                if (exception.getMessage().equals("user didnt exist")) {
                    jsonData = new ResponseResult("user didnt exist", null, 402);
                }
                if (exception.getMessage().equals("Bad credentials")) {
                    jsonData = new ResponseResult("user or password wrong", null, 403);
                }
                if (!StringUtils.isEmpty(jsonData)) {
                    String json = objectMapper.writeValueAsString(jsonData);//package to json
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(json);
                    out.flush();
                    out.close();
                }
            }
        };
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //handle login
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                User userDetails = (User) authentication.getPrincipal();
                logger.info("USER : " + userDetails.getUsername() + " LOGIN SUCCESS !  ");
                ResponseResult jsonData = new ResponseResult("register OK", null, 200);
                String json = objectMapper.writeValueAsString(jsonData);
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(json);
                out.flush();
                out.close();
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {    //login successful
        return new UserDetailsService() {
            @Autowired
            private UserRepository UserRepository;

            @Override
            public UserDetails loadUserByUsername(int id) throws UsernameNotFoundException {

                User User = UserRepository.findById(id);
                if (User != null) {
                    return new User(User);
                } else throw new UsernameNotFoundException("Username " + id + " not found");
            }
        };
    }
}
