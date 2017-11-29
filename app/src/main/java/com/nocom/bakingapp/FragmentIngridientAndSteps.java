package com.nocom.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moha on 11/18/2017.
 */

public class FragmentIngridientAndSteps extends Fragment {

    RecyclerView mrecyclerview;
    IngriAdapter adapter;
    List<Ingrideint> listItems;
    ArrayList<? extends Steps> listItems2;
     Boolean mTablet;


    Button[] buttons = new Button[0];

    Button ingrdinets;
   /* Button step1;
    Button step2;
    Button step3;
    Button step4;
    Button step5;
    Button step6;
    Button step7;
    Button step8;
    Button step9;
    Button step10;
    Button step11;
    Button step12;
    Button step13;

*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_ingrdientsandsteps, container, false);


        mrecyclerview = (RecyclerView) view.findViewById(R.id.mrecycle);


        ingrdinets = (Button) view.findViewById(R.id.ingrdientbutton);


        listItems2 = getArguments().getParcelableArrayList("moha");

        buttons = new Button[13];
        buttons[0] = (Button) view.findViewById(R.id.moha2);
        buttons[1] = (Button) view.findViewById(R.id.moha3);
        buttons[2] = (Button) view.findViewById(R.id.moah4);
        buttons[3] = (Button) view.findViewById(R.id.moha5);
        buttons[4] = (Button) view.findViewById(R.id.moha6);
        buttons[5] = (Button) view.findViewById(R.id.moha7);
        buttons[6] = (Button) view.findViewById(R.id.moha8);
        buttons[7] = (Button) view.findViewById(R.id.moha9);
        buttons[8] = (Button) view.findViewById(R.id.moha10);
        buttons[9] = (Button) view.findViewById(R.id.moha11);
        buttons[10] = (Button) view.findViewById(R.id.moha12);
        buttons[11] = (Button) view.findViewById(R.id.moha13);
        buttons[12] = (Button) view.findViewById(R.id.moha14);



        listItems = getArguments().getParcelableArrayList("edttext");




            moha();


        return view;

    }


    public void moha() {
        ingrdinets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getArguments() != null) {

                    LinearLayoutManager llm = new LinearLayoutManager(getContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    mrecyclerview.setLayoutManager(llm);
                    adapter = new IngriAdapter(getActivity(), listItems);
                    mrecyclerview.setAdapter(adapter);
                    //String llk=
                    Log.i(" listitem2", listItems2.get(0).ndescripsion.toString());
                    Log.i(" listitemsize", String.valueOf(listItems2.size()));


                }


            }

        });


        for (int i = 0; i < listItems2.size(); i++) {

            if (listItems2.get(i).ndescripsion != null || listItems2.get(i).ndescripsion != "") {
                final Button mybutton = buttons[i];
                mybutton.setVisibility(View.VISIBLE);
                mybutton.setText(listItems2.get(i).getNshortDescription());
                final int finalI = i;
                mTablet = ((DetailActivity) getActivity()).isTablet();

                mybutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTablet) {
                            String describtion = listItems2.get(finalI).getNdescripsion();
                            String shortdesc = listItems2.get(finalI).getNshortDescription();
                            int number = listItems2.get(finalI).getNumber();

                            FragmentDetailedSteps fragmentDetailedSteps = new FragmentDetailedSteps();
                            Bundle args = new Bundle();

                            if (listItems2.get(finalI).getNurl() != null) {
                                String url = listItems2.get(finalI).getNurl();
                                args.putString("url", url);
                            }
                            args.putString("YourKey", describtion);
                            args.putString("mohasa", shortdesc);
                            args.putInt("mmm", number);
                            args.putParcelableArrayList("stepsarraylist", listItems2);
                            fragmentDetailedSteps.setArguments(args);

                            Toast.makeText(getContext(), "iam in tablet mode", Toast.LENGTH_SHORT).show();

                            ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                    //.r(FragmentIngridientAndSteps.this)
                                    .replace(R.id.container2, fragmentDetailedSteps)
                                    .addToBackStack(null)
                                    .commit();
                        }
                        else{

                            Toast.makeText(getContext(), "not tablet", Toast.LENGTH_SHORT).show();
                            String describtion = listItems2.get(finalI).getNdescripsion();
                            String shortdesc = listItems2.get(finalI).getNshortDescription();
                            int number = listItems2.get(finalI).getNumber();

                            FragmentDetailedSteps fragmentDetailedSteps = new FragmentDetailedSteps();
                            Bundle args = new Bundle();

                            if (listItems2.get(finalI).getNurl() != null) {
                                String url = listItems2.get(finalI).getNurl();
                                args.putString("url", url);
                            }
                            args.putString("YourKey", describtion);
                            args.putString("mohasa", shortdesc);
                            args.putInt("mmm", number);
                            args.putParcelableArrayList("stepsarraylist", listItems2);
                            fragmentDetailedSteps.setArguments(args);


                            ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                    //.r(FragmentIngridientAndSteps.this)
                                    .replace(R.id.container, fragmentDetailedSteps)
                                    .addToBackStack(null)
                                    .commit();




                       //     ((DetailActivity)getActivity()).replaceFragment();

                        }
                    }
                });


            }

        }


    }







}




