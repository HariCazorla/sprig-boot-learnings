# Spring security working

## General Worlflow

`Request -> filter -> authentication manager -> authentication provider -> (UserDetailsService, PasswordEncoder)`

## Authentication

- Create AppUser, implement UserDetails (uname, pwd, role)
- Create AppUserRepo
- Create AppUserService, implement UserDetailsService (loadUserByUsername)
- Create DaoAuthProvider, set UserDetailsService and BCryptPasswordEncoder
- Update Authentication manager to use DaoAuthProvider
