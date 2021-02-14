Security
---------------
* Securty 를 추가하자마자 모든 페이지가 login으로 이동 되었다.  
* @Bean - 리턴되는 오브젝트를 IoC에 등록.
* Security config 파일 생성 후에는 login 페이지로 강제 이동하지 않았다.
```
@Configuration //config 
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{
	
	@Bean // 리턴되는 오브젝트를 IoC에 등록
	public BCryptPasswordEncoder encPassword() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/main/**").authenticated()
		.antMatchers("/system/**").access("hasRole('ROLE_SYSTEM')")
		.and().formLogin().loginPage("/login");
	}
}
```

JPA repository
---------------
```
ex) 
public interface LoginRepository  extends JpaRepository<Login,String>{}
```
* jpa 를 상속받았기 때문에 @Repository 사용필요없음.
* <모델,id의 타입> 이 들어간다.

application.yml
---------------
```
spring:   
  thymeleaf:
    prefix: classpath:templates/thymeleaf/ # 접두어 classpath:templates/ 가 default 
    suffix: .html # 접미어
    cache: false # 개발시 새로고침하면 적용.
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create
      #naming:
        #physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
        #추가하면 camelCase로 컬럼명이 생성된다.
  h2:
    console:
      enabled: true
logging:
  file:
    path: /logs
    max-size: 500MB
    max-history: 10
  level:
    root: info
mybatis:
  mapper-locations: mapper/**/*.xml # mapper 기본경로 
  configuration:
    map-underscore-to-camel-case: true # 카멜형식으로 자동변환
    
```


