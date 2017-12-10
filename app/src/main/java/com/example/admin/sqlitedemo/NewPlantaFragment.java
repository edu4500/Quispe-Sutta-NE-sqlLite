package com.example.admin.sqlitedemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPlantaFragment extends Fragment {

    EditText etFirstName, etLastName, etMobile;
    Button btnSave, btnShow;

    public NewPlantaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_person, container, false);
        initView(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void initView(View view) {
        etFirstName=view.findViewById(R.id.etFirstName);
        btnSave=view.findViewById(R.id.btnSave);
        btnShow=view.findViewById(R.id.btnShow);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlantaDAO plantaDAO=new PlantaDAO(getActivity());
                Planta planta=new Planta();
                planta.setNombre(etFirstName.getText().toString());
                String msg=plantaDAO.savePlanta(planta);
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new AllPlantaFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout,fragment)
                        .addToBackStack(NewPlantaFragment.class.getName())
                        .commit();
            }
        });
    }

}
