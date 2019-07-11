package com.lolisoft.daggerloginexample;

import com.lolisoft.daggerloginexample.login.LoginActivityMVP;
import com.lolisoft.daggerloginexample.login.LoginActivityPresenter;
import com.lolisoft.daggerloginexample.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterUnitTest {

    LoginActivityPresenter presenter;
    User user;

    LoginActivityMVP.Model mockedModel;
    LoginActivityMVP.View mockedView;

    @Before
    public void init(){
        mockedModel = mock(LoginActivityMVP.Model.class);
        mockedView = mock(LoginActivityMVP.View.class);

        user = new User("John", "Doe");

        //when(mockedModel.getUser()).thenReturn(user);

        //when(mockedView.getName()).thenReturn("Dionicio");
        //when(mockedView.getLastName()).thenReturn("Acevedo");

        presenter = new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);
    }

    @Test
    public void noExistsInteractionWithView() {
        presenter.getCurrentUser();

        verify(mockedView, times(1)).showUserNotValid();
        //verify(mockedView, never()).showUserNotValid();
    }

    @Test
    public void loadUserFromRepoWhenValidUserIsPresented()
    {
        when(mockedModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        //Check the flow with the model data
        verify(mockedModel, times(1)).getUser();
        //Check the flow with view
        verify(mockedView, times(1)).setName("John");
        verify(mockedView, times(1)).setLastName("Doe");

        verify(mockedView, never()).showUserNotValid();
    }
/*
    @Test
    public void verifyShowUserNotValid()
    {
        presenter.getCurrentUser();

        verify(mockedView, times(1)).showUserNotValid();
    }*/
    @Test
    public void verifyShowUserNotValidWhenUserIsNull()
    {
        when(mockedModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        //Check the flow with the model data
        verify(mockedModel, times(1)).getUser();
        //Check the flow with view
        verify(mockedView, never()).setName("John");
        verify(mockedView, never()).setLastName("Doe");


        verify(mockedView, times(1)).showUserNotValid();

    }

    @Test
    public void createUserErrorWhenAnyFieldIsEmpty()
    {
        when(mockedView.getName()).thenReturn("John");
        when(mockedView.getLastName()).thenReturn("");

        //Simulate the click for the empty lastname
        presenter.onLoginButtonClick();

        when(mockedView.getName()).thenReturn("");
        when(mockedView.getLastName()).thenReturn("Doe");

        //Simulate the click for the empty name
        presenter.onLoginButtonClick();

        //check the showEmptyField message be called when any field is empty
        //in this case 2 times for name and lastname fields in the view
        verify(mockedView, times(2)).showEmptyField();

        //name is called two times because the name is first in the onclick
        verify(mockedView, times(2)).getName();
        //last name is called one time because the lastname isn't called if name contains text
        verify(mockedView, times(1)).getLastName();

        //check the createUser method never been called
        verify(mockedModel, never()).createUser("","");
    }

    @Test
    public void saveUserValid()
    {
        //simulate the view contains information
        when(mockedView.getName()).thenReturn("John");
        when(mockedView.getLastName()).thenReturn("Doe");

        //simulate the user click the button
        presenter.onLoginButtonClick();


        verify(mockedView, times(2)).getName();
        verify(mockedView, times(2)).getLastName();
        //Show a succes message in the view
        verify(mockedView, times(1)).showUserSaved();
        //verify the createUser method be called 1 time with the Name: John and last name: Doe
        verify(mockedModel, times(1)).createUser("John", "Doe");
    }
}
