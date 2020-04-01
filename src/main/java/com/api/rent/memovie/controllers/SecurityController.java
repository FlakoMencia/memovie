package com.api.rent.memovie.controllers;

import com.api.rent.memovie.enums.RoleEnum;
import com.api.rent.memovie.model.UserPrincipal;
import com.api.rent.memovie.pojo.SingUpRequestPojo;
import com.api.rent.memovie.service.UserPrincipalService;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MARIO MENCIA
 */
@Log
@RestController
@RequestMapping("/security")
@Getter
@Setter
public class SecurityController {

    @Autowired
    private UserPrincipalService userPrincipalService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public JavaMailSender emailSender;

    @Value("${app.back.end.url.environment}")
    private String urlEnvironment;

    @Value("${app.backe.end.register.mail}")
    private String appMail;

    /**
     * Metodo para registrar nuevo usuario con confirmacion al correo
     *
     * @param signUpRequest
     * @return Response(Content String)
     */
    @PostMapping({"/signup"})
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody SingUpRequestPojo signUpRequest) {
        if (userPrincipalService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email is alredy registered!", HttpStatus.BAD_REQUEST);
        }
        if (userPrincipalService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("User is NOT available!", HttpStatus.BAD_REQUEST);
        }
        UserPrincipal newUser = new UserPrincipal();
        String password = new BCryptPasswordEncoder().encode(signUpRequest.getPassword());
        newUser.setPassword(password);
        newUser.setUsername(signUpRequest.getUsername());
        newUser.setName(signUpRequest.getName());
        newUser.setLastName(signUpRequest.getLastName());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setEnabled(false);
        newUser.setRole(RoleEnum.USER.getCode());
        StringBuilder sb = new StringBuilder();
        sb.append("Thank you for sign up in MeMovie.com, Please confirm your account at the link below: ");
        sb.append(urlEnvironment + "security/confirm/" + newUser.getUsername());

        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setTo(newUser.getEmail());
        simpleMail.setFrom(appMail);
        simpleMail.setSubject("MeMovie Resgister Service");
        simpleMail.setText(sb.toString());

        emailSender.send(simpleMail);
        newUser = userPrincipalService.save(newUser);

        return new ResponseEntity<String>("Sign up almost complete, please check your email acount!", HttpStatus.ACCEPTED);
    }

    /**
     * Metodo de confirmacion para nuevo usuario
     *
     * @param userName
     * @return Response(Content String)
     */
    @GetMapping({"/confirm/{userName}"})
    public ResponseEntity<?> confirmNewUser(@PathVariable("userName") String userName) {
        if (userPrincipalService.existsByUsername(userName)) {
            UserPrincipal userToConfirm = userPrincipalService.findByUsername(userName);
            userToConfirm.setEnabled(Boolean.TRUE);
            userPrincipalService.save(userToConfirm);
            return new ResponseEntity<>("The user " + userName + " has been confirmed!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid user!", HttpStatus.BAD_REQUEST);
    }

    /**
     * Metodo para deshabilitar un usuario ADMIN ONLY
     *
     * @param userName
     * @return Response(Content String)
     */
    @PutMapping({"/admin/disable/user/{userName}"})
    public ResponseEntity<?> disableUser(@PathVariable("userName") String userName) {
        if (userPrincipalService.existsByUsername(userName)) {
            UserPrincipal userToConfirm = userPrincipalService.findByUsername(userName);
            userToConfirm.setEnabled(Boolean.FALSE);
            userPrincipalService.save(userToConfirm);
            return new ResponseEntity<>("The user " + userName + " has been DISABLE!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para cambiar role a un usuario ADMIN ONLY
     *
     * @param userName
     * @param role
     * @return Response(Content String)
     */
    @PutMapping({"/admin/change/{userName}/{role}"})
    public ResponseEntity<?> changeRole(@PathVariable("userName") String userName, @PathVariable("role") String role) {
        if (userPrincipalService.existsByUsername(userName) && !role.isEmpty()) {
            UserPrincipal userToConfirm = userPrincipalService.findByUsername(userName);
            userToConfirm.setRole((role.toUpperCase().contains("ADMIN") ? RoleEnum.ADMIN.getCode() : RoleEnum.USER.getCode()));
            userPrincipalService.save(userToConfirm);
            return new ResponseEntity<>("The user " + userName + " has been DISABLE!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid! check the parameters", HttpStatus.NOT_ACCEPTABLE);
    }

}
