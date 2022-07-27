# Computer System Overview

- 하드웨어만 있다고 게임을 할수있을까?
    - cpu, gpu, 메모리, ssd(디스크), LAN
    -> 위의 하드웨어들을 효율적으로 사용하게 도와주는 운영체제(OS)가 필요하다!
    -> **하드웨어 뿐만 아니라 OS도 필요하다**

## OS
- 하드웨어(컴퓨팅 자원들)들을 효율적으로 관리해서 사용자(혹은 응용프로그램)에게 서비스를 제공하는 역할을 한다.

## 컴퓨터 하드웨어
1. 프로세서
- 계산하는 녀석
- CPU, GPU(그래픽 카드 -> 최초에는 그래픽을 위해 태어났지만 요새는 CPU와 마찬가지로 연산하는데 사용됨), 응용 전용 처리장치

2. 메모리
- 저장하는 녀석
- RAM(주기억장치), Disk(보조기억장치)

3. 주변장치
- 그외 나머지 하드웨어들
- 키보드, 마우스, 모니터, 프린터, 네트워크 모뎀..

## 프로세서
- 연산 수행(계산하는 녀석)
- 또한! 컴퓨터의 모든 장치 **동작 제어**

## 레지스터
- 저장하는 녀석인데 "프로세서 내부에 있는 메모리"(프로세서가 사용할 데이터 저장)
- 컴퓨터에서 '가장' 빠른 메모리

- 종류
    - 전용(어떤 것의 전용), 범용(범용)
    - 가시 레지스터(변경O), 불가시 레지스터(변경X)
    - 데이터 레지스터(데이터 저장), 주소 레지스터(주소 저장), 상태 레지스터(상태 저장)

## <운영체제와 프로세서>
- **운영체제는 프로세서를 관리하는 역할을 포함한다.**
- 운영체제는 프로세서에게 처리할 작업 할당 및 관리
    - 프로세스 생성 및 관리

- 프로그램(프로세스)의 프로세서 사용 제어
    - 프로그램(프로세스)의 프로세서 사용 시간 관리

## 메모리

- 데이터를 저장하는 장치(기억장치)
    - 프로그램이나 사용자 데이터 등.. 저장함

- 레지스터 (비싸고 빠름, 용량은 그만큼 작음) (in cpu)
- 캐시 (in cpu, 그러나 레지스터보다 cpu 코어에서 좀 멀리 있다.)
- 메인메모리
- 보조기억장치 (싸고 느림, 용량은 그만큼 큼)

> 모두 레지스터로 하면되잖아? 그렇게 되면 엄청 빠를텐데,, 용량도 작아서 가볍고,, 그러나 너무 비싸! 많은 컴퓨터 선배들이 고민고민하여 가장 효율적인 최적의 비율의 레지스터, 캐시, 메인메모리, 보조기억장치를 고안해내었고 우린 그걸 사용중(효율적이다? - 싼데 빠르게)

### 메모리 종류

#### 주 기억 장치
- 주 기억장치(Main Memory) - 메인보드에 꽂혀있다. (RAM)
- 프로세서가 수행할 때, 프로그램(프로세스)와 데이터는 메모리에 있어야한다.
    - 프로세서가 직접 접근할수 있는 범위는 (레지스터 - 캐시 - 메인메모리이다..)이기 때문에
- CPU속도와 Disk속도차이는 계속해서 커지는중(CPU의 발전속도가 더크다.) 그렇기에 병목현상이 일어나고 중간에 메모리를 사용하여 병목현상을 줄였다.
    - 디스크 모두를 메모리로 변경하면 되잖아? -> 가격!
- 프로세서가 '직접' 접근할수있는 메모리중 용량이 가장크고 가격이 저렴(느림)

#### 캐시
- cpu안에 있는 메모리 (레지스터보단 cpu 코어보다 멀다)
- 메모리를 넣어도 cpu와 메모리 사이 속도차이 큼(병목현상)
- 또 그래서 캐시를 중간에 넣음
- 캐시는 크기가 작음.. 이걸로 병목현상 해결할수있나?

- 캐시의 동작: 일반적으로 HW적으로 관리됨
    - 캐시 히트
        - 필요한 데이터 블럭이 캐시에 존재
        - 캐시 히트가 일어나면 메모리까지 가지않아도 되니 효율적
    - 캐시 미스
        - 필요한 데이터가 캐시에 없음
        - 데이터 블럭을 메모리에서 캐시로 운반

- "지역성(locality)"
    - 공간적 지역성: 참조한 주소와 인접한 주소를 참조하는 특성 (배열)
    - 시간적 지역성: 한번 참조한 주소를 또 다시 참조하는 특성 (for 문)
    -> "지역성은 캐시 적중률과 밀접한 관련!"


#### 보조 기억 장치
- 프로그램과 데이터 저장
- 프로세서가 직접 접근 불가!
- 프로세서는 주기억 장치를 거쳐서 접근한다.
- 싸고(느림) 용량큼

## <운영체제와 메모리>
- 메모리 할당 및 관리
- 가상 메모리 관리

## 시스템 버스
- 하드웨어들이 데이터 및 신호를 주고받는 물리적인 통로 (프로세서 <-> 메인메모리 <-> 주변장치)
    - 리소스들이 통신하는 통로
- 데이터버스, 주소버스, 제어버스(통신 종류에 따라 구분)

## 주변 자치
- 프로세서와 메모리 제외한 하드웨어들
- 입력(키보드, 마우스), 출력(몸니터 프린터, 스피커), 저장장치

## <운영체제와 주변장치>
- 장치 드라이버 관리 (주변장치를 사용하기 위한 인터페이스: 장치 드라이버)
    - OS가 주변장치를 관리하기 쉽게 인터페이스를 만들었는데 그게 장치 드라이버
- 인터럽트 처리
    - 주변장치의 요청 처리
- 파일 및 디스크 관리