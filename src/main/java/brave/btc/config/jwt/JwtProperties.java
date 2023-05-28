package brave.btc.config.jwt;

public interface JwtProperties {


    String SECRET = "jmt"; // 우리 서버만 알고 있는 비밀값
    int AT_EXP_TIME = 18000000; // 5시간 18000000 (1/1000초) 테스트를 위해 30초로 설정
    int RT_EXP_TIME = 1209600000; // 2주 (1/1000초)
    int ST_EXP_TIME = 604800000; // 1주
    String TOKEN_PREFIX = "Bearer ";
    String AT_HEADER = "Authorization";
    String RT_HEADER = "Refresh-Token";
}
