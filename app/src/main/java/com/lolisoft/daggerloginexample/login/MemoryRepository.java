package com.lolisoft.daggerloginexample.login;

public class MemoryRepository implements LoginRepository {
    private User mUser;

    @Override
    public void saveUser(User user) {
        if(user != null){
            mUser = user;
        }
    }

    @Override
    public User getUser() {
        /*if(mUser == null){
            mUser = new User("Dionicio", "Acevedo");
            mUser.setId(0);
        }*/

        return mUser;
    }
}
