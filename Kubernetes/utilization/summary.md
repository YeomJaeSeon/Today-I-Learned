# 11.1.1 ~ 11.1.4

## 11.1 포드의 자원 사용량 제한

- 쿠버네티스 같은 컨테이너 오케스트레이션 툴의 장점은 여러대의 서버를 하나의 클러스터로 묶어 리소스 플로 사용할수 있따는점 !
    - scale out같은 서버를 수평적으로 증설하는 것에 유리
- 그치만 머니머니 해도 기본적인 컴퓨팅 자원 활용률을 높이는 것이 중요하다.(기본적으로!) (Utilization)
- 11.1에서는 어떻게하면 쿠버네티스에서 컴퓨팅자원의 활용률을 높일지에 대해 알아보고, 쿠버에서 지원하는 다양한 기능에 대해서 알아보자.

### 11.1.1 컨테이너와 포드의 자원 사용량 제한 : Limits

- 도커에서 컨테이너의 자원 사용량 제한하는 방법이 여러가지 있다. (메모리, cpu ,..)
- 쿠버도 기본적으로 도커를 컨테이너 런타임으로 사용하기 때문에 포드를 생성할때 도커와 같이 cpu, 메모리의 최대 사용량을 제한할수 있다.
    - !!! 자원 제한을 하지않으면 포드가 노드의 자원을 모두 사용해버릴수있다. 노드의 자원이 고갈되면 안되겠다.

—> 명시적으로 자원 제한하자

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: resource-limit-pod
  labels:
    name: resource-limit-pod
spec:
  containers:
  - name: nginx
    image: nginx:latest
  resources:
    limits: # 제한
      memory: "256Mi"
      cpu: "1000m"
```

### 11.1.2 컨테이너와 포드의 자원 사용량 제한하기: Requests

- Requests는 뭘까?
- Limits과 혼동되기 쉽지만 Requests는 **적어도 이 만큼의 자원은 컨테이너에게 보장돼야 한다** 라는 뜻이다.
- Requests는 오버커밋을 가능하게 하는 기능이기에 중요
    - 오버커밋이란? : 한정된 컴퓨팅 자원을 효율적으로 사용하기 위한 방법으로 사용할수 잇는 자원보다 더 많은 양을 가상 머신이나 컨테이너에게 할당함으로써 전체 자원 사용률(utilization)을 높이는 방법

! 상황 가정

- 1GB메모리를 탑재한 하나의 서버 컴퓨터에는 두개의 컨테이너가 있다. 두 컨테이너(A, B)에 각각 정적으로 500MB씩 할당하였따.
- 근데 A는 1MB도 안쓰고 B는 499MB정도를 쓰고있다. → A가 안쓰는 메모리를 B가썼으면 좋겠다.
- 그런데 정적으로 메모리를 할당한 것이기에 불가능하다. (메모리 빌려주는것이)

→ 쿠버네티스에서는 이런 상황에서 오버커밋을 통해 실제 물리 자원보다 더 많은 양의 자원을 할당하는 기능을 제공한다.

- 메모리 !!제한!!이 750MB인 포드 두개를 생성 가능
    - 서버 컴퓨터 자원이 1G인데 그럼 1.5G가 필요하잖아? → 실제로 1.5G를 사용하는게 아니다.
    - A포드가 메모리 사용률이 낮다면 B포드가 A로부터 남는 메모리 자원을 “”기회적"”으로 사용할수 있게 된다.

이걸 “”Limits””라고 부른다.

- 그런데 A컨테이너가 500MB사용하고있는데 B컨테이너 750MB(Limits가 750MB)사용하려 한다면?

→ 이 상황을 위해 “사용을 보장받을 수 있는 경계선” 이라는 개념이 필요하다.

- A, B모두 나는 적어도 500MB는 사용할수 있다는 경계선이 있으면 된다.
- Limits가 750MB인 두 컨테이너가 경계선이 500MB라면 500MB이내 사용할땐 아무런 문제가 안된다. 그러나 A가 500사용하고있을때 B가 700을 사용하려 한다면 메모리 사용 시도가 실패하게 된다.
- “이미 A가 500인 경계선을 사용하는데 B가 Limits이내의 700을 사용하려 한다면 실패하게된다!””

→ 이걸 “Requests”라고 부른다.

정리하면

- 오버커밋(실제 물리 자원보다 더 많은 양의 자원을 활용 할수 있게 하는 기능)을 위해 Limits를 이용하고 “Limits”를 이용하면 자원간 충돌이 일어날수있기에 (서버가 1G, 두 컨테이너가 500, 750사용하려 할때) 자원 경계션인 “Requests”를 사용한다.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: resource-limit-with-request-pod
  labels:
    name: resource-limit-with-request-pod
spec:
  containers:
  - name: nginx
    image: nginx:latest
  resources:
    limits:
      memory: "256Mi"
      cpu: "1000m"
    requests:
      memory: "128Mi"
      cpu: "500m"
```

