# B-Tree 인덱스 키 추가 및 삭제
- 인덱스가 존재하는 테이블에 Insert, Delete, Update하면 인덱스에 대해서도 해당 작업이 발생된다. 인덱스에 대해서 Insert, Delete, Update가 어떻게 발생하는지 알아보자.

## 인덱스 Insert
1. B-Tree상에 어디에 인덱스를 키를 저장할지 위치를 검색한다. 
2. B-Tree의 리프노드에 저장한다.
   - 리프노드가 다 차면 리프노드가 분리되는데 이때, 상위 브랜치 노드까지 처리범위가 넓어진다. (cost가 크다.)
- 보통 레코드 insert비용이 1, 이면 인덱스 insert비용은 1.5
  - 세개의 인덱스가 있는 테이블에 `INSERT INTO USER (name) value('염재선')`하면 1.5 * 3 + 1 (5.5)비용이 든다. 
  - 해당 테이블의 레코드 수, 칼럼의 크기, 인덱스 칼럼 특징(unique, pk, clustered~)에 따라 세부적으론 모두 다르다.
  - 대부분은 메모리, cpu에서 처리하는 시간이 아니라 disk i/o 시간이다.
- 새로운 인덱스가 B-tree에 저장될때, 스토리지엔진에 따라 즉시 저장될수도 있고 그렇지 않을수도있다.
  - MYISAM, MEMORY 디비는 바로 인덱스키가 insert됨
  - innodb는 지연시켜 나중에 인덱스키를 insert시킬수있음 (지능적으로)
    - unique idx, pk같은 경우는 중복여부 체크해야해서 바로 인덱스키가 insert된다.

## 인덱스 Delete
-  해당 인덱스키의 리프노드를 찾아 삭제 마킹만 한다.(간단)
- 스토리지 엔진에 다라 지연하여 처리할수있음
  - innodb: 지연처리
  - MyISAM MEMORY: 지연처리 X

## 인덱스 Update
- 인덱스 값에 따라 B-Tree의 리프노드 위치가 결정되기에 바로 값을 수정할순 없고 아래의 과정을 따름
  1. 해당 인덱스 키 Delete
  2. 해당 인덱스 키 Insert
- 다른 작업들과 동일하게, 스토리지 엔진에 따라 지연처리가 가능하다.

## 인덱스 키 검색
- 트리탐색: 루트토드부터해서 리프노드까지 이동하며 인덱스를 이용해 비교하는 작업
- 단순히 `Select`할때만 인덱스를 사용해 조회쿼리의 성능을 높이는게 아니라, `Update`, `Delete`할때도 인덱스를 통해 빠르게 조회한다음 처리가 가능하다.

- 인덱스 사용하는 경우
  - search하려는 키와 인덱스키가 100%일치
  - search하려는 키와 인덱스키가 앞부분(Left most part)만 일치
  - 범위검색 (<, >), 그러나 해당 인덱스의 뒷부분 칼럼은 인덱스 이용불가
    - (idx: (name, grade, age), `select * from user where name = '염재선' and grade >= 80 and age = 25` age에 대해선 인덱스가 타지 않는다.)
- 인덱스 사용안되는 경우
  - 인덱스 키값을 변형하여 사용하려는 상황
    - 인덱스에 존재하는 키값을 변형하면, B-Tree에 존재하는 값이 아니기에 인덱스를 태울수 없다.
```sql
-- index 안탄다.(900만개 레코드 존재하는 테이블에서 1m1s걸림)
explain select * from user where concat(name, 'hihihi') = 'name1352hihihi';

-- index 탄다.
explain select * from user where name = 'name1352';
```
- 인덱스와 Lock의 연관성
  - innodb 스토리지 엔진은 인덱스로 검색한 레코드들에 락을 건다.
  - 인덱스 설계과 동시성과도 밀접한 관계가 있다.
    - 인덱스를 잘 설계하지 못하면 많은 레코드 혹은 테이블 전체에 락걸려 동시성이 떨어진다.