package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao ususuarioDao;

    @Autowired
    private JWTUtil jwUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario user){

        Usuario userLogued = ususuarioDao.obtenerUsuarioPorCredenciales(user);
        if(userLogued != null){

            String tokenJwt = jwUtil.create(String.valueOf(userLogued.getId()),userLogued.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }
}
