package com.lolisoft.daggerloginexample.login;

public class LoginActivityModel implements LoginActivityMVP.Model{

    private LoginRepository repository;

    public LoginActivityModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String name, String lastName) {
        //logica de negocio aqui (validaciones, existencia, etc)
        repository.saveUser(new User(name,lastName));
    }

    @Override
    public User getUser() {
        //logica de negocio aqui (validaciones, existencia, transformaciones, etc)
        return repository.getUser();
    }
}