→ yaml파일 한줄요약

“128Mi까지 메모리 사용은 무조건 보장된다. 그러나 유휴 자원이 있다면 256Mi까지도 사용할수 있다.”

→ requests보다 더 많은 자원을 포드는 사용할수 없기에 쿠버네티스의 스케줄러는 requests를 보고 여유가 있는 노드를 선택해 포드를 생성한다. 즉 포드를 할당할때의 기준은 “limits”가 아닌 “requests”

### 11.1.3 CPU 자원 사용량의 제한 원리

- 미리 알아야할것
    - 쿠버는 cpu를 m(밀리코어)단위로 제한하며 1개의 cpu는 1000m에 해당됨. 서버(노드)에 2개의 cpu가 존재한다면 최대 2000m 만큼의 CPU Requests를 포드의 컨테이너에 할당가능

```yaml
...
resources:
    limits:
      memory: "256Mi"
      cpu: "1000m" # --cpus
    requests:
      memory: "128Mi"
      cpu: "500m" # --cpu-shares
```

- cpu의 Limits와 Requests는 `docker run` 명령어의 옵션인 `--cpus` 와 `--cpu-shares` 와 각각 동일하다.

case 1) 노드(1000m의 cpu)에 포드 컨테이너 하나(Limits: 700m, Requests: 500m)

→ 포드 컨테이너 하나는 Limits만큼의 cpu사용 가능(70%, 700 / 1000)

case 2) 노드(1000m의 cpu)에 포드 컨테이너 두개(A, B) (모두 Limits: 700m, Requests: 500m)

→ `--cpu-shares` 와 동일한 Requests 비율로 나눠서 사용 가능(500: 500 = 1 : 1)

→ CPU 1000m만큼의 Requests를 모두 소진하였기에 새로운 Requests를 가지는 컨테이너를 해당 노드에할당하는 것은 불가능

case 3) 노드(1000m cpu)에 A 포드컨테이너(Limits: 700m, Requests: 500m)이 이미 생성되어 있고 B포드 컨테이너(Limits:700m, Requets:500m)이 생성되려고 시도하는 상황

→ A포드 컨테이너는 1000m중 Limits만큼의 700m을 사용중인데 B포드 컨테이너가 오면 B의 Requests는 500m이므로 A는 200만큼의 쓰로틀이 발생하고 B는 Requests만큼의 cpu를 할당받게된다.

→ case 2)에서 새로운 포드 컨테이너가 해당 노드에 들어오는게 불가능한것과 다른 이유다.

!! 참고!

쿠버네티스에서 cpu는 압축 가능한 리소스라고 불린다. 메모리는 압축불가능한 리소스이다. 그래서 A포드 컨테이너가 200m만큼 압축해버린것.. cpu가 아니라 메모리였으면 우선순위가 낮은 프로세스가 종료되어 버린다고 한다. 

### Qos클래스와 메모리 자원 사용량 제한 원리

- cpu는 압축간으한 리소스라 Requests만큼의 자원이 없으면 나머지 포드에 쓰로틀 걸어버리고 자신이 할당받을수 있는데 메모리는 그렇지 않다.(메모리에 실제 데이터가 올라간 것이므로 쓰로틀 걸어버리면 데이터가 날아가버릴것.)
- 그래서 메모리를 확보하기 위해 우선순위가 낮은 포드 또는 프로세스를 강제로 종료하게 설계되어있다.
- 이러한 메모리 자원 경합으로 강제로 종료된 포드는 다른 노드로 옮겨가게 되는데 이를 “Eviction(퇴거)” 라고 부른다.
    - 원래 있던 노드에는 메모리 자원이 부족하기 때문에..

→ 그럼 어떤 포드나 프로세스 먼저 제거?

- Requets나  Limits 값에 따른 내부적인 우선순위 계산
- 3가지 종류의 Qos(Quality of Service) 클래스를 명시적으로 포드에 설정

