# 실습2

- 개발이 완료된 nestjs를 Dockerfile을 이용해 도커 이미지 빌드하고 도커 컴포즈로 해당 도커이미지를 컨테이너 실행시키고, mysql 컨테이너와 서로 연결

# 알게된 점
- 도커 컨테이너 끼리 서로 연결하려면 networks를 만들어서 지정해줘야함
- nestjs 컨테이너가 mysql 컨테이너에 접근하려면 host가 'localhost'나 '127.0.0.1'이면 안된다. 그러면 자신의 컨테이너의 로컬 주소에 접근하는 것, mysql호스트에 접근해야한다. 이 때, mysql컨테이너의 이름을 이용하면 접근가능 - 네트워크로 두 컨테이너가 미리 연결되어있기 때문!? 
