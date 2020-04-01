package com.api.rent.memovie.pojo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MARIO MENCIA
 */
@Getter
@Setter
public class SingUpRequestPojo implements Serializable {

    private static final long serialVersionUID = 2156796016320952383L;

    private String name;

    private String lastName;

    private String username;

    private String password;

    private String email;

}
