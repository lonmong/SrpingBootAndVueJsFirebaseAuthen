package com.example.firebase.authen.model.dto.request;

import lombok.Data;
import java.io.Serializable;

@Data
public class FirebaseSignInRequestDTO implements Serializable {

    private String uid;

    private String idToken;
}
