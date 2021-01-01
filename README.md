# jpa

## 2단계 요구사항
아래의 도메인 설명을 보고 **엔티티 클래스의 연관 관계**를 매핑해 본다.

- 지하철역과 노선이 있다.
- 지하철역은 여러 노선에 소속될 수 있다.  
    - 환승역을 고려한다.
- 노선 조회 시 속한 지하철역을 볼 수 있다.
- 지하철역 조회 시 어느 노선에 속한지 볼 수 있다.
- 사용자와 즐겨찾기가 있다.
- 사용자는 여러 즐겨찾기를 가질 수 있다.
- 즐겨찾기에는 출발역과 도착역이 포함되어 있다.

## 2단계 기능목록
- 지하철역 : 노선 = 다대다(M:N) 양방향 연관 관계
- 사용자 : 즐겨찾기 = 일대다(1:N) 양방향 연관 관계
- 즐겨찾기 출발역과 도착역 추가