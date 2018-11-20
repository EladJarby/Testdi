package com.example.elad.test.screens.main.fragments.contactslist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elad.test.R;
import com.example.elad.test.data.model.ContactsItem;
import com.example.elad.test.screens.base.BaseFragment;
import com.example.elad.test.screens.main.fragments.contactslist.adapters.ContactsListAdapter;
import com.example.elad.test.screens.main.fragments.contactslist.contracts.ContactsListContract;
import com.example.elad.test.screens.main.fragments.contactslist.impl.ContactsListPresenter;

import java.util.List;

public class ContactsListFragment extends BaseFragment implements ContactsListContract.View {

    ContactsListContract.Presenter presenter;

    RecyclerView recyclerView;
    ContactsListAdapter contactsListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ContactsListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts_list,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onAttach(this);
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        contactsListAdapter = new ContactsListAdapter(new ContactsListCallback());
        recyclerView.setAdapter(contactsListAdapter);
    }

    @Override
    public void setData(LiveData<List<ContactsItem>> allContacts) {
        allContacts.observe(this, new Observer<List<ContactsItem>>() {
            @Override
            public void onChanged(@Nullable List<ContactsItem> contactsItems) {
                contactsListAdapter.submitList(contactsItems);
            }
        });
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }
}
