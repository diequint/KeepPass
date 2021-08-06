package com.diequint.keeppass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllKeysFragment extends Fragment {
    List<ListElement> elements;
    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        vista=  inflater.inflate(R.layout.fragment_all_keys, container, false);
        init();

        return vista;
    }

    public void init() {
        elements = new ArrayList<>();
        elements.add(new ListElement("#1f85de","Facebook","diego@mail.com","www.facebook.com/login"));
        elements.add(new ListElement("#56a5ea","Twitter","diego@mail.com","www.twitter.com/login"));
        elements.add(new ListElement("#000000","Github","diequint","www.github.com/home"));
        elements.add(new ListElement("#129b12","Spotify","diego@mail.com","www.player.spotify.com/login"));
        elements.add(new ListElement("#050a48","Blackboard","201700000","www.buap.blackboard.com/acceder"));
        elements.add(new ListElement("#ff0000","Adobe","diego@mail.com","www.creativecloud.com/login"));
        elements.add(new ListElement("#009688","Google","diego@mail.com","www.gmail.com"));
        elements.add(new ListElement("#f44336","Outlook","diego@mail.com","www.office365.com/outlook/login"));
        ListAdapter listAdapter = new ListAdapter(elements,getActivity().getApplicationContext());
        RecyclerView recyclerView =vista.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(listAdapter);
    }
}