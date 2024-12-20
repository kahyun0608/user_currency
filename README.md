# ⚡️스프링 심화 주차 : 환전 API⚡️
## 1. 기능 구현
- C: 환전 요청 수행
    -  `환전 대상 통화 식별자`가 `Currency` 테이블에 가지고 있는 환율을 기준으로 환전을 수행합니다.
-  R: 고객 고유 식별자를 기반으로 특정 고객이 수행한 환전 요청 조회 <br>
    : 고객의 모든 환전 요청을 그룹화하여 해당 고객이 수행한 환전 요청 데이터들의 총 row 수와 환전을 요청한 총 금액 조회
-  U: 특정 환전 요청 상태를 취소로 변경
   -  상태 값은 `normal`, `cancelled`
-  D: 고객이 삭제될 때 해당 고객이 수행한 모든 환전 요청도 삭제
-  스프링이 구동될 때 통화 테이블에 있는 환율이 0이거나 음수이거나 지정된 범위를 벗어나는 경우, 유효하지 않은 값으로 간주하고 로그를 기록
-  GlobalExceptionHandler를 사용한 예외처리
  
## 2. API 명세서
https://gossamer-giraffe-f6d.notion.site/14c3bd8753e48065adc8dcb5a97a3521?v=4ac0553ef58141a2a0db8e399342b8a5&pvs=4

## 3. ERD
<img width="563" alt="스크린샷 2024-11-14 오후 4 31 50" src="https://github.com/user-attachments/assets/ba6ba928-ffa7-4982-a1dc-f56184cf1e43">

## 4. 소감
이번 프로젝트를 프로젝트를 통해 JPQL, RestExceptionAdvice 등 새로운 문법들에 대해 더 공부할 수 있었습니다. <br> 
하지만 fetch join 이나 지연로딩같은 성능 차이에 대해 더 공부할 수 있으면 좋을 것 같습니다. 
