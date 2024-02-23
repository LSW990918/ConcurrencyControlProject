# <strong>BackOfficeProject</strong>


## 🎁 프로젝트 개요


- **개발 기간** : 24.02.14 ~ 24.02.23
- **개발 환경** : Kotlin, Spring Boot, Supabase, PostgreSql, Redis
- **프로젝트 이름** : ConcurrencyControlProject
- **프로젝트 설명 :** 동시성 제어 구현을 위한 티켓팅 서비스


## 👩 Team B02

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

### **Backend**

- Spring Boot: 3.2.0

### **DB**

- SupaBase(postgreSQL): [https://supabase.com/dashboard/projects]

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
    - 
### 리뷰 CRUD
- 리뷰 CRUD
  - 인증/인가
    - 
    - 
### 티켓팅 기능
- 티켓팅 기능
  - 인증/인가
    - 
### 회원가입/로그인
- 회원가입/로그인
  - JWT Token 기반 인증/인가
    - Spring Security 활용


### 프로젝트 후기
- <strong>이시원</strong>
    -
- <strong>윤승환</strong>
    - 
- <strong>이승상</strong>
    - 
- <strong>김현주</strong>
    - 
