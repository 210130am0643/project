application.yml
--------------
```
spring:   
  thymeleaf:
    prefix: classpath:templates/thymeleaf/ # 접두어 classpath:templates/ 가 default 
    suffix: .html # 접미어
    cache: false # 개발시 새로고침하면 적용.
mybatis:
  mapper-locations: mapper/**/*.xml # mapper 기본경로 
  configuration:
    map-underscore-to-camel-case: true # 카멜형식으로 자동변환
```


