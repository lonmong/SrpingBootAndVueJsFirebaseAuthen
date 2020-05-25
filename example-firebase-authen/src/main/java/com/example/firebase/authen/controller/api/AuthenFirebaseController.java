package com.example.firebase.authen.controller.api;

import com.example.firebase.authen.model.dto.request.FirebaseSignInRequestDTO;
import com.google.firebase.auth.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/authen")
@Slf4j
public class AuthenFirebaseController {


    @PostMapping("/singIn")
    public ResponseEntity<?> signInEmail(@RequestBody FirebaseSignInRequestDTO dto) throws FirebaseAuthException {
        FirebaseToken decodedToken = null;
        try {
            log.info(dto.getIdToken());
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(dto.getIdToken());
            String uid = decodedToken.getUid();
            log.info("Uid: " + uid);
            if (uid.equals(dto.getUid())) {
                UserRecord firebaseUser = FirebaseAuth.getInstance().getUser(uid);
                String firstName = "";
                String lastName = "";
                try {
                    List<String> names = convertFirebaseName(firebaseUser.getDisplayName());
                    if (names.size() == 1) {
                        firstName = names.get(0);
                    } else if (names.size() == 2) {
                        firstName = names.get(0);
                        lastName = names.get(1);
                    } else {
                        firstName = names.get(0);
                        lastName = names.get(1) + " " + names.get(2);
                    }
                } catch (Exception e) {
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid uid");
                }
                return ResponseEntity.ok("Welcome ::"+firebaseUser.getEmail()+" "+firstName+" "+lastName);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid uid");
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private List<String> convertFirebaseName(String displayName) {
        List<String> list = new ArrayList<String>(Arrays.asList(displayName.split(" ")));
        return list;
    }

}
