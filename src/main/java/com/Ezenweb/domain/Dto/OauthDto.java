package com.Ezenweb.domain.Dto;

import lombok.*;

import java.util.Map;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class OauthDto {

    private  String memail;     // 아이디 [ 이메일 ]
    private  String mname;     // 이름 [ 닉네임 ]
    private  String registrationId;     // auth 회사명
    private  Map<String , Object> attributes; // 인증결과
    private String oauth2UserInfo;  // 회원정보 키

    // auth 회사에 따른 객체 생성
    public  static OauthDto of(
            String registrationId ,                                 // 1. 회사명
            String oauth2UserInfo ,                            // 2. 회원정보
            Map< String , Object > attributes)          // 3. 인증된 토큰
    {
        if( registrationId.equals("kakao") ){
          return   ofKakao( registrationId , oauth2UserInfo , attributes);
        } else if (registrationId.equals("naver") ) {
            return ofNaver( registrationId , oauth2UserInfo , attributes);
        } else if ( registrationId.equals("google") ) {
            return ofGoogle( registrationId , oauth2UserInfo , attributes);
        }else {return null;}
    }

    // 1. 카카오 객체 생성 메소드
    public  static OauthDto ofKakao ( String registrationId , String oauth2UserInfo , Map< String , Object > attributes)  {
       // 1. 회원정보 호출
       Map<String , Object> kakao_account = (Map<String, Object>) attributes.get( oauth2UserInfo);
       Map<String , Object> profile = (Map<String, Object>) kakao_account.get("profile");
        return OauthDto.builder()
                .memail( (String) kakao_account.get("email"))
                .mname( (String)profile.get("nickname") )
                .registrationId(registrationId)
                .attributes(attributes)
                .build();
    }

    // 2. 네이버 객체 생성 메소드
    public static OauthDto ofNaver(String registrationId , String oauth2UserInfo , Map< String , Object > attributes){
        System.out.println("naver attributes : " + attributes);
        Map<String , Object> reponse = (Map<String, Object>) attributes.get( oauth2UserInfo);

        return  OauthDto.builder()
                .memail( (String) reponse.get("email"))
                .mname( (String) reponse.get("nickname"))
                .registrationId( registrationId )
                .oauth2UserInfo( oauth2UserInfo )
                .attributes( attributes)
                .build();
    }

    // 3. 구글 객체 생성 메소드
    public static OauthDto ofGoogle(String registrationId , String oauth2UserInfo , Map< String , Object > attributes){
        System.out.println("google attributes : " + attributes);
        return OauthDto.builder()
                .memail( (String) attributes.get("email"))
                .mname( (String) attributes.get("name"))
                .registrationId(registrationId)
                .oauth2UserInfo( oauth2UserInfo )
                .attributes( attributes )
                .build();
    }

    // 4. dto --> toEntity

}
