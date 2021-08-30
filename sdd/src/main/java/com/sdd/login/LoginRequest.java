package com.sdd.login;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

/**
 *   "imei": "865652042931545",
 *   "user_id": "vivek",
 * "password": "123323", // It will be encrypted as per required by API team
 *   "imei-1": "865652042830549",
 *   "platform": "android"
 * }
 */

@Data
public class LoginRequest {
    String username;
    String password;

    @JsonProperty("user_id")
    private String userId;
    private String platform;
    private String imei;

}
