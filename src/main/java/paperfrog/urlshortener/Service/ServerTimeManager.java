package paperfrog.urlshortener.Service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ServerTimeManager {
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
        System.out.println("time : "+time+" url : "+address);
        return address;
    }
}
