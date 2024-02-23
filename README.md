# <strong>BackOfficeProject</strong>


## 🎁 프로젝트 개요


- **개발 기간** : 24.02.14 ~ 24.02.23
- **프로젝트 이름** : ConcurrencyControlProject
- **프로젝트 설명 :** 동시성 제어 구현을 위한 티켓팅 서비스


## 👩 Team A03

- <strong>이시원</strong>
    - 역할 - 카테고리 CRUD, 티켓 CRUD
- <strong>윤승환</strong>
    - 역할 - 굿즈CRUD
- <strong>이승상</strong>
    - 역할 - 유저CRUD, 인증/인가
- <strong>박미소</strong>
    - 역할 - 리뷰CRUD
- <strong>팀 전체</strong>
    - 동시성 제어를 위한 테스트코드
    - Redis를 이용한 Lock 구현



## **📚기술스택**

<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"/></a>
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white"/></a>
<img src="https://img.shields.io/badge/Kotlin-663399?style=for-the-badge&logo=Kotlin&logoColor=white"/></a>
<img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white">
<img src="	https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white">
<img src="https://img.shields.io/badge/Redis-FF2D20?style=for-the-badge&logo=Redis&logoColor=white">
<img src="https://img.shields.io/badge/Swagger-6DB33F?style=for-the-badge&logo=Swagger&logoColor=white">
<img src="https://img.shields.io/badge/H2-FF2D20?style=for-the-badge&logo=H2&logoColor=white">
<img src="https://img.shields.io/badge/docker-316192?style=for-the-badge&logo=docker&logoColor=white">

## 🎈 주요기능

### 카테고리 기능
- 카테고리 CRUD
  - 인증/인가
    - 역할 ADMIN 만 생성, 수정, 삭제 가능
    - 굿즈의 상위 객체
### 굿즈 CRUD
- 굿즈 CRUD
  - 인증/인가
    - 역할 ADMIN, SELLER 만 생성, 수정, 삭제 가능
    - 상품 수정 시 티켓 갯수 변경 불가능
### 리뷰 CRUD
- 리뷰 CRUD
  - 인증/인가
    - 본인이 구매한 굿즈에만 리뷰를 작성 가능
    - 본인의 리뷰만 수정, 삭제 가능
    - 스코어 점수 최대 5점 만점
### 티켓팅 기능
- 티켓팅 기능
  - 한명 당 티켓 하나씩 구매 가능
  - 티켓팅 동시성 제어 -> redis
### 회원가입/로그인
- 회원가입/로그인
  - JWT Token 기반 인증/인가
    - Spring Security 활용

## 동시성을 위해 사용한 lock
### @Synchronized 
- 장점 : 코드 간결성, 간단한 사용법, 일관성 유지
- 단점 : 단일 서버에서는 잘 작동하지만 서버를 scale out 해서 여러 대의 서버에서 작동할 경우 동시성 해결 불가

### Lettuce
- 장점 : redis 의존성을 추가하는 경우 기본 redis client 로 제공하므로, 별도의 설정 없이 간단히 구현 가능
- 단점 : spin lock 방식이여서 lock 재시도 로직을 직접 작성, lock 획득까지 redis 에 계속 요청을 보내 redis 서버 부담이 큼

### Redisson
#### spin lock
- 장점 : 비교적 구현이 단순하고, waitTime(대기 시간), leaseTime(점유 시간) 등의 기능 제공
- 단점 : lock 과 재시도 로직을 직접 작성해야함. lock 획득까지 redis 에 계속 요청을 보내 redis 서버의 부담이 큼
#### event lock(pub sub 기반)
- 장점 : redis 에 매번 요청을 보내지 않고 서버가 대기하고 있기 때문에 redis 의 부담이 적고, 여러 서버에서 작동 시에도 동시성 제어 가능
- 단점 : 구현체를 바로 사용하기 때문에 세밀한 제어는 불가능 할 수 있음


### 프로젝트 후기
- <strong>이시원</strong>
    -
- <strong>윤승환</strong>
    -
- <strong>이승상</strong>
    - 이번 프로젝트로 redis 와 lock 을 공부했고 얻은게 많은 프로젝트. 팀원들과 협업부분도 좋았습니다.
- <strong>박미소</strong>
    - redis 와 동시성 제어에 대해 알게 됐고 팀원분들의 인사이트를 얻는 좋은 기회였습니다.
