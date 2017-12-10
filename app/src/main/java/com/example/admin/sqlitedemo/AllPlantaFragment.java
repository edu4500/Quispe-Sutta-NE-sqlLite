package com.example.admin.sqlitedemo;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllPlantaFragment extends Fragment {

    ListView listView;
    ArrayList<Planta> plantas;
    PlantaDAO plantaDAO;
    ArrayAdapter<Planta> plantaArrayAdapter;

    public AllPlantaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_all_persons, container, false);
        // Inflate the layout for this fragment
        listView=view.findViewById(R.id.listview);
        plantaDAO=new PlantaDAO(getActivity());
        setAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                showAlertDialog(position);
            }
        });

        return view;
    }

    private void setAdapter() {
        plantas=plantaDAO.getAllPlanta();
        plantaArrayAdapter=new ArrayAdapter<Planta>(getActivity(),android.R.layout.simple_list_item_1,plantas);
        listView.setAdapter(plantaArrayAdapter);
    }

    private void showAlertDialog(final int position) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Select Option")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showEditDialog(position);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showConfirmationDialog(position);
                    }
                })
                .show();
    }

    private void showEditDialog(int position) {
        final Planta planta=plantas.get(position);

        View view=getActivity().getLayoutInflater().inflate(R.layout.fragment_all_persons,null);
        final EditText etFirstName=view.findViewById(R.id.etFirstName);
        Button btnSave=view.findViewById(R.id.btnSave);
        Button btnShow=view.findViewById(R.id.btnShow);
        etFirstName.setText(planta.getNombre());
        btnSave.setVisibility(View.GONE);
        btnShow.setVisibility(View.GONE);
        new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        planta.setNombre(etFirstName.getText().toString());
                        String msg=plantaDAO.updatePlanta(planta);
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                        setAdapter();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    private void showConfirmationDialog(final int position) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Do you really want to Delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Planta planta=plantas.get(position);
                        String msg=plantaDAO.removePlanta(planta.getCodigo());
                        plantas.remove(planta);
                        plantaArrayAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

}
