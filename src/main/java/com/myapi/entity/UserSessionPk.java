package com.myapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Embeddable
public class UserSessionPk  implements Serializable {
    private String email;
    private String sessionid;
}
