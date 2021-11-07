# URL-Shortener (http://smgs.tk)
## 목차
* [프로젝트 소개](#프로젝트-소개)
* [주요 기능](#주요-기능)
* [프로젝트 환경](#프로젝트-환)
# 프로젝트 소개
## 프로젝트 링크
### http://smgs.tk
무료 도메인이라 가끔 접속이 안될 경우가 있습니다. 그럴 떄는 아래 사이트를 이용해주세요. 
도메인+8자리로 리다이렉트 하기 때문에 도메인이 동작하지 않을 때는 리다이렉트 기능도 동작하지 않습니다.
http://15.164.83.29:8082/home
## URL-Shortener
> 특정 url을 입력 시 고정된 짧은 길이의 url로 변환 해주는 사이트입니다.

![소개](https://user-images.githubusercontent.com/11247319/140642678-82e7bc00-6ca5-44ea-8be5-ade1f3517660.gif)
![urlshortener](https://user-images.githubusercontent.com/11247319/140647304-2d473847-02ac-445d-b480-8da153a68400.jpg)


# 주요 기능
## 등록
> 특정 URL을 고정된 8자리의 URL로 줄여줍니다. 또한 현재 등록되어 있는 url을 표기합니다.

![image](https://user-images.githubusercontent.com/11247319/140647568-2af4af59-2579-4c24-8dfe-947a38ee63e3.png)

### 검증 처리
> 등록을 희망하는 URL과 http통신을 하여 접속 가능한 URL인지 검증합니다.

![검증](https://user-images.githubusercontent.com/11247319/140647719-ce1675cd-782d-4194-9327-0d4fc3961a64.gif)

# 기능을 위한 고려했던 과정
## 8자리의 url은 어떤 값을 써야할까?
### 1. 단순하게 오름차순의 수를 사용하자.
> 그러나 99999999개를 초과하여 URL을 등록할 경우 8자리를 벗어나게 된다.

![9999999](https://user-images.githubusercontent.com/11247319/140647928-0475556a-b337-4b94-92c3-110679acf587.jpg)

### 2. 고유값 하면 역시 UUID!
> uuid는 '-'를 제외해도 32자리라 너무 길다. 그렇다고 앞의 8자리만 잘라서 쓰기에는 희박하지만 중복의 가능성이 있다.

![uuid](https://user-images.githubusercontent.com/11247319/140648431-71b0989d-f2d2-4402-950d-bf85a99ceab8.jpg)

### 3. 현재 시간을 이용하자! (최종 선택)
> 시간은 흐르기 때문에 JVM이 구동되고 난 후 부터의 시간을 8자리로 변환하는 방법을 사용했다.
>> java의 nanotime은 14자리의 10진수를 반환한다. 8자리로 줄이기 위해 64진수로 변환하였다.

아래는 64진수의 수로 변환하기 위한 알고리즘이다.
```
    /***
     * 0 ~ 9 : 그대로 -> 10개
     * 10 ~ 35 : a ~ z -> 26개
     * 36 ~ 61 : A ~ Z -> 26개
     * 62, 63 : # , @ -> 2개
     ***/
    public String getAddressByNanoTime(){
        Long time=System.nanoTime();
        String address="";
        while(time>0){
            Long rem=time%64;
            if(rem<10) address+=(char)('0'+rem);
            else if(rem<36) address+=(char)('a'+rem-10);
            else if(rem<61) address+=(char)('A'+rem-36);
            else if(rem==62) address+='#';
            else address+='@';
            time/=64;
        }
        return address;
    }
```
# 프로젝트 환경
## 개발 환경
>이번 프로젝트의 특징으로는 DB를 사용하지 않았다는 것이다.
```
Spring boot : 2.5.3
Java : 11(jar)
Build tool : Gradle
Dependencies : SpringWeb, Lombok, thymeleaf, 
HTML,css library : bootstrap 5.0.2
CD : Jenkins
Cloud : AWS EC2 (micro)
```

# 향후 추가 할 기능
## 현재 고민중인 안건
- URL 등록 시 짧지만 시간 지연이 있는데 무엇이 문제일까?

## 기능 관련
- DB를 사용하지 않을 것이기에 끝없이 메모리에 URL을 저장 할 수는 없다. 그렇기 때문에 저장된 URL이 특정 시간이 지나면 삭제가 되는 기능을 추가할 것이다.
