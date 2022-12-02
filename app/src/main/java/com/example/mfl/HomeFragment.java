package com.example.mfl;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<String> nomegiochi = new ArrayList<>();
    ArrayList<String> sviluppo = new ArrayList<>();
    private ListView lv;
    String data;
    Button bottone;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        bottone = viewGroup.findViewById(R.id.button5);

        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new fetchdata().start();
            }
        });

        lv = (ListView) getActivity().findViewById(R.id.lista);

        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add(nomegiochi.toString());


        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, your_array_list );

        lv.setAdapter(arrayAdapter);

        return viewGroup;
    }




        class fetchdata extends Thread {



        @Override
    public void run(){



            try {
                URL url = new URL ("https://www.giantbomb.com/api/games/3030-4725/?api_key=6cda058679cecae3b068ecd416ab3eab3e2c5baf&format=json&field_list=name&filter=original_release_date:2020-05-10|2020-07-12");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    data = data + line;
                }

                if(!data.isEmpty()){

                    String crappyPrefix = "null";

                    if(data.startsWith(crappyPrefix)){
                        data = data.substring(crappyPrefix.length(), data.length());
                    }

                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray risultati = jsonObject.getJSONArray("results");
                    nomegiochi.clear();
                    for (int i = 0; i < risultati.length(); i++){
                        JSONObject nome = risultati.getJSONObject(i);


                            String nomevero = nome.getString("name");
                            nomegiochi.add(nomevero);




                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }




        }
    }


}
