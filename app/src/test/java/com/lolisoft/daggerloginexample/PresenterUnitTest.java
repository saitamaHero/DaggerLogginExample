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

    @Test
    public void verifyShowUserNotValid()
    {
        presenter.getCurrentUser();

        verify(mockedView, times(1)).showUserNotValid();
    }
}
