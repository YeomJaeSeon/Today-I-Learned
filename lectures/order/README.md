# DDD layered architecture

## domain layer (추상화 레벨 높은 layer)
- Entity: 도메인을 모델링하고 관리하는 객체
- Service: Entity의 속성과 메서드를 활용하고, domain의 interface를 구현한 infrastructrue layer의 세부기술들을 조합해서 유저가 원하는 도메인 규칙을 만들어내는 객체
  - domain layer에선 Service가 1~2개로 소수의 개수만 존재하는것이 좋다.
- Reader: 데이터를 영속성 layer (DB)에서 데이터를 가져와 객체로 만드는 역할을 하는 객체
- Store: 객체를 serialize해서 DB에 넣는 객체
- Executor: 읽고 쓰고 ~ 조합해서 실행하는 객체
- Factory: 객체 자체가 메모리상에 복잡한 규칙에 맞게 만들어져야할때 사용되는 객체(생성자, 빌더패턴 만으로 표현하기엔 부족하고 코드 또한 길어짐 - domain layer가 한눈에 들어오지않음)

> 위 객체들의 조합으로 domain layer에서 업무 규칙을 표현할수있다.

> domain layer에선 추상화 레벨을 높여서 domain layer만 읽어도 코드가 이해되고 도메인규칙이 이해되어야한다. (세세한 구현은 infrastructure layer에서)

- domain layer 입장에서 input(request param), output(response) 도 추상화가 되어야 한다.
  - 외부에서 받은 input을 그대로 받아버리면 외부(interfaces, infrastructures layer)의 변경이 일어나면 domain layer도 그대로 변경의 영향을 받는다.
    - 외부에서 받는 parameter가 바뀐다고 해서 도메인 규칙을 표현하는 domain layer가 변경이 일어나면 안된다.
  - interfaces의 input을 domain layer가 받으면 의존관계가 단방향이 아닌 양방향이된다. (interfaces -> domain, domain -> interfaces)

- Command: '데이터 변경 목적의' 추상화된 input 객체
- Criteria: '조회 목적의' 추상화된 input 객체
- Info: 추상화된 output 객체 (Entity를 domain layer에서 바로 return하면 도메인 로직이 새어나간다. (entity엔 도메인 관련된 비즈니스 메서드 - 주문 도메인이라고 하면 '주문 완료해'라는 비즈니스 메서드도 가질건데, 업무규칙 관련된 도메인이 외부로 새어나가면 layer간의 역할과 책임을 분리하여 관심사 분리하려는 의미도 줄어들고 가독성도 안좋고(domain layer이외, application layer에서 업무 로직이 있으니) side effect도 있을 듯))
> domain layer input output으론 java의 Long, String, .. 도 괜찮다.

- domain layer의 Service는 추상화된 interface들을 의존 함으로써, **도메인의 업무 규칙이 한눈에 보이고**(구현되지 않은 추상화만 있으니 코드가 짧을 것이고), **구현체가 바뀌더라도 변경할 필요가 없다.**
  - **가독성**, **변경에 유연** 두가지 장점!
- domain layer에 xxxService라는 객체는 한두개 정도로 하자.
  - xxxService이름의 객체가 너무 많으면 코드파악이 힘듬. 의존성도 많고, 테스트코드 작성도 어려움
  - 서비스는 대부분 상하 의존관계가 있다.
  - domain layer의 비즈니스 규칙을 정의하는 객체는 전부 xxxService로 이름짓지말고, Store, Reader, Executor, Factory,,로 다양하게 사용하자.
  - 추상화된 고레벨의 xxxService는 특정 도메인에 한두개정도로 하여, 도메인 레이어의 가독성을 높이자.
- Service간엔 참조관계를 갖지 말자.
  - DDD layer간의 단방향을 유지하면서 Service간의 서로간의 참조를 하지 않으면 코드가 깔끔해짐
  - 코드가 뚱뚱해지고 로직 파악도 어려움

> domain layer는 정적타입. 정적모델링

## infrastructure layer (추상화 레벨 낮은 layer)
- domain layer의 추상화된 interface를 실제로 구현하는 layer
- DIP(Dependency Inversion Principal)를 통해서 세세한 구현이 바뀌더라도 domain layer까지 변경의 여파가 가지 않도록 한다.
  - spring DI container(ioc container)를 이용해서 DIP원칙이 지켜짐
- infrastructure layer간의 참조관계는 허용한다. (domain layer의 Service간의 참조는 허용 불가.)
- `@Component` 어노테이션을 이용해서 구현체를 지정하자.
  - `@Service` 어노테이션은 domain layer의 Service에 이용하면 실제 도메인 업무규칙의 entry point를 `@Service`만으로 구별하고 인식할수있다. (좋은 방법인듯.)

> infrastructure layer는 동적 타입, 동적 모델링(런타임 시에 실제 의존할 구현체가 정해짐)

## application layer
- transaction이 지켜져야하는 domain layer와 구별되는 이외의 업무 규칙을 하나로 묶는 layer
  - (주문 완료 + 주문 완료 후 알림톡 이나 이메일 발송) - aggregate
- Facade 디자인 패턴처럼, 여러개를 하나로 묶어서 처리하는 layer로 일관성 지켜져야하는 업무 규칙의 도메인 로직과 그외의 도메인 로직을 하나로 묶는 역할을 한다.

## interfaces layer
- 시스템 외부에서 전달되는 요청을 해석해서, 시스템이 처리할수 있게 하는 역할
  - 유저가 원하는 형태로 input & 유저가 원하는 형태로 output
- 외부에서 요청하는 다양한 프로토콜을 커버하면서 적절하게 다음 layer인 application layer에 맞게 요청을 전달한다.
  - HTTP API, gRPC, 비동기 메시징(aws sqs) ...
- Controller, DTO, Mapper(DTO -> Command, Criteria, Java 기본 데이터형태로, 혹은 Info -> DTO로 바꿔서 다음 layer로 전달하는 객체)
  - **외부 프로토콜이 무엇이던간에, 뒷단의 layer(application layer이후)는 영향이 없도록 Mapper를 통해서 다음 layer가 필요한 객체로 변경한다**.
- Request, Response 프로퍼티는 꼭 필요한 녀석들만 요청받고 응답하도록 하자.
  - 과하게 많은 프로퍼티들을 요청받거나 리턴하면 클라이언트와 의존성이 높아져 추후에 변경이 어려움. (앱의 강업.., 외부에서 이미 의존성을 가지고있어서 변경이 어렵다.)
