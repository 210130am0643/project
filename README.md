# 목차
* [Security](#security)  
* [JPA](#jpa-repository)  
* [Appliction.yml](#applicationyml)

Security
=========
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
		.and()
		.formLogin()
		.loginPage("/login")
		.usernameParameter("userId") //username 이 기본으로 됨.
		.loginProcessingUrl("/login") // login controller를 안만들어도 security에서 잡음.
		.defaultSuccessUrl("/"); // 로그인 성공시 이동
	}
}
```
## sessionDetails
```
public class SessionDetails implements UserDetails{

	private Login login;
	
	public SessionDetails(Login login) {
		this.login = login;
	}
	
	//해당 User의 권한을 리턴하는곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return login.getUserRole();
			}
		});
		return collection;
	}
```
## sessionDetailsService
* security에서 loginProcessingUrl 요청이 오면 여기로 온다.
* session(내부 Authentication (내부 UserDetails))
* username 으로 파라미터 값은 고정되어 맵핑된다. security에서 usernameparameter 를 수정해주어야함.
```
@Service
@RequiredArgsConstructor
@Slf4j
public class SessionDeatilsService implements UserDetailsService{

	private final LoginRepository loginRepository;
	
	// username 과 parameter 값이 동일해야함.
	// login.html 에서 나는 userId 를 사용하기때문에 userId로 변경
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login login = loginRepository.findByUserId(username);
		if(login != null) {
			return new SessionDetails(login);
		}
		return null;
	}

}
```


JPA repository
============
```
ex) 
public interface LoginRepository  extends JpaRepository<Login,String>{
	public Login findByUserId(String userId);
}
```
* jpa 를 상속받았기 때문에 @Repository 사용필요없음.
* <모델,id의 타입> 이 들어간다.
* findBy + 컬럼 은 규칙.

application.yml
=============
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


