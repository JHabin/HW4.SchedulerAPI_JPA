# Scheduler API (JPA)

## API 명세서

### 회원가입 및 로그인
| 기능   | HTTP | URL              |Request| Response                                             | Status |
|------|------|------------------|--|------------------------------------------------------|--------|
| 회원가입 | `POST`| /api/user/signup |{"username": "진하빈", "password":"abc123", "email":"abc123@example.com"}| {"userId":1, "username":"진하빈", "email":"abc123@example.com"} |201|
| 로그인  | `POST`| /api/user/login  |{"password":"abc123", "email":"abc123@example.com"}| {"id":1}                                             |302|
| 로그아웃 | `POST`| /api/user/logout ||| 302    |

### 일정 관리
| 기능       | HTTP     | URL                         | Request                                                                 | Response                                                                                                                           | Status                       |
|----------|----------|-----------------------------|-------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|------------------------------|
| 일정 생성    | `POST`   | /api/schedules              | {"userId":1, "title": "과제제출", "contents": "금 12시", "password":"abc123"} | {"userId":1, "id":1, "title":"과제제출", "contents": "금 12시", "createAt": "2024-11-13 14:57:59"}                                       | 201        / 400 bad request |
| 전체 일정 조회 | `GET`    | /api/schedules/             |                                                                         | {"userId":1, "id":1, "title":"과제제출", "contents": "금 12시", "createAt": "2024-11-13 14:57:59", "updateAt": "2024-11-14 14:57:59} ... | 200 ok / 404 not found       |
| 선택 일정 조회 | `GET`    | /api/schedules/{scheduleId} |                                                                         | {"userId":1, "id":1, "title":"과제제출", "contents": "금 12시", "createAt": "2024-11-13 14:57:59", "updateAt": "2024-11-14 14:57:59}     | 200 ok / 404 not found       |   
| 일정 수정    | `PUT`    | /api/schedules/{scheduleId} |   {"userId":1, "title": "과제제출", "contents": "금 14시", "password":"abc123"}                                                                      | {"userId":1, "id":1, "title":"과제제출", "contents": "금 14시", "createAt": "2024-11-13 14:57:59", "updateAt": "2024-11-14 14:57:59}     | 200 ok / 4xx error           |
| 일정 삭제    | `DELETE` | /api/schedules/{scheduleId} |   {"password":"abc123"}                                                                      | | 204 no content / 4xx error   |                             


---
## ERD 설계

### USERS 테이블
| field(Kor) | field(Eng) | Key | Type        |NULL Check|
|------------|------------|-----|-------------|--|
| 작성자 식별번호   | userId     | PK  | INT         |X|
| 작성자 이름     | username   | X   | VARCHAR(20) |X|
| 작성자 이메일    | email      | X   | VARCHAR(50) |X|
| 비밀번호       | password   | X   | VARCHAR(20) |X|
| 작성자 등록일    | createAt   | X   | TIMESTAMP   |X|
| 작성자 수정일    | updateAt   | X   | TIMESTAMP   |X|


### SCHEDULES 테이블
| field(Kor) | field(Eng) | Key | Type        | NULL Check |
|------------|------------|-----|-------------|------------|
| 일정 식별번호    | id         | PK  | INT         | X          |
| 작성자 식별번호   | userId     | FK  | INT         | X          |
| 일정 이름      | title      | X   | VARCHAR(20) | X          |
| 일정 내용      | contents   | X   | VARCHAR(50) | O          |
| 일정 작성일     | createAt   | X   | TIMESTAMP   | X          |
| 일정 수정일     | updateAt   | X   | TIMESTAMP   | X          |
| 비밀번호       | password   | X   | VARCHAR(20)   | X          |

