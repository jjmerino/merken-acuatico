package cl.adepti.merkenacuatico.rest.controller;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.adepti.merkenacuatico.data.ExamRepository;
import cl.adepti.merkenacuatico.data.UserRepository;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.User;

@Controller
public class UserController implements AuthenticationProvider {
	
	@Autowired
	UserRepository repo;
	
	@RequestMapping("/profile")
	@ResponseBody
	public String exam() {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = repo.findByUsername("usuario").getPassword();
	      return name;
	}

	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        User user = repo.findByUsername(username);
        
        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }
 
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
 
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
 
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }
 
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
