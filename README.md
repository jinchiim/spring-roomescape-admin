# 요구사항 문서

- [x] localhost:8080/admin 요청 시 어드민 메인 페이지가 응답할 수 있도록 구현한다.
- [x] 어드민 메인 페이지는 templates/admin/index.html 파일을 이용한다.
- [x] localhost:8080/admin/reservation 요청 시 아래 화면과 같이 예약 관리 페이지가 응답할 수 있도록 구현한다.
- [x] 페이지는 templates/admin/reservation-legacy.html 파일을 이용한다.
- [x] 예약 조회 API 명세를 따라 예약 관리 페이지 로드 시 호출되는 예약 목록 조회 API를 구현한다.

# API 명세

## 예약 조회 API

### Request

> GET /reservations HTTP/1.1

### Response

> HTTP/1.1 200
>
> Content-Type: application/json

``` JSON 
[
    {
        "id": 1,
        "name": "브라운",
        "date": "2023-01-01",
        "time": "10:00"
    },
    {
        "id": 2,
        "name": "브라운",
        "date": "2023-01-02",
        "time": "11:00"
    }
]
```

