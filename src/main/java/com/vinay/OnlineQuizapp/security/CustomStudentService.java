package com.vinay.OnlineQuizapp.security;

import com.vinay.OnlineQuizapp.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomStudentService implements UserDetails {

    private Student student;

    public CustomStudentService(Student student) {
        this.student = student;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> simpleGrantedAuthorities =
                student.getRoles().stream()
                        .map(roleEntity -> {
                            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleEntity.getName());
                            return simpleGrantedAuthority;
                        }
                        ).toList();

        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return student.getPassword();
    }

    @Override
    public String getUsername() {
        return student.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return student.getIsEnable();
    }
}
