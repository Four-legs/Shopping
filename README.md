# Spring/JPA를 활용한 상품 주문 서비스

## 프로젝트 목표

Spring 프레임워크와 JPA의 개념을 숙지한 후, 이를 활용해 보며 여러 방면에 대한 역량을 키우는 것을 목표로 한다. 또한, 테스트 코드 작성을 습관화하여 구현한 기능이 잘 동작하는지 검증을 철저히 하는 것을 목표로 한다.

각 도메인들을 객체 지향에 걸맞게 설계해, 유지보수 및 확장에 용이하도록 한다.

> ### 구현 목표 기능 (필수기능)
  #

- 상품 주문 / 상품 주문 취소
- 주문 조회 기능
- 주문 이력 검색 기능 (취소 여부 확인)
- 주문 및 주문 취소시 상품 재고량 변화
- 간단한 화면 구현 (Frontend)

### 구현 목표 기능 (확장기능)
#
- 로그인 기능
- 카테고리 별 상품 검색 기능
- 다양한 상품 종류 추가
- 배송 정보 조회
- 관리자용 기능 (Admin) : 상품 입고, 할인 적용 등

#
# 프로젝트 진행 이력

## 03.13
- 프로젝트 생성 (Spring Boot)
- 프로젝트 환경 설정 (Lombok)
- 데이터베이스 연결 (H2 Database)

## 03.16
- 도메인 구현 (회원, 상품, 주문, 배송 등)
- 각 도메인들의 연관관계는 다음 클래스 다이어그램과 같다.  
![클래스 다이어그램](./img/class_diagram.png)
- 도메인 구현 리팩토링 : 연관관계 메소드 작성, 다대일 및 일대일 관계에 LAZY 로딩 적용
- 회원 도메인에 대한 기능 구현
    - MemberRepository, MemberService (회원 가입, 조회)

## 03.17
- 상품 도메인에 대한 기능 구현
    - ItemRepository, ItemService (재고량 증감)

## 03.18
- 주문 도메인에 대한 기능 구현
    - OrderRepository, OrderService
    - 주문하기, 주문취소 기능 구현 (재고 수량 변동 포함)
    - OrderService는 MemberRepository, ItemRepository를 포함 (의존성 주입)
- 주문 생성 메소드를 Order 객체에 생성 (생성 메소드)
    - 주문 생성의 로직이 복잡한 경우 이를 엔티티 자체에 포함시킴
    - 이러한 방식을 도메인 모델 패턴(DDD)이라 함
    - 주문 취소 및 주문의 최종 가격을 구하는 메소드도 포함