- 쿠버네티스에서의 메모리 부족과 OOM(Out Of Memory)
    - 쿠버 노드에는 노드의 상태 정보를 볼수있는 Conditions라는 것이 존재한다.
        - MemoryPressure, DiskPressure등의 값을 확인가능(kubelet 쿠버네티스 에이 전트가 노드의 자원 상태를 주기적으로 확인하며 해당 값들을 갱신한다.)
    - `kubectl describe node`로 확인 가능
    - 메모리 부족하지 않을 때는 MemoryPressure의 값이 False로 설정되어있다. 부족하면 True
        - MemoryPressure가 True가 되면 쿠버네티스는 해당 노드에서 실행중이던 모든포드에 대해 “순위"를 매긴 뒤, 가장 우선순위가 낮은 포드를 다른 노드로 Evict(퇴거!) 시켜버린다. + MemoryPressure가 True인 노드에 더이상 포드를 할당하지 않아버린다.
    - kubelet이 MemoryPressure 상태를 감지하기 “전!!!”에 갑자기 메모리 사용량이 많아지면 리눅스 시스템의 OOM Killer가 우선순위가 낮은 프로세스를 종료시킨다.(여기에서의 우선순위는 쿠버가 설정한 우선순위가 아닌가?) → 아니다!
        - OOM우선순위 매기는 두가지 방법
            1. oom_score_adj
            2. oom_score
            
            “아무튼 쿠버네티스가 노드의 포드들에 우선순위를 매기는 방법과 다르다!”
            

### Qos 클래스 1 Guaranteed 클래스

- Qos클래스는 포드의 Limits Requests값에 따라 설정된다.
- 다양한 종류가 있는데 Qos를 설정하지 않았더라도 자동으로 설정됨.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: resource-limit-pod
  labels:
    name: resource-limit-pod
spec:
  containers:
  - name: nginx
    image: nginx:latest
    resources:
      limits:
        memory: "256Mi"
        cpu: "1000m"
```





- Guaranteed Qos클래스는 Limits와 Requests가 완전히 동일할때 해당 포드의 Qos클래스엔 `Guaranteed` 가 부여된다.

### Qos 클래스 2 BestEffort 클래스

- requests와 Limits를 아애 설정하지 않은 포드에 설정되는 클래스임

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: resource-limit-pod
  labels:
    name: resource-limit-pod
spec:
  containers:
  - name: nginx
    image: nginx:latest
```



- 모든 자원을 사용할 수도(Limits에 제한이 없음) 자원을 전혀 사용하지 않을 수(Requests설저이 없어서 보장받을 자원이 없음)도있다.

### Qos 클래스 3 Burstable 클래스

- 위의 클래스가 아닌 포드는 전부 Burstable클래스
- Limits가 Requets보다 큰 경우.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: resource-limit-pod
  labels:
    name: resource-limit-pod
spec:
  containers:
  - name: nginx
    image: nginx:latest
    resources:
      limits:
        memory: "256Mi"
        cpu: "1000m"
      requests:
        memory: "128Mi"
        cpu: "500m"
```


- Burstable클래스의 포드는 Requets보다 더 많이 자원을 사용하고 있을 수 있다.(Limits때문에)
- 이떄 자원의 경합이 일어날수 있음.
- 그렇게 된다면 Request보다 더 맣은 자원을 사용하는 포드는 우선순위가 낮게 설정됨

### Qos 클래스와 메모리 부족

- kubelet이 메모리 부족한 상황이면 우선순위가 낮은 포드를 다른 노드로 퇴거(Evict)시키는데 kubelet이 그걸 감지하기도 전에(MemoryPressure: True되기도 전에) 급격히 메모리 사용량이 늘면 OOM  Killer가 OOM 점수가 낮은 프로세스를 강제 종료해버린다. 이렇게 되면 레플리카셋의 포드 개수만큼 유지가 자동으로 되기 때문에 포드는 재시작이 될수도 있다.
- 우선순위: Guaranteed > Burstable > BestEffort
- 메모리 부족하면 BestEffort포드 종료, 그다음 Burstable종료.. Guaranteed는 마지막에 종료된다.
- 클래스의 우선순위는 절대적인 것이 아니다.!! 포드가 현재 메모리를 많이 사용하면 사용할수록 우선순위가 낮아진다.

### 자원 오버커밋의 필요성

- 포드가 언제 어떻게 얼마나 자원을 사용하게 될지 예측하는 것은 불가능.
- 오버커밋을 이용함으로써 예상치 못한 문제 발생할수도있다.
- 오버 커밋의 원리 너무 복잡… Limits, Qos 우선순위..

→ 오버커밋을 사용하여 노드의 자원들을 효율적으로 사용할순있지만 필수는 아니다. !! 이러한 방법도 있다는 것을 알려주려고 저자는 적었다. 자원의 활용도를 높이는 것보다 어플리케이션의 안정성이 더 중요하다면 Requets와 Limits를 동일하게 설정하는 Guaranteed 클래스로 생성하는 것이 해답이 될 수도 있다.