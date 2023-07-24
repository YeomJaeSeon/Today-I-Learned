#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <netdb.h>

int main() {
    const char *host = "www.naver.com";
    struct hostent *hostent_result;
    struct in_addr **addr_list;

    // 호스트 정보 얻기
    hostent_result = gethostbyname(host);

    if (hostent_result == NULL) {
        fprintf(stderr, "호스트 정보를 얻어오지 못했습니다.\n");
        return 1;
    }

    // 호스트 정보에서 IP 주소 가져오기
    addr_list = (struct in_addr **)hostent_result->h_addr_list;
    if (addr_list[0] == NULL) {
        fprintf(stderr, "IP 주소를 찾을 수 없습니다.\n");
        return 1;
    }

    // IP 주소 출력
    printf("'%s'의 IP 주소는 %s입니다.\n", host, inet_ntoa(*addr_list[0]));

    return 0;
}
