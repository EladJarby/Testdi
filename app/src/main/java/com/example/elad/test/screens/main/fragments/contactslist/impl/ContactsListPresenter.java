package com.example.elad.test.screens.main.fragments.contactslist.impl;

import com.example.elad.test.data.DataManager;
import com.example.elad.test.screens.main.fragments.contactslist.contracts.ContactsListContract;

public class ContactsListPresenter implements ContactsListContract.Presenter {

    private DataManager dataManager;
    private ContactsListContract.View view;

    public ContactsListPresenter() {
        this.dataManager = DataManager.getDataManager();
    }

    @Override
    public void onAttach(ContactsListContract.View view) {
        this.view = view;
        init();
    }

    private void init() {
        if(view != null) {
            view.setData(dataManager.getLocalDataManager().getAppDatabase().contactsItemDao().getAllContacts());
        }
    }

    @Override
    public void onDetach() {
        view = null;
    }
}
